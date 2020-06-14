package ru.job4j.map.pimalex;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {
    @Test
    public void whenEqualsAndHashCodeAreNotOverride() {
        //создаем два объекта с одинаковыми полями
        User user1 = new User("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));
        User user2 = new User("Alex", 2, new GregorianCalendar(1990, Calendar.APRIL, 15));
        //посмотрим на хэшкоды наших объектов
        //Т.к. алгоритм генерации хешкода для объекта работает с учаcтием генератора случайных чисел,
        //то при каждом выполнении программы хеш-коды объектов будут разными.
        System.out.println("hash-code user1: " + user1.hashCode()); //3447021
        System.out.println("hash-code user2: " + user2.hashCode()); //440434003
        System.out.println("user1 -> " + user1);
        System.out.println("user2 -> " + user2);

        //добавить объекты в карту
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        //в результате увидим две записи в карту, у которых буду различные ключи и различные значения.
        //не смотря на одинаковые со стороны бизнес логики объекты они все же различные т.к. у них не переопределены
        //методы equals() и hashCode()
        System.out.println(map); //{ru.job4j.map.pimalex.User@1a407d53=java.lang.Object@3d8c7aca, ru.job4j.map.pimalex.User@3498ed=java.lang.Object@5ebec15}


        //Тут переопределен только hashCode()
        // -1887340557
        //-1887340557
        //{ru.job4j.map.pimalex.User@8f8177f3=java.lang.Object@1554909b, ru.job4j.map.pimalex.User@8f8177f3=java.lang.Object@6bf256fa}

        System.out.println("достали объект по ключу user1 -> " + map.get(user1));
        System.out.println("достали объект по ключу user2 -> " + map.get(user2));

    }


}