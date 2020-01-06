package ru.job4j.tutorial.search_algorithms.binary_search;

import java.util.Arrays;

/**
 * Реализация бинарного поиска, итеративный подход.
 */

public class BinarySearch1 {
    public static void main(String[] args) {
        int[] arr = new int[]{89, 57, 91, 47, 95, 3, 27, 22, 67, 99};

        Arrays.sort(arr);

        int index = binarySearch(arr, 67);
        print(67, index);

    }

    /**
     * Метод для вывода результата.
     */
    public static void print(int elementToSearch, int index) {
        if (index == -1) {
            System.out.println(elementToSearch + " not found.");
        } else {
            System.out.println(elementToSearch + " found at index: " + index);
        }
    }

    /**
     * Бинарный поиск, итеративный подход.
     * @param arr массив, в котором ищем элемент
     * @param elementToSearch элемент, который ищем
     * @return индекс искомого элемента
     */

    public static int binarySearch(int[] arr, int elementToSearch) {
        int firstIndex = 0;
        int lastIndex = arr.length - 1;

        //условие прекращения (элемент не представлен)
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            //если средний элемент - целевой элемент, вернуть его индекс
            if (arr[middleIndex] == elementToSearch) {
                return middleIndex;
            }

            //если средний элемент меньше
            //направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (arr[middleIndex] < elementToSearch) {
                firstIndex = middleIndex + 1;
            }

            // если средний элемент больше
            // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (arr[middleIndex] > elementToSearch) {
                lastIndex = middleIndex - 1;
            }
        }
        return -1;
    }
}
