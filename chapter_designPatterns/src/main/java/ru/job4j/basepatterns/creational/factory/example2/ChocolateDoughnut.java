package ru.job4j.basepatterns.creational.factory.example2;

/**
 * Теперь давайте создадим четыре класса пончиков, которые имплементируют интерфейс
 * Doughnut.Шоколадный
 * 
 * @author Alex
 *
 */

public class ChocolateDoughnut implements Doughnut {

	@Override
	public void eat() {

		System.out.println("You are eating Chocolate doughnut!");
	}

}
