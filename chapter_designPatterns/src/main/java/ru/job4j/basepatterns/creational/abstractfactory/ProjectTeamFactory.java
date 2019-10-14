package ru.job4j.basepatterns.creational.abstractfactory;

public interface ProjectTeamFactory {
    Developer getFirstDeveloper();

    Developer getSecondDeveloper();

    Tester getTester();

    ProjectManger getProjectManager();
}
