package ru.job4j.basepatterns.behavioral.interpreter;

public interface Expression {
    public boolean interpret(String context);
}
