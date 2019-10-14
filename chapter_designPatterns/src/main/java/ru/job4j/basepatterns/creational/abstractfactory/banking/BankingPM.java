package ru.job4j.basepatterns.creational.abstractfactory.banking;


import ru.job4j.basepatterns.creational.abstractfactory.ProjectManger;

public class BankingPM implements ProjectManger {
    @Override
    public void manageProject() {
        System.out.println("Banking PM manages banking project...");
    }
}
