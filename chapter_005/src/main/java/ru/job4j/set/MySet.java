package ru.job4j.set;

import ru.job4j.list.*;

import java.util.Iterator;
import java.util.Objects;

/**
 * MySet
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MySet<E> implements Iterable<E> {
    private MyList<E> list;

    public MySet() {
        this.list = new MyArrayList<>();
    }

    private boolean checker(E model) {
        boolean checker = true;
        for (Iterator<E> itr = iterator(); itr.hasNext();) {
            if (itr.next().equals(model)) {
                checker = false;
            }
        }
        return checker;
    }

    public void add(E model) {
        if (checker(model)) {
            list.add(model);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MySet<?> mySet = (MySet<?>) o;
        return Objects.equals(list, mySet.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "MySet{" + "list=" + list + '}';
    }
}
