package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * MyLinkedList
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MyLinkedList<E> implements MyList<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    public E removeFirst() {
        if (size > 0) {
            Node<E> result = first;
            first = first.next;
            size--;
            return result.date;
        } else {
            throw new NoSuchElementException();
        }
    }

    public E removeLast() {
        if (size > 0) {
            Node<E> result = last;
            last = last.prev;
            size--;
            return result.date;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean add(E model) {
        addFirst(model);
        return true;
    }

    public void addFirst(E model) {
        Node<E> f = first;
        Node<E> newLink = new Node<>(null, model, f);
        this.first = newLink;
        if (f == null) {
            last = newLink;
        } else {
            f.prev = newLink;
        }
        this.size++;
        this.modCount++;
    }

    public void addLast(E model) {
        Node<E> l = last;
        Node<E> newLink = new Node<>(l, model, null);
        this.last = newLink;
        if (l == null) {
            first = newLink;
        } else {
            l.next = newLink;
        }
        this.size++;
        this.modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        private int expectedModCount = modCount;
        private int currentIndex = 0;
        Node<E> buffer = first;

        @Override
        public boolean hasNext() {
            checkModification();
            return currentIndex < size;
        }

        @Override
        public E next() {
            checkModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                E result = buffer.date;
                buffer = buffer.next;
                currentIndex++;
                return result;
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

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E date, Node<E> next) {
            this.date = date;
            this.next = next;
            this.prev = prev;
        }
    }
}
