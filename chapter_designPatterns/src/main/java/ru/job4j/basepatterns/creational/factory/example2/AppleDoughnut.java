package ru.job4j.basepatterns.creational.factory.example2;

/**
 * Теперь давайте создадим четыре класса пончиков, которые имплементируют интерфейс
 * Doughnut.Яблочный
 *
 * @author Alex
 */


public class AppleDoughnut implements Doughnut {
    @Override
    public void eat() {
        System.out.println("You are eating Apple doughnut!");
    }
}
