package ru.job4j.map;


import java.util.*;

/**
 * MyHashMap
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MyHashMap<K, V> implements Iterable<MyHashMap.Node> {

    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {

            private int expectedModCount = modCount;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                checkModification();
                boolean result = false;
                for (int i = currentIndex; i < table.length; i++) {
                    if (table[i] != null) {
                        currentIndex = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Node next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return table[currentIndex++];
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

    @Override
    public String toString() {
        return "MyHashMap{" + "table=" + Arrays.toString(table) + '}';
    }

    static class Node<K, V> {
        K key;
        V value;
        int hashCode;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            int result = 1;
            final int prime = 31;
            result = prime * result + ((key == null) ? 0 : key.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }
    }
    /**
     * Inner container.
     */
    private Node[] table;
    private int amount = 0;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOADFACTOR = 0.75;
    private int i;
    private int modCount = 0;

    public MyHashMap() {
        this.table = new Node[DEFAULT_CAPACITY];
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int amount() {
        return amount;
    }

    private final int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    private int setIndex(K key) {
        int beforeIndex = hash(key);
        return beforeIndex & table.length - 1;
    }

    private void checkSize() {
        if (amount > (this.table.length * LOADFACTOR)) {
            int newSize = this.table.length << 1;
            Node[] newTable = new Node[newSize];
            for (Node value : this.table) {
                newTable[setIndex((K) value.key)] = value;
            }
            this.table = newTable;
        }
    }

    boolean insert(K key, V value) {
        boolean result = false;
        checkSize();
        int i = setIndex(key);
        if (table[i] == null) {
            Node<K, V> newMode = new Node<>(key, value);
            table[i] = newMode;
            result = true;
            modCount++;
            amount++;
        }
        return result;
    }

    V get(K key) {
        int i = setIndex(key);
        return (this.table[i] != null) ? (V) this.table[i].value : null;
    }

    boolean delete(K key) {
        int i = setIndex(key);
        boolean result = false;
        if (this.table[i] != null && this.table[i].key.equals(key)) {
            this.table[i] = null;
            result = true;
            modCount++;
            amount--;
        }
        return result;
    }
}
