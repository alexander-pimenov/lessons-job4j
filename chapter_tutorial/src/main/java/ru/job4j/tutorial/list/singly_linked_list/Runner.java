package ru.job4j.tutorial.list.singly_linked_list;

public class Runner {
    public static void main(String[] args) {

        List list = new List();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        list.print();

//        System.out.println();
//        list.remove();
//        list.print();

        System.out.println();
//        list.removeAt(30);
//        list.print();
//        list.removeAt(50);
//        list.print();
        System.out.println(list.find(20));

    }
}
