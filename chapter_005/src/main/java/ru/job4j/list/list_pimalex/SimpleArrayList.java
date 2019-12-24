package ru.job4j.list.list_pimalex;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Контейнер для данных, базирующийся на односвязном списке.
 * Класс SimpleArrayList.
 */

public class SimpleArrayList<E> implements Iterable<E> { //implements Iterable<E>

    private int size;
    private Node<E> first; //указатель на первый элемент (узел)

    /**
     * Метод вставляет в НАЧАЛО списка данные.
     * (очень похоже на Stack - добавляем в начало.)
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data); //создаем новый элемент
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод реализующий удаления ПЕРВОГО элемента в списке
     * и возвращающий его значение.
     * (очень похоже на Stack - удаляем из начала.)
     */
    public E delete() {
        Node<E> result = this.first;
        if (result == null) {
            throw new NoSuchElementException();
        }
        this.first = first.next;
        this.size--;
        return result.data;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        if (result == null) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначенный для хранения данных.
     * Приватный статический вложенный класс.
     * Он хранит в себе указатель на следующий узел и само значение объекта,
     * которое подставляется при создании узла в конструкторе.
     */
    private static class Node<E> {

        E data; // данные
        Node<E> next; // указатель на следующий элемент

        Node(E data) {
            this.data = data;
        }
    }

    /**
     * Метод получения Итератора.
     */

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }

                int i = cursor;
                E nextElement = get(i);
                cursor++;
                return nextElement;
            }
        };
    }

    /**
     * Метод получения Итератора.
     * Вариант 2.
     */
//    @Override
//    public Iterator<E> iterator() {
//        return new SimpleArrayItr<>();
//    }
//
//    private static class SimpleArrayItr<E> implements Iterator<E> {
//        private int nextIndex;
//        private final Node<E> next;
//
//        SimpleArrayItr() {
//                next =
//        }
//
//
//        @Override
//        public boolean hasNext() {
//            return false;
//        }
//
//        @Override
//        public E next() {
//            return null;
//        }
//    }


}

//    //        @Override
//    //        public Iterator<E> iterator() {
//    //            return new ArrayItr<>(a);
//    //        }
//    //    }
//    //
//    //    private static class ArrayItr<E> implements Iterator<E> {
//    //        private int cursor;
//    //        private final E[] a;
//    //
//    //        ArrayItr(E[] a) {
//    //            this.a = a;
//    //        }
//    //
//    //        @Override
//    //        public boolean hasNext() {
//    //            return cursor < a.length;
//    //        }
//    //
//    //        @Override
//    //        public E next() {
//    //            int i = cursor;
//    //            if (i >= a.length) {
//    //                throw new NoSuchElementException();
//    //            }
//    //            cursor = i + 1;
//    //            return a[i];
//    //        }
//    //    }


    /*Получение итератора по образу LinkedList

        public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }
    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

//AbstractList
protected transient int modCount = 0;

    public Iterator<E> iterator() {
        return new Itr();
    }

    //    private class Itr implements Iterator<E> {
    //        /**
    //         * Index of element to be returned by subsequent call to next.
    //         */
//        int cursor = 0;
//
//        /**
//         * Index of element returned by most recent call to next or
//         * previous.  Reset to -1 if this element is deleted by a call
//         * to remove.
//         */
//        int lastRet = -1;
//
//        /**
//         * The modCount value that the iterator believes that the backing
//         * List should have.  If this expectation is violated, the iterator
//         * has detected concurrent modification.
//         */
//        int expectedModCount = modCount;
//
//        public boolean hasNext() {
//            return cursor != size();
//        }
//
//        public E next() {
//            checkForComodification();
//            try {
//                int i = cursor;
//                E next = get(i);
//                lastRet = i;
//                cursor = i + 1;
//                return next;
//            } catch (IndexOutOfBoundsException e) {
//                checkForComodification();
//                throw new NoSuchElementException();
//            }
//        }
//
//        public void remove() {
//            if (lastRet < 0)
//                throw new IllegalStateException();
//            checkForComodification();
//
//            try {
//                AbstractList.this.remove(lastRet);
//                if (lastRet < cursor)
//                    cursor--;
//                lastRet = -1;
//                expectedModCount = modCount;
//            } catch (IndexOutOfBoundsException e) {
//                throw new ConcurrentModificationException();
//            }
//        }
//
//        final void checkForComodification() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//        }
//    }


//        private void checkPositionIndex(int index) {
//        if (!isPositionIndex(index))
//            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//    }
//


/*        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }


        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

        private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }



    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

            public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

        /**
     * Unlinks non-null first node f.
     */
//private E unlinkFirst(LinkedList.Node<E> f) {
//    // assert f == first && f != null;
//    final E element = f.item;
//    final LinkedList.Node<E> next = f.next;
//    f.item = null;
//    f.next = null; // help GC
//    first = next;
//    if (next == null)
//        last = null;
//    else
//        next.prev = null;
//    size--;
//    modCount++;
//    return element;
//}




