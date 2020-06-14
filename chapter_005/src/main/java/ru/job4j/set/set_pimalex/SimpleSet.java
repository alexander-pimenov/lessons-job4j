package ru.job4j.set.set_pimalex;

import ru.job4j.list.array_list_pimalex.DynamicSimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {


    private DynamicSimpleArrayList<E> simpleArrayList;

    public SimpleSet() {
        this.simpleArrayList = new DynamicSimpleArrayList<>();
    }

    /**
     * Метод добавляющий элементы в Set и делающий проверку на наличие дубликатов,
     * и отсутствие дубликата null.
     * Если Set содержит такой же элемент, то он не будет добавлен.
     *
     * @param e объект
     */
    public void add(E e) {
        if (!contains(e)) { //запись в сет, если разные объекты
            this.simpleArrayList.add(e);
        }
    }

    /**
     * Метод проверяющий наличие элементов в Set
     *
     * @param value Добавляемый элемент
     * @return булево значение
     */
//    public boolean contains(E value) {
//        for (E element : simpleArrayList) {
//            //проверка на содержание в коллекции null и что будет
//            //если хотим добавить null
//            if (element == null) {
//                if (value == null) {
//                    return true;
//                }
//            } else {
//                if (element.equals(value))  //проверка, если объекты совпадают
//                    return true;
//            }
//        }
//        return false;
//    }
    public boolean contains(E value) {
        for (E setItem : simpleArrayList) {
            if (setItem == value) {
                return true;
            }
        }

        return false;
    }


    /**
     * Метод возвращающий размер нашего Set
     *
     * @return int
     */
    public int size() {
        return this.simpleArrayList.size();
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArrayList.iterator();
    }


    public static void main(String[] args) {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(null);
        set.add(3);
        set.add(null);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(null);
        set.add(1);
        set.add(null);
        set.add(null);
        set.add(1);
        set.add(3);
        System.out.println("Size: " + set.simpleArrayList.size());

        for (Integer item : set) {
            System.out.println(item);
        }
        System.out.println("====================================");

        SimpleSet<String> setString = new SimpleSet<>();
        setString.add("aaa");
        setString.add("aaa");
        setString.add("qqq");
        setString.add("qqq");
        setString.add("sss");
        setString.add("sss");
        setString.add(null);
        setString.add(null);
        setString.add("qqq");
        setString.add("aaa");
        setString.add("sss");
        setString.add(null);
        System.out.println("Size: " + setString.simpleArrayList.size());
        for (String item : setString) {
            System.out.println(item);
        }


    }
}
