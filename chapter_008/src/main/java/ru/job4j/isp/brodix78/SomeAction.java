package ru.job4j.isp.brodix78;

public class SomeAction implements Action {
    @Override
    public boolean execute() {
        System.out.println("Make some action");
        return true;
    }
}
