package ru.job4j.lsp.parking;

public interface Parking {
    boolean add(Vehicle vehicle);

    Vehicle retrieve(String number);

    boolean canPark(Vehicle vehicle);
}
