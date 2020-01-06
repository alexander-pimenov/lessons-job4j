package ru.job4j.basepatterns.creational.factory.example2;

/**
 * Рассмотрим пример паттерна Фабрика на примере фабрики пончиков.
 * <p>
 * Здесь принимаем ENUM DoughnutTypes, а возвращаем любой объект,
 * имплементирующий интерфейс Doughnut. Таким образом, мы теперь можем
 * "конструировать" любой пончик и возвращать его пользователю.
 * <p>
 * Определяем тип с помощью обычного switch... case.
 * <p>
 * Какие преимущества дает паттерн Фабрика (Factory)
 * <p>
 * 1. Во-первых, мы можем создавать объекты разных типов с помощью одного и того
 * же метода. Это очень удобно, когда возникает ситуация, в которой мы не знаем,
 * какой тип нам понадобится. Например, создадим метод "скушать случайный
 * пончик" - eatRandomDoughnut. Этот метод в main() методе.
 * <p>
 * 2. Во-вторых, мы можем "запаковать" дополнительный функционал в нашу Фабрику.
 * Например, посчитаем, сколько пончиков каждого типа было создано конкретной
 * фабрикой. Сначала создаем новые переменные, которые будут хранить результат.
 * <p>
 * 3. И в-третьих, с помощью паттерна Factory мы можем генерировать сложные
 * объекты намного проще и с меньшим количеством ошибок.
 *
 * @author Alex
 */

public class DoughnutFactory {

    // переменные, которые будут хранить результат количества созданных пончиков
    private Integer cherryDoughnutCount = 0;
    private Integer chocolateDoughnutCount = 0;
    private Integer almondDoughnutCount = 0;
    private Integer appleDoughnutCount = 0;

    // метод создающий пончики и считающий количество их
    public Doughnut getDoughnut(DoughnutTypes type) {
        Doughnut toReturn = null;
        switch (type) {
            case CHERRY:
                cherryDoughnutCount++;
                toReturn = new CherryDoughnut();
                break;
            case CHOCOLATE:
                chocolateDoughnutCount++;
                toReturn = new ChocolateDoughnut();
                break;
            case ALMOND:
                almondDoughnutCount++;
                toReturn = new AlmondDoughnut();
                break;
            case APPLE:
                appleDoughnutCount++;
                toReturn = new AppleDoughnut();
                break;
            default:
                throw new IllegalArgumentException("Wrong doughnut type:" + type);
        }
        return toReturn;

    }

    // метод для вывода результатов в консоль
    public void printCount() {
        System.out.println("Number of doughnuts produced (by type):");
        System.out.println("Cherry doughnuts: " + cherryDoughnutCount);
        System.out.println("Chocolate doughnuts: " + chocolateDoughnutCount);
        System.out.println("Almond doughnuts: " + almondDoughnutCount);
        System.out.println("Apple doughnuts: " + appleDoughnutCount);
        System.out.println("Total: " + (cherryDoughnutCount + chocolateDoughnutCount + almondDoughnutCount + appleDoughnutCount));

    }
}
