package ru.job4j.generic;

import java.util.*;

/**
 * SimpleArray
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {
    private T[] objects;
    private int currentSize;
    private int checkingForSet;

    public SimpleArray(T[] objects) {
        this.objects = objects;
        this.currentSize = objects.length;
        this.checkingForSet = objects.length;
    }

    public boolean add(T model) {
        boolean result = false;
        if (currentSize < objects.length) {
            objects[currentSize] = model;
            result = true;
            if (checkingForSet > currentSize) {
                checkingForSet = currentSize;
            }
            currentSize++;

        }
        return result;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, objects.length);

        if (currentSize > index && index >= checkingForSet) {
            this.objects[index] = model;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void remove(int index) {
        Objects.checkIndex(index, objects.length);
        final int newSize = objects.length - 1;
        if (newSize > index) {
            System.arraycopy(objects, index + 1, objects, index, newSize - index);
        }
        objects[objects.length - 1] = null;
        currentSize--;
    }

    public Object get(int index) {
        Objects.checkIndex(index, objects.length);
        return objects[index];
    }

    public Object size() {
        return objects.length;
    }

/*    public static void main(String[] args) {
        SimpleArray<String> sa = new SimpleArray<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        sa.remove(9);
        sa.remove(0);
        sa.add("666");
        sa.add("666");
        sa.set(6, "777");
        sa.set(9, "7777");
        for (int i = 0; i < 10; i++) {
            System.out.println(sa);
        }
    }*/

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < objects.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return objects[currentIndex++];
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public String toString() {
        return "SimpleArray{" + "objects=" + Arrays.toString(objects) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return Arrays.equals(objects, that.objects);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(objects);
    }
}
