package ru.job4j.tutorial.initialization_blocks;

public abstract class Bar {
    private String name;

    static {
        System.out.println("Bar:static 1");
    }

    {
        System.out.println("Bar:instance 1 (non-static)");
    }

    static {
        System.out.println("Bar:static 2");
    }

    public Bar() {
        System.out.println("Bar:constructor");
    }

    {
        System.out.println("Bar:instance 2 (non-static)");
    }

    public Bar(String name) {
        this.name = name;
        System.out.println("Bar:name-constructor");
    }
}
