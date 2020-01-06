package ru.job4j.basepatterns.creational.factory.example2;

import java.util.Random;

/**
 * Создадим main(), в котором создадим все типы поисков по очереди и вызовем на
 * них метод eat().
 *
 * @author Alex
 */

public class Main {

    public static void main(String[] args) {

        DoughnutFactory factory = new DoughnutFactory();

        Doughnut cherry = factory.getDoughnut(DoughnutTypes.CHERRY);
        Doughnut chocolate = factory.getDoughnut(DoughnutTypes.CHOCOLATE);
        Doughnut amound = factory.getDoughnut(DoughnutTypes.ALMOND);
        Doughnut apple = factory.getDoughnut(DoughnutTypes.APPLE);

        cherry.eat();
        chocolate.eat();
        amound.eat();
        apple.eat();
        System.out.println("###################################");

        // запуск метода "случайный пончик" в цикле 100 раз
        for (int i = 0; i < 100; i++) {
            eatRandomDoughnut(factory);
        }

        factory.printCount();

    }

    // метод "скушать случайный пончик", выбор начинок из сущесвующих в Enum
    private static void eatRandomDoughnut(DoughnutFactory factory) {

        //переменная - "случайный пончик"
        Doughnut randomDoughnut = getRandomDoughnut(factory);

        System.out.printf("What a surprise! ");

        randomDoughnut.eat();
    }

    // метод генерации "случайного пончика"
    // Обратите внимание - nextInt() будет генерировать целые числа в диапазоне [0;4].
    // То есть возможны такие опции: 0, 1, 2 и 3. А это и есть 4 вида начинок пончиков.
    private static Doughnut getRandomDoughnut(DoughnutFactory factory) {

        Random random = new Random();

        DoughnutTypes type = DoughnutTypes.values()[random.nextInt(DoughnutTypes.values().length)];

        return (factory.getDoughnut(type));
    }

}
