package ru.job4j.list;

import java.util.*;

/**
 * MyArrayList
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MyArrayList<E> implements MyList<E> {

    /**
     * Inner container.
     */
    private  Object[] container;
    private int i;
    private int modCount;

    /**
     * The size of the MyArrayList (the number of elements it contains).
     *
     * @serial
     */
    private int size = 0;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;


    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.container = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.container = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public MyArrayList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Extending the array.
     */
    private void extendArr() {
        if (container.length == size) {
            container = Arrays.copyOf(container, this.size() * 2);
        }
    }
    @Override
    public boolean add(E model) {
        extendArr();
        container[i++] = model;
        modCount++;
        this.size++;
        return true;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, container.length);
        return (E) container[index];
    }

    /**
     * Get size of the array.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public String toString() {
        return "MyArrayList{" + "container=" + Arrays.toString(container) + '}';
    }

    private class Itr implements Iterator<E> {

        private int expectedModCount = modCount;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < MyArrayList.this.size();
        }

        @Override
        public E next() {
            checkModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return (E) MyArrayList.this.container[currentIndex++];
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void checkModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Arrays.equals(container, that.container);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(container);
        return result;
    }
}
