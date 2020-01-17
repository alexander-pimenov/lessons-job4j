package ru.job4j.basepatterns.behavioral.iterator.example1;

/**
 * Клиент работает со всеми объектами через интерфейсы коллекции и итератора.
 * Так клиентский код не зависит от конкретных классов, что позволяет применять
 * различные итераторы, не изменяя существующий код программы.
 *
 * В общем случае клиенты не создают объекты итераторов, а получают их из коллекций.
 * Тем не менее, если клиенту требуется специальный итератор,
 * он всегда может создать его самостоятельно.
 */

public class JavaDeveloperRunner {
    public static void main(String[] args) {
        String[] skills = {"Java", "Spring", "Hibernate", "Maven", "PostgreSQL"};

        JavaDeveloper javaDeveloper = new JavaDeveloper("Eugene Suleimanov", skills);

        //Получаем итератор из коллекции.
        Iterator iterator = javaDeveloper.getIterator();
        System.out.println("Developer: " + javaDeveloper.getName());
        System.out.println("Skills: ");

        //Вызывая Итератор в цикле, мы проходимся по всей коллекции skills
        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString() + " ");
        }
    }
}
