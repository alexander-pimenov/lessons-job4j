package ru.job4j.basepatterns.creational.factory.example1;

import ru.job4j.basepatterns.creational.factory.example1.Developer;

public class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Php developer writes Php code...");
    }
}
