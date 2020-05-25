package ru.job4j.set.set_pimalex;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("first");
        set.add("second");
        set.add("zero");
        set.add("third");
        set.add("ten");
        set.add("twenty one");
        set.add("fife");

        System.out.println(set);
        System.out.println("===========================");

        for (String value : set) {
            System.out.println(value + " имеет hashCode = " + value.hashCode());
        }

        System.out.println("####################################");

        Set<String> setTree = new TreeSet<>();
        setTree.add("first");
        setTree.add("second");
        setTree.add("zero");
        setTree.add("third");
        setTree.add("ten");
        setTree.add("twenty one");
        setTree.add("fife");

        System.out.println(setTree);
        System.out.println("===========================");

        for (String value : setTree) {
            System.out.println(value + " имеет hashCode = " + value.hashCode());
        }
    }
}

//[zero, twenty one, third, ten, fife, first, second]
//===========================
//zero имеет hashCode = 3735208
//twenty one имеет hashCode = 545686455
//third имеет hashCode = 110331239
//ten имеет hashCode = 114717
//fife имеет hashCode = 3142850
//first имеет hashCode = 97440432
//second имеет hashCode = -906279820
//####################################
//[fife, first, second, ten, third, twenty one, zero]
//===========================
//fife имеет hashCode = 3142850
//first имеет hashCode = 97440432
//second имеет hashCode = -906279820
//ten имеет hashCode = 114717
//third имеет hashCode = 110331239
//twenty one имеет hashCode = 545686455
//zero имеет hashCode = 3735208