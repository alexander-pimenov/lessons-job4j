package ru.job4j.pimalex78.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String s1 = "";
        String s2 = "";
        String s3 = null;
        String s4 = null;
        String s5 = new String(""); //'new String' это избыточно
        String s6 = new String(""); //'new String' это избыточно

        System.out.println(s1.equals(s5)); //true
        System.out.println(s1.equals(s3)); //false, всегда false
//        System.out.println(s3.equals(s1)); //Exception java.lang.NullPointerException
//        System.out.println(s3.equals(s4)); //Exception java.lang.NullPointerException

        List<String> l = new ArrayList<>();
        l.add(s1);
        l.add(s2);
        l.add(s3);
        l.add(s4);
        l.add(s5);
        l.add(s6);
        System.out.println(l.size()); //6

        Set<String> s = new HashSet<>();
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);
        s.add(s5);
        s.add(s6);
        System.out.println(s.size()); //2


        {
//            A a = new A(); //нельзя создать объект абстрактного класса
            A b = new B();
            A c = new C();
        }
        {
//            B a = new A(); //нельзя создать объект абстрактного класса
            B b = new B();
//            B c = new C(); //несовместимый тип
        }
        {
//            C a = new A(); //нельзя создать объект абстрактного класса
//            C b = new B(); //несовместимый тип
            C c = new C();
        }

        A a = null;
        B b = null;
        C c = null;

        a = (A) b; //значение 'b' всегда null
        a = (A) c; //значение 'c' всегда null
        a = (C) a; //переменная 'a' присваивается самой себе
        a = (B) a; //переменная 'a' присваивается самой себе

//        b = (B) c; //неконвертируемые типы, невозможно кастовать 'с' к (B)
        b = (B) a; //значение 'a' всегда null
//        b = (C) a; //несовместимый тип
//        b = (A) b; //несовместимые типы

        c = (C) a;
//        c = (C) b; //неконвертируемые типы, невозможно кастовать 'b' к (C)
//        c = (A) a; //несовместимый тип
//        c = (A) c; //несовместимые типы
    }
}
