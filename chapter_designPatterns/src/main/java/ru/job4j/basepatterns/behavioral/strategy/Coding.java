package ru.job4j.basepatterns.behavioral.strategy;

class Coding implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Coding...");
    }
}
