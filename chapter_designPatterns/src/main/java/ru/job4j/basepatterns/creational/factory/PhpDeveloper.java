package ru.job4j.basepatterns.creational.factory;

public class PhpDeveloper implements Developer{
    @Override
    public void writeCode() {
        System.out.println("Php developer writes Php code...");
    }
}
