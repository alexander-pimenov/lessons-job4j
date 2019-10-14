package ru.job4j.map;

//import java.util.Calendar;
import java.util.Objects;

/**
 * User
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        //Если ссылка равна ссылки, то true. Тот же самый обьект.
        if (this == o) {
            return true;
        }
        //Для любой ненулевой ссылки на значение х выражение х. equals(null) должно возвращать false.
        //Так же проверяем классы, если разные то не равны.
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        //Определились, что o это инстанс того же класса, что и сравневаемый и начинаем проверку полей.
        //Для примитивов мы просто сравниваем на прямую, а для ссылочных типов используем внутрении реализации equals
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }
//Если есть @Override equals(), то делаем @Override hashCode().
    @Override
    public int hashCode() {
        //Если поля не инициализированы возвращаем 0 в кач. значения.
        if (name == null && children == 0 && birthday == null) {
            return 0;
        }
        //Назначаем нечетный итерджер 31, в книге почему-то 37.
        final int prime = 31;
        //Присвоим не 0 значение результату.
        int result = 1;
        //Если String name не null, то получаем хешкод String и прибавляем к нашему результату.
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        //Прибавляем кроичество детей к результату.
        result = prime * result + children;
        //Если обьект Calendar не null, то получаем хешкод birthday и прибавляем к нашему результату.
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        //Возврашаем результат.
        return result;
    }
}
