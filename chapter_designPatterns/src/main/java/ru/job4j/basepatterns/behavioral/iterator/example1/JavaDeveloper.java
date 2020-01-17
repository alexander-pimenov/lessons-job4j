package ru.job4j.basepatterns.behavioral.iterator.example1;

/**
 * JavaDeveloper - это Конкретная Коллекция, которая
 * возвращает новый экземпляр определённого конкретного итератора,
 * связав его с текущим объектом коллекции.
 * Обратите внимание, что сигнатура метода возвращает интерфейс
 * итератора public Iterator getIterator().
 * Это позволяет клиенту не зависеть от конкретных классов итераторов.
 * <p>
 * private class SkillIterator implements Iterator - это
 * конкретный итератор, который реализует алгоритм обхода какой-то конкретной коллекции.
 * Объект итератора должен сам отслеживать текущую позицию при обходе коллекции,
 * чтобы отдельные итераторы могли обходить одну и ту же коллекцию независимо.
 */

public class JavaDeveloper implements Collection {
    private String name;
    private String[] skills;

    public JavaDeveloper(String name, String[] skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public Iterator getIterator() {
        return new SkillIterator();
    }

    private class SkillIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < skills.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return skills[index++];
        }
    }
}
