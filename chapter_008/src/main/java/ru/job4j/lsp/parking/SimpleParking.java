package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Существует парковка для грузовых и легковых машин.
 * Количество парковочных мест для каждого типа машин
 * задается на этапе создания парковки.
 * Легковая машина может занять только место,
 * предназначенное для легковой машины.
 * Грузовая машина может разместиться на месте,
 * предназначенном для грузовых машин, либо
 * на N парковочных мест для легковых машин, стоящих рядом.
 * Необходимо разработать сервис для учета парковки машин.
 */

public class SimpleParking implements Parking {
    private static final int DEFAULT_CAR_PLACES = 10;

    private static final int DEFAULT_TRUCK_PLACES = 5;

    private static final int CAR_SIZE = 1;

    private final int carPlaces;

    private final int truckPlaces;

    public List<Vehicle> cars = new ArrayList<>();

    public List<Vehicle> trucks = new ArrayList<>();

    public SimpleParking() {
        this.carPlaces = DEFAULT_CAR_PLACES;
        this.truckPlaces = DEFAULT_TRUCK_PLACES;
    }

    public SimpleParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        if (!canPark(vehicle)) {
            throw new IllegalArgumentException();
        }
        if (vehicle.size() == CAR_SIZE) {
            cars.add(vehicle);
        } else {
            if (trucks.size() < truckPlaces) {
                trucks.add(vehicle);
            } else {
                //IntStream.range(0, 10) - генерируем int числа от 0 до 9 (10 не включается)
                IntStream.range(0, vehicle.size()).forEach(n -> cars.add(vehicle));
            }
        }
        return true;
    }

    @Override
    public Vehicle retrieve(String number) {
        List<Vehicle> carVehicles = cars.stream().filter(c -> c.number().equals(number)).collect(Collectors.toList());
        if (carVehicles.size() > 0 && cars.removeAll(carVehicles)) {
            return carVehicles.get(0);
        }
        List<Vehicle> truckVehicle = trucks.stream().filter(t -> t.number().equals(number)).collect(Collectors.toList());
        if (truckVehicle.size() > 0 && trucks.removeAll(truckVehicle)) {
            return truckVehicle.get(0);
        }
        return null;
    }

    @Override
    public boolean canPark(Vehicle vehicle) {
        if (vehicle.size() == CAR_SIZE && cars.size() == carPlaces) {
            return false;
        }
        if (vehicle.size() != CAR_SIZE && (trucks.size() == truckPlaces && vehicle.size() > (carPlaces - cars.size()))) {
            return false;
        }
        return true;
    }
}
