package ru.job4j.map.pimalex.referencebook;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ReferenceBook<K, V> implements Book<K, V> {

    public static void main(String[] args) {
        ReferenceBook<String, String> strings = new ReferenceBook<>();
//        strings.insert("a", "b");
//        System.out.println(strings.get("a"));
//        strings.insert("a", "c");
//        System.out.println(strings.get("a"));
//        System.out.println(strings.size());
//        strings.insert("w", "c");
//        System.out.println(strings.size());
//        System.out.println(strings.get("a"));
//        strings.delete("a");
//        System.out.println(strings.get("a"));
//        System.out.println(strings.size);
//        strings.delete("w");
//        System.out.println(strings.get("w"));
//        System.out.println(strings.size);

//        strings.insert("1","1");
//        strings.insert("2","2");
//        strings.insert("3","3");
//        strings.insert("4","4");
//        strings.insert("5","5");
//        strings.insert("6","6");
//        strings.insert("7","7");
//        strings.insert("8","8");
//        strings.insert("9","9");
//        strings.insert("10","10");
//        strings.insert("11","11");
//        strings.insert("12","hello");
//        strings.insert("13","13");
//        strings.insert("14","14");
//        strings.insert("15","15");
//        strings.insert("16","16");
//        strings.insert("17","17");
//        strings.insert("18","18");

        //покажем что итератор проходится по значениям
//        for(String s: strings){
//            System.out.println(s);
//        }

//        System.out.println(strings.size());
//        System.out.println(strings.get("12"));
//        System.out.println(strings.hashTable.length);
//        for (int i=1; i<6; i++){
//            strings.delete(String.format("%s", i));
//        }
//        System.out.println(strings.size());
//        System.out.println(strings.hashTable.length);

//        System.out.println(strings.hash("27"));
//        System.out.println(strings.hash("1758"));
//        System.out.println(strings.hash("hello"));
//        //System.out.println(strings.hash(null));
//        System.out.println(strings.hash("111111"));
//        System.out.println("===========================");

        for (int i = 0; i < 60; i++) {
            strings.insert("key" + i, "value" + i);
        }
        System.out.println(strings.size());
        System.out.println(strings.hashTable.length);
        System.out.println(strings.get("key5"));
        System.out.println(strings.get("key25"));
    }

    /*хеш-таблица, реализованная на основе массива, для хранения пар «ключ-значение» в виде узлов.
    Здесь хранятся наши Node;*/
    private Node<K, V>[] hashTable;
    /*количество пар «ключ-значение»;*/
    private int size = 0;
    /*предельное количество элементов, при достижении которого размер хэш-таблицы увеличивается вдвое.
    Рассчитывается по формуле (capacity * loadFactor);*/
    private float threshold;
    /*этот параметр отвечает за то, при какой степени загруженности текущей
    хеш-таблицы необходимо создавать новую хеш-таблицу*/
    final float loadFactor = 0.75f;

    public ReferenceBook() {
        hashTable = new Node[16];
        threshold = hashTable.length * 0.75f; //заполнение таблицы не более, чем на 3/4
    }

    /*метод вставляющий объект в карту
     * это центральное место нашей хэш-мапы*/
    @Override
    public boolean insert(final K key, final V value) {
        if (size + 1 >= threshold) {
            threshold *= 2;
            arrayDoubling();
        }
        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key); //по ключу получаем индекс
        //int index = newNode.hash();

        if (hashTable[index] == null) {
            return simpleAdd(index, newNode);
        }

        List<Node<K, V>> nodelist = hashTable[index].getNodes();

        for (Node<K, V> node : nodelist) {
            if (keyExistButValueNew(node, newNode, value) ||
                    collisonProcessing(node, newNode, nodelist)) {
                return true;
            }
        }
        return false;
    }

    /*обычное добавление элемнта*/
    private boolean simpleAdd(int index, Node<K, V> newNode) {
        hashTable[index] = new Node<K, V>(null, null);
        hashTable[index].getNodes().add(newNode);
        size++;
        return true;
    }

    /*метод перезаписи данных в существующую ноду*/
    private boolean keyExistButValueNew(
            final Node<K, V> nodeFromList,
            final Node<K, V> newNode,
            final V value) {
        if (newNode.getKey().equals(nodeFromList.getKey()) &&
                !newNode.getValue().equals(nodeFromList.getValue())) {
            //перезаписываем значение в ноде по ключу через setValue()
            nodeFromList.setValue(value);
            return true;
        }
        return false;
    }

    /*метод разрешающий коллизии*/
    private boolean collisonProcessing(
            final Node<K, V> nodeFromList,
            final Node<K, V> newNode,
            final List<Node<K, V>> nodes) {
        //проверка на что хеш-коды совпали, а ключ не равен
        //и значения не равны.
        if (newNode.hashCode() == nodeFromList.hashCode() &&
                !Objects.equals(newNode.key, nodeFromList.key) &&
                !Objects.equals(newNode.value, nodeFromList.value)) {
            //если условие выполнено, то добавляем новую ноду в посланный список nodes
            nodes.add(newNode);
            size++;
        }
        return false;
    }

    /*расширение массива*/
    private void arrayDoubling() {
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = new Node[oldHashTable.length * 2];
        size = 0;
        for (Node<K, V> node : oldHashTable) {
            if (node != null) {
                for (Node<K, V> n : node.getNodes()) {
                    //переписываем ноды по новому массиву с помощью
                    //метода insert() описанного выше
                    insert(n.key, n.value);
                }
            }
        }
    }

    @Override
    public boolean delete(final K key) {
        int index = hash(key); //по ключу получаем индекс
        //если в ячейке по индексу null, ничего нет, то возвращаем false
        if (hashTable[index] == null) {
            return false;
        }
        if (hashTable[index].getNodes().size() == 1) {
            hashTable[index] = null;
            size--;
            //hashTable[index].getNodes().remove(0); //эквивалентно верхней строчке
            return true;
        }
        //вытаскиваем весь список нод и проверяем ключи, у каждой ноды этого листа
        List<Node<K, V>> nodeList = hashTable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            if (key.equals(node.getKey())) {
                nodeList.remove(node); //используем метод remove() из LinkedList
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(final K key) {
        int index = hash(key); //по ключу получаем индекс
        //если не проходим нижнюю проверку, то возвращать можем null или выкинуть exception
        if (index < hashTable.length &&
                hashTable[index] != null) {

            //если список нод состоит из одной ноды,т.е. нулевой элемент
            //списка, то берем её значение
            //это делаем для ускорения работы
            if (hashTable[index].getNodes().size() == 1) {
                return hashTable[index].getNodes().get(0).getValue();
            }

            //вытаскиваем по этому индексу лист нод
            List<Node<K, V>> list = hashTable[index].getNodes();
            for (Node<K, V> node : list) {
                //делаем проверку на одинаковые ключи, и если они совпали
                //то возвращаем значение ассоциированное с этим ключом
                if (key.equals(node.getKey())) {
                    return node.getValue();
                }
            }
        }

        return null; //если нет такого элемента то можно или возвращать null
        //или кидать Exception или NullPointerException или ArrayIndexOutOfBoundsException
        //throw new NullPointerException();
        //throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int counterArray = 0; //счетчик идущий по массиву хэш-таблицы
            int valueCounter = 0; //счетчик идущий по количеству значений, сравниваем его с size
            Iterator<Node<K, V>> subIterator = null; //указатель на итератор взятый из листа корневой ноды

            /*наш итератор останавливается в двух случаях:
             * когда valueCounter == size либо когда в subIterator
             * hasNext() возвращает false */
            @Override
            public boolean hasNext() {
                if (valueCounter == size) //если выполняется, то значит прошли по всем значениям
                    return false;

                if (subIterator == null || !subIterator.hasNext()) {
                    if (moveToNextCell()) { //проверка можно ли пройти к следующей ячейке
                        //дошли до не пустой ячейки, вытаскиваем лист нодов nodes
                        //и вызываем его итератор (т.к. используем лист и у него есть свой итератор)
                        subIterator = hashTable[counterArray].getNodes().iterator();
                    } else {
                        return false;
                    }
                }
                return subIterator.hasNext();
            }

            /*метод в котором доходим до не пустой ячейки*/
            private boolean moveToNextCell() {
                counterArray++; //переходим на следующую ячейку массива

                /*здесь в цикле проходимся по всем ячейкам массива хэш-таблицы
                 * пока не дойдем до конца массива и так же проскакиваем
                 * ячейки в которых нет элементов, т.е. содержит null*/
                while (counterArray < hashTable.length && hashTable[counterArray] == null) {
                    counterArray++;
                }
                return counterArray < hashTable.length && hashTable[counterArray] != null;
            }

            @Override
            public V next() {
                valueCounter++;
                /*здесь next() вернули ноду и у ноды вызвали getValue()
                 * и соответственно вернули значение*/
                return subIterator.next().getValue();
            }
        };
    }

//    //для примера
//    public void add(K key, V value) {
//        Node<K, V> newNode = new Node<>(key, value);
//        int index = hash(newNode);
//    }

//    //для примера можно так определять номер ячейки в массиве
//    private int hash(Node<K, V> node) {
//        return node.hashCode() % hashTable.length;
//    }

    /*функция получения номера для ячейки в массиве*/
    private int hash(final K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return (key == null) ? 0 : (Math.abs(hash) % hashTable.length);
        //return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
    }


    private class Node<K, V> {
        //все значения Node будем хранить в списках
        //первый Node будет иметь key=null и value=null (типа заглушки) - корневая Node
        //и потом ссылку на уже реальные Node с реальными key и value
        private List<Node<K, V>> nodes;
        private int hash; //хэш-код узла
        private K key; //объект ключ
        private V value; //объект значение

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node<K, V>>();
        }

        private int hash() {
            return hashCode() % hashTable.length;
        }

        private List<Node<K, V>> getNodes() {
            return nodes;
        }

        private int getHash() {
            return hash;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof Node) {
                Node<K, V> node = (Node) obj;
                return (Objects.equals(key, node.getKey()) &&
                        Objects.equals(value, node.getValue()) ||
                        Objects.equals(hash, node.hashCode()));
            }
//            if (obj == null || getClass() != obj.getClass()) return false;
//            Node<?, ?> node = (Node<?, ?>) obj;
//            return hash == node.hash &&
//                    Objects.equals(nodes, node.nodes) &&
//                    Objects.equals(key, node.key) &&
//                    Objects.equals(value, node.value);
            return false;
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            hash = hash * 17 + value.hashCode();
            return hash;
            //return Objects.hash(nodes, hash, key, value);
        }
    }
}
