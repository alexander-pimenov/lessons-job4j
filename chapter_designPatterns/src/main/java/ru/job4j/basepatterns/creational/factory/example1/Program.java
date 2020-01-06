package ru.job4j.basepatterns.creational.factory.example1;

public class Program {
    public static void main(String[] args) {
        DeveloperFactory developerFactory = new JavaDeveloperFactory();
        Developer developer = developerFactory.createDeveloper();

        developer.writeCode();
    }

}
