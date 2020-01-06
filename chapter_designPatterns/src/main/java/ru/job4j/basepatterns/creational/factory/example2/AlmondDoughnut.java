package ru.job4j.basepatterns.creational.factory.example2;

/**
 * Теперь давайте создадим четыре класса пончиков, которые имплементируют интерфейс
 * Doughnut.Миндальный
 * 
 * @author Alex
 *
 */

public class AlmondDoughnut implements Doughnut {

	@Override
	public void eat() {

		System.out.println("You are eating Almond doughnut!");
	}

}
