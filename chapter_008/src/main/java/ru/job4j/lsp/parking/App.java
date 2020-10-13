package ru.job4j.lsp.parking;

/**
 * По дефолту cars = 10,
 * trucks = 5
 *
 */

public class App {
    public static void main(String[] args) {
        SimpleParking simpleParking = new SimpleParking();
        Car car1 = new Car("a111aa77", "VW");
        Truck truck1 = new Truck("e222ee55", "Volvo");

        simpleParking.add(car1);
        simpleParking.add(car1);
        simpleParking.add(car1);
        simpleParking.add(truck1);
        simpleParking.add(truck1);
        simpleParking.add(truck1);
        simpleParking.add(truck1);
        simpleParking.add(car1);
        simpleParking.add(car1);
        simpleParking.add(car1);
        simpleParking.add(truck1);
        simpleParking.add(truck1);
        simpleParking.add(truck1);

        System.out.println("количество cars: " + simpleParking.cars.size());
        System.out.println("количество trucks: " + simpleParking.trucks.size());


        //        IntStream.range(0, 10).forEach(System.out::println);
//        ArrayList<Integer> numbs = new ArrayList<>();
//        IntStream.range(0, 10).forEach(numbs::add);
//        System.out.println(numbs);
//        System.out.println(numbs.size());

    }
}
