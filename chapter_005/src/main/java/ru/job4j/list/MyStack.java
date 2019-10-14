package ru.job4j.list;

import java.util.Iterator;

/**
 * MyStack
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MyStack<E> implements Iterable<E> {

    private int size = 0;
    private MyLinkedList<E> innerContaner;

    public MyStack() {
        this.innerContaner = new MyLinkedList<>();
    }


    public E poll() {
        E result = innerContaner.removeFirst();
        size--;
        return result;

    }

    public void push(E value) {
        innerContaner.add(value);
        size++;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return innerContaner.iterator();
    }
}
