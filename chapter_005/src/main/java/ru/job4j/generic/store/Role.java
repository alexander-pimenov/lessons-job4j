package ru.job4j.generic.store;

public class Role extends Base {

    protected Role(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return String.format("Role{ %s }", getId());
    }
}
