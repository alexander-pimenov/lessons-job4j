package ru.job4j.isp.task;

/**
 * 4.1. Напишите консольное приложение, которое создает и обрабатывает
 * коллекцию согласно варианта (таблица 1).
 * ТРЕБОВАНИЯ.
 * 1. Приложение должно быть написано на языке Java.
 * 2. Использовать только стандартные компиляторы и библиотеки.
 * 3. При кодировании должны быть использованы соглашения об
 * оформлении кода для языка Java.
 * Множество типа HashSet, которое содержит объекты "Точка", описываемые как (x, y)
 * Создать множество точек заданного количества. Затем найти точки, наиболее и наименее удаленные от заданной
 * прямой. Прямая определяется уравнением A * x + B * y + C = 0
 */
public class Runner {
    public static void main(String[] args) {
        double a = 2;
        double b = 2;
        double c = 2; //уравнение
        Points points = new Points();
        Menu menu = new Menu(points, a, b, c);
        menu.showMenu();

    }
}
