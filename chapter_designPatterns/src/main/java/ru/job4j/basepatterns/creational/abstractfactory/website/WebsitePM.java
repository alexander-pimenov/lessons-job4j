package ru.job4j.basepatterns.creational.abstractfactory.website;


import ru.job4j.basepatterns.creational.abstractfactory.ProjectManger;

public class WebsitePM implements ProjectManger {
    @Override
    public void manageProject() {
        System.out.println("Website PM manages website project...");
    }
}
