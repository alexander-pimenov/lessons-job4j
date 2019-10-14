package ru.job4j.bank;

import java.util.*;

public class Convert {

    public Convert() {

    }

    //Converts array to list
    List<Integer> makeList(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }


    //Converts list to array
    public int[][] makeArray(List<Integer> list, int rows) {
        Iterator<Integer> iterator = list.iterator();
        int columns = list.size() / rows + (list.size() % rows == 0 ? 0 : 1);


        int[][] array = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }
}