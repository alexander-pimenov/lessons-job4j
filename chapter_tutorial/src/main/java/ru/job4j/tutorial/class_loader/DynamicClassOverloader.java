package ru.job4j.tutorial.class_loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * рассматривам пример из
 * http://java-online.ru/java-classloader.xhtml#top
 */
public class DynamicClassOverloader extends ClassLoader {

    /**
     * Системный загрузчик классов не просто загружает файлы классов с диска,
     * но еще и запоминает их во внутреннем кэше – так что последующие обращения
     * к loadClass для того же имени класса просто выдают готовый объект Class из кэша.
     * <p>
     * Мы будем хранить кэш в нестатическом private-поле типа java.util.HashMap нашего
     * класса DynamicClassOverloader.
     * <p>
     * Хотя Java-машина самостоятельно хорошо кэштрует классы. Достает их потом из кэша.
     */
    private Map classesHash = new java.util.HashMap();

    public final String[] classPath;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public DynamicClassOverloader(String[] classPath) {

        // Набор путей поиска - аналог переменной CLASSPATH
        this.classPath = classPath;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = findClass(name);
        if (resolve) {
            resolveClass(result);
        }
        return result;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    protected Class findClass(String name) throws ClassNotFoundException {
        Class result = (Class) classesHash.get(name);
        if (result != null) {
            /*
             * System.out.println("% Class " + name +
             *                       " found in cache");
             */

            return result;
        }
        //дополнительная проверка имени name
        if (name.toLowerCase().indexOf("truestatic") != -1) {
            return findSystemClass(name);
        }
        File f = findFile(name.replace('.', '/'), ".class");
        // Класс mypackage.MyClass следует искать файле
        // mypackage/MyClass.class
        /*
         * System.out.println("% Class " + name +
         *                    (f==null?"":" found in "+f));
         */
        if (f == null) {
            // Обращаемся к системному загрузчику в случае
            // неудачи. findSystemClass – это метод
            // абстрактного класса ClassLoader с объявлением
            // protected Class findSystemClass(String name)
            // (т.е. предназначенный для использования в
            // наследниках и не подлежащий переопределению).
            // Он выполняет поиск и загрузку класса по
            // алгоритму системного загрузчика. Без вызова
            // findSystemClass(name) нам пришлось бы
            // самостоятельно позаботиться о загрузке всех
            // стандартных библиотечных классов типа
            // java.lang.String, что потребовало бы
            // реализовать работу с JAR-архивами
            // (стандартные библиотеки почти всегда
            // упакованы в JAR)
            return findSystemClass(name);
        }
        try {
            byte[] classBytes = loadFileAsBytes(f);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(
                    "Cannot load class " + name + ": " + e);

        } catch (ClassFormatError e) {
            throw new ClassNotFoundException(
                    "Format of class file incorrect for class "
                            + name + ": " + e);
        }
        classesHash.put(name, result);
        return result;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    protected java.net.URL findResource(String name) {
        File f = findFile(name, "");
        if (f == null) {
            return null;
        }
        try {
            return f.toURL();
        } catch (java.net.MalformedURLException e) {
            return null;
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Поиск файла с именем name и, возможно, расширением
     * extension в каталогах поиска, заданных параметром
     * конструктора classPath. Имена подкаталогов в name
     * разделяются символом '/' – даже если в операционной
     * системе принят другой разделитель для подкаталогов.
     * (Именно в таком виде получает свой параметр метод
     * findResource.)
     */
    private File findFile(String name, String extension) {
        File f;
        //for (int k = 0; k < classPath.length; k++) { // с помощью обычного for
        for (String s : classPath) {
            //f = new File((new File(classPath[k])).getPath() // с помощью обычного for
            f = new File((new File(s)).getPath()
                    + File.separatorChar
                    + name.replace('/',
                    File.separatorChar)
                    + extension);
            if (f.exists())
                return f;
        }
        return null;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[(int) file.length()];
        FileInputStream f = new FileInputStream(file);
        try {
            f.read(result, 0, result.length);
        } finally {
            try {
                f.close();
            } catch (Exception e) {
                // Игнорируем исключения, возникшие при
                // вызове close. Они крайне маловероятны и не
                // очень важны - файл уже прочитан. Но если
                // они все же возникнут, то они не должны
                // замаскировать действительно важные ошибки,
                // возникшие при вызове read.
            }
        }
        return result;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
