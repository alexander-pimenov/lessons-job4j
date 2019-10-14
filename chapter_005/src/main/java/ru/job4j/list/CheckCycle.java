package ru.job4j.list;

public class CheckCycle<E> {

    boolean hasCycle(Node<E> first) {
        boolean result = false;
        Node<E> wolf = first;
        Node<E> hare = first;
        if (hare == null || hare.getNext() == null) {
            return result;
        } else {
            while (wolf != null && wolf.getNext() != null) {
                wolf = wolf.getNext().getNext();
                hare = hare.getNext();
                if (hare == wolf) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
