package ru.job4j.tutorial.initialization_blocks;

/**
 * Когда в классе присутствуют все три метода инициализации (конструкторы,
 * нестатические блоки инициализации и статические блоки инициализации),
 * статические всегда выполняются первыми (когда класс загружается в память)
 * в порядке их объявления, затем выполняются нестатические блоки инициализации
 * в порядке, в котором они объявлены, а после них – конструкторы.
 * Когда вводится суперкласс, порядок выполнения немного меняется:
 * 1.	Статические блоки инициализации суперкласса, в порядке их объявления.
 * 2.	Статические блоки инициализации подкласса, в порядке их объявления.
 * 3.	Нестатические блоки инициализации суперкласса, в порядке их объявления.
 * 4.	Конструктор суперкласса.
 * 5.	Нестатические блоки инициализации подкласса, в порядке их объявления.
 * 6.	Конструктор подкласса.
 * <p>
 * Обратите внимание на то что статические блоки инициализации были выполнены только один раз,
 * даже если были созданы два объекта Foo.
 * Хотя нестатистические и статические блоки инициализации могут быть полезны, логика
 * инициализации должна быть помещена в конструкторы, а методы (или статические методы)
 * должны использоваться в случаях, когда сложная логика требует инициализации состояния
 * объекта.
 */

public class Foo extends Bar {
    static {
        System.out.println("Foo:static 1");
    }

    {
        System.out.println("Foo:instance 1 (non-static)");
    }

    static {
        System.out.println("Foo:static 2");
    }

    public Foo() {
        System.out.println("Foo:constructor");
    }

    public Foo(String name) {
        super(name);
        System.out.println("Foo:name-constructor");
    }

    {
        System.out.println("Foo:instance 2 (non-static)");
    }

    public static void main(String[] args) {
        System.out.println("#################");
        new Foo();
        System.out.println("#################");
        new Foo("Bazzy");
    }
}

/*
Результат:

Bar:static 1
Bar:static 2
Foo:static 1
Foo:static 2
#################
Bar:instance 1 (non-static)
Bar:instance 2 (non-static)
Bar:constructor
Foo:instance 1 (non-static)
Foo:instance 2 (non-static)
Foo:constructor
#################
Bar:instance 1 (non-static)
Bar:instance 2 (non-static)
Bar:name-constructor
Foo:instance 1 (non-static)
Foo:instance 2 (non-static)
Foo:name-constructor
*/