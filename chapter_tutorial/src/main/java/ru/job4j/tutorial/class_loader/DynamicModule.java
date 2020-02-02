package ru.job4j.tutorial.class_loader;

public class DynamicModule extends TrueStaticModule {
    public String toString() {
        return "DynamicModule, version 1! " + (counter++);
    }
}
