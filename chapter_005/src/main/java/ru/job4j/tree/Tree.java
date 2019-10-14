package ru.job4j.tree;

import java.util.*;

/**
 * Tree
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements MyTree<E> {

    private final Node<E> root;
    private int modCount;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentNode = this.findBy(parent);
        if (parentNode.isPresent() && this.findBy(child).isEmpty()) {
            parentNode.get().add(new Node<>(child));
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element != null) {
                if (element.leaves().size() > 2) {
                    result = false;
                }
                for (Node<E> child : element.leaves()) {
                    data.offer(child);
                }
            }
        }
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Queue<Node<E>> data = new LinkedList<>();
            private int expectedModCount = modCount;

            {
                this.data.offer(root);
            }

            public int getSizeOfData() {
                return data.size();
            }

            @Override
            public boolean hasNext() {
                checkModification();
                return !data.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    E result = null;
                    Node<E> element = this.data.poll();
                    if (element != null) {
                        for (Node<E> value : element.leaves()) {
                            data.offer(value);
                        }
                        result = element.getValue();
                    }
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
        };
    }
}
