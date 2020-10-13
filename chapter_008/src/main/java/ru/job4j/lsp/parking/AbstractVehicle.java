package ru.job4j.lsp.parking;

import java.util.Objects;

public abstract class AbstractVehicle implements Vehicle {

    private String number;
    private String name;
    private int size;

    public AbstractVehicle(String number, String name, int size) {
        this.number = number;
        this.name = name;
        this.size = size;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String number() {
        return this.number;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractVehicle that = (AbstractVehicle) obj;
        return Objects.equals(number, that.number);
    }
}
