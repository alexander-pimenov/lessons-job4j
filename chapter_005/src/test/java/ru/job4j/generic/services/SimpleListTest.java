package ru.job4j.generic.services;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleListTest {

    @Test
    public void whenCreateContainerShouldReturnTheSameType() {
        //Обязательно в угловых скобках указываем тот тип, который должен
        //содержаться в нашем SimpleList, в массиве. Если мы не укажем это в
        //угловых скобках <>, то компилятор будет ругаться.
        //Поэтому обязательно указываем хранимый тип, например <String>.
        //Хранить можно только объекты, но не примитивные типы!!!
        SimpleList<String> simple = new SimpleList<>(4);

        //Т.к. Steck наследутся от SimpleList и имеет указаный в классе
        // генерик <String>, то здесь можно уже не писать <String>.
        Stack<String> stack = new Stack(6);

        stack.add("stack test");
        String stackResult = stack.get(0);

        simple.add("test");
        String result = simple.get(0);

        assertThat(result, is("test"));
        assertThat(stackResult, is("stack test"));
    }

    @Test
    public void whenTypeIntShouldReturnInt() {

        SimpleList<Integer> simple = new SimpleList<>(4);

        simple.add(10);
        int result = simple.get(0);

        assertThat(result, is(10));
    }

    /**
     * Для примера работы с генериками, у которых есть ограничения
     * создали пустые классы:
     */
    public class A {
    }

    public class B extends A {
    }

    public class C extends B {
    }

    //метод для демонстрации работы нашего кода (это не тест)
    public void wildCardTest() {
        SimpleList<B> list = new SimpleList<>(10);
        //И сюда мы можем вставить любые типы, которые расширяют класс А
        //т.е. здесь идет downcasting и они приводятся к одному значению.
        // list.add(new A());
        list.add(new B());
        list.add(new C());

        print(list);
        printUpper(list);
        printLower(list);
    }

    //<?> говорит, что сюда можно вставить любой тип данных,
    //который может храниться в нашем контейнере: и А и В и С.
    public void print(SimpleList<?> list) {
        //todo print
    }

    //для демонстрации UpperWildCard
    //<? extends B> - говорит, чтобы наш контейнер содержал только
    //класс В либо только классы, которые расширяют класс В.
    public void printUpper(SimpleList<? extends B> list) {
        //todo printUpper
    }

    //для демонстрации LowerWildCard
    //<? super B> - говорит, что либо мы используем класс В, либо классы-родители
    //для класса В.
    public void printLower(SimpleList<? super B> list) {
        //todo printLower
    }

}