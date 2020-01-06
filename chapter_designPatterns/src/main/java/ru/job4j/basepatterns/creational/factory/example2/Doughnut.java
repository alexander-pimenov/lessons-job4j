package ru.job4j.basepatterns.creational.factory.example2;

/**
 * Введем пример с пончиками.
 * Для того, чтобы мы могли их производить на одной фабрике, 
 * нужно "указать, что все эти пончики имеют один тип" - а 
 * именно, "пончик". 
 * Давайте объединим их, создав общий для всех интерфейс Doughnut.
 * 
 * @author Alex
 *
 */

public interface Doughnut {

	void eat();
}
