package ru.job4j.map.pimalex;

import java.util.Calendar;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

//    В идеале метод equals() класса Object должен проверять объекты на эквивалентность,
//    исходная же реализация метода equals в классе Object
//    equals() сравнивает ссылки if (this == o) return true
//    (ссылки на адреса памяти зарезервированные под объекты),
//    и если они ссылаются на один и тот же объект, возвращает true,
//    equals() не сравнивает значения полей объектов!
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return children == user.children &&
//                Objects.equals(name, user.name) &&
//                Objects.equals(birthday, user.birthday);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, children, birthday);
//    }

//    Этот код для примера, его можно разобрать
//
//    @Override
//    public int hashCode(){
//        int result = Short.hashCode(name);
//        result = 31*result + Short.hashCode(children);
//        result = 31* result + Short.hashCode(birthday);
//        return result;
//    }

    //преимущество - однострочный, использует статический метод hash()
    //недостаток - низкая производительность
    //но если производительность не критична,
    //то можно использовать.
    @Override
    public int hashCode(){
        return Objects.hash(name, children, birthday);
    }



}
