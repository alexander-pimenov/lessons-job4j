package ru.job4j.basepatterns.creational.factory.example1;

import ru.job4j.basepatterns.creational.factory.example1.Developer;

public class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer writes Java code...");
    }
}
