package ru.job4j.list;

import java.util.Iterator;

/**
 * MyQueue
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MyQueue<E> implements Iterable<E> {

    private int size = 0;
    private MyStack<E> firstStack;
    private MyStack<E> secondStack;

    public MyQueue() {
        this.firstStack = new MyStack<>();
        this.secondStack = new MyStack<>();
    }


    public E poll() {
        if (secondStack.size() == 0) {
            while (firstStack.size() != 0) {
                secondStack.push(firstStack.poll());
            }
        }
        E result = secondStack.poll();
        size--;
        return result;

    }

    public void push(E value) {
        firstStack.push(value);
        size++;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return firstStack.iterator();
    }
}