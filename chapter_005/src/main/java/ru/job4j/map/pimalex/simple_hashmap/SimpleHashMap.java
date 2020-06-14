package ru.job4j.map.pimalex.simple_hashmap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<V, K> implements SimpleMap<K, V> {

    public static void main(String[] args) {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        System.out.println(strings.hashTable.length);
        System.out.println("=======");

        for (int i = 0; i < 25; i++) {
            int hash = strings.hash("" + i);
            System.out.println(hash);
            int indexFor = strings.indexFor(hash);
            System.out.println(indexFor);

            strings.insert(String.format("%s", i), String.format("value_%s", i));
            System.out.println(strings);
            System.out.println("----");

        }
//        System.out.println("=======");
//        System.out.println(strings.size());
//        System.out.println(strings.hashTable.length);
//        System.out.println("=======");
//
//        Iterator<Node<String, String>> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            Node<String, String> next = iterator.next();
//            System.out.println(next);
//        }
//        System.out.println("=======");
//        System.out.println(strings);

    }

    private Node<K, V>[] hashTable;
    static final int INITIAL_CAPACITY = 16;
    static final float LOAD_FACTOR = 0.75f;
    private int size = 0;
    /*Рассчитывается по формуле (capacity * loadFactor);*/
    private float threshold;

    public SimpleHashMap() {
        hashTable = new Node[INITIAL_CAPACITY];
        //threshold = hashTable.length * 0.75f;
    }

    public SimpleHashMap(int capacity) {
        //size = 0;
        this.hashTable = new Node[capacity];
        //this.threshold = hashTable.length * LOAD_FACTOR;
    }

    public int capacity() {
        return hashTable.length;
    }

//    private int hash(K key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }

    public int hash(K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        //return (key == null) ? 0 : (Math.abs(hash) % hashTable.length);
        return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);

    }

    /**
     * Определяет индекс в массиве по хэшу ключа
     *
     * @param hash значение хэша ключа
     * @return индекс в массиве
     */
    public int indexFor(int hash) {
        return hash & (hashTable.length - 1);
    }

    /**
     * Вставка объекта в массив хэш-таблицы
     *
     * @param key   ключ объекта
     * @param value значение объекта
     * @return true если объект вставлен или false если там уже есть объект
     */
    @Override
    public boolean insert(K key, V value) {
        boolean result = false;
        if (size + 1 >= (this.hashTable.length * LOAD_FACTOR)) {
//        if (size + 1 >= threshold) {
//            threshold *= 2;
            arrayDoubling();
        }
        int hash = hash(key);
        int index = indexFor(hash);
        if (hashTable[index] == null) {
            hashTable[index] = new Node<>(hash, key, value);
            result = true;
            size++;
        }
        return result;
    }

    /**
     * Метод изменения размера массива, если порог превышен.
     * Массив увеличивается вдвое.
     */
    private void arrayDoubling() {
        Node<K, V>[] oldHashTable = hashTable;
        int oldCapacity = oldHashTable.length;
        hashTable = new Node[oldCapacity * 2];
        int newCapacity = hashTable.length;
        int i = 0;

//        int newSize = this.hashTable.length<<1;
//        Node<K,V>[] newTable = new Node[newSize];
//        for (Node<K,V> value : this.hashTable) {
//            K key = value.getKey();
//            int hash = hash(key);
//            int index = indexFor(hash);
//            newTable[index]  = value;
//        }
//        this.hashTable = newTable;

        for (int index = 0; index < oldCapacity; index++) {
            if (oldHashTable[index] != null) {
                K key = oldHashTable[index].getKey();
                int hash = hash(key);
                int indexNew = indexFor(hash);
//                i = oldHashTable[index].hash & (newCapacity - 1);
//                hashTable[i] = oldHashTable[index];
                hashTable[indexNew] = oldHashTable[index];
            }
        }
    }

    @Override
    public V get(K key) {

        int hash = hash(key);
        int index = indexFor(hash);
        if (hashTable[index] == null) {
            return null;
        } else {
            V value = null;
            Node<K, V> kvNode = hashTable[index];
            if (kvNode.key.equals(key)) {
                value = kvNode.value;
            }
            return value;
        }

//        return this.hashTable[index] == null ? null : hashTable[index].getValue();
    }

    @Override
    public boolean delete(K key) {
        boolean result = false;
        int hash = hash(key);
        int index = indexFor(hash);
        if (hashTable[index] != null) {
            hashTable[index] = null;
            result = true;
            size--;
        }
        return result;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "SimpleHashMap{" +
                "hashTable=" + Arrays.toString(hashTable) +
                '}';
    }

    public Iterator<Node<K, V>> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Node<K, V>> {

        private int index = 0;
        private int position = 0;

        @Override
        public boolean hasNext() {
            return index < SimpleHashMap.this.size;
        }

        //@SuppressWarnings("unchecked")
        @Override
        public Node<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (hashTable[position] == null) {
                position++;
            }
            Node<K, V> node = hashTable[position];
            position++;
            index++;
            return node;
        }
    }


//    @Override
//    public Iterator<V> iterator() {
//        return new Iterator<V>() {
//            int index = 0;
//            int position = 0;
//
//            @Override
//            public boolean hasNext() {
//                return SimpleHashMap.this.size > index;
//            }
//
//            @Override
//            public V next() {
//                if (!hasNext()) {
//                    throw new NoSuchElementException();
//                }
//                while (hashTable[position] == null) {
//                    position++;
//                }
//                //position++;
//                index++;
//                return SimpleHashMap.this.hashTable[position++].value;
//            }
//        };
//    }

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }
}
