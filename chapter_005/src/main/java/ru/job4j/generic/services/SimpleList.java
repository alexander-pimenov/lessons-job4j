package ru.job4j.generic.services;

/**
 * Разберем этот класс для закрепления понятия Generic
 * Напишем примитивный контейнер, который может хранить любой тип,
 * т.е. это есть примитивная обертка.
 */
public class SimpleList<E> {

    /*Если мы хотим создать контейнер, который будет унифицированным во всем коде,
     * то нужно использовать обобщенные типы данных, т.е. генерики.
     * Но до Java 1.5 их не было и мы имели следующую запись:*/

    private Object[] objects;
    private int index = 0;

    //Создадим конструктор
    public SimpleList(int size) {
        this.objects = new Object[size];

        /*КОД ЗАКОМЕНТИРОВАН, Т.К. INTELLIJ IDEA ВЫДАЕТ ОШИБКУ. НО ПРИНЦИП ВЕРНЫЙ.
        * не смог понять в чем ошибка.*/
        // Из этого класса Stack, т.к. он наследует класс SimpleList
        // мы можем теперь получить генерик. И вывести его на печать.
        //Это используется для того, чтобы создать инстанс нашего объекта.
        //Компилятор не знает какой тип ему здесь создать, т.к. в процессе
        //компиляции все эти типы будут подчищены.
//        Type t = ((ParameterizedType) getClass().getGenericSuperclass())
//                .getActualTypeArguments()[0];
//        try {
//            String typeName = t.getTypeName();
//            System.out.print("string " + typeName); //напечатаем пустую строчку
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    /*В параметр метода add() будем передавать бессылочную структуру данных,
     * как мы знаем это массив.*/

    //Здесь в методе lowcasting (понижение типа) до Object,
    //это упрощенная модель.
    public void add(E value) {
        this.objects[index++] = value;
    }

    public E get(int position) {
        //Здесь делаем приведение типов
        return (E) this.objects[position];
    }

    //Т.к. генерик не указан в названии класса, то мы прописываем его
    //самостоятельно и в возвращаемом типе данных и
    // также мы можем указать его во входящем параметре, если нужно: <K> K (K value)
    public <K> K print(K value) {
        System.out.println(value);
        return value;
    }
}
