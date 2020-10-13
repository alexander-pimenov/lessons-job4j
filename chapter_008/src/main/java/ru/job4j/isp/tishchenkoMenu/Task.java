package ru.job4j.isp.tishchenkoMenu;

public class Task implements ActionTask {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String todo() {
        return "Выполнение задачи";
    }
}
