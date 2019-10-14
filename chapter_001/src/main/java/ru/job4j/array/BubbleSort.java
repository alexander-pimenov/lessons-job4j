package ru.job4j.array;
/**
 * BubbleSort
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {
    /**
     * BubbleSort a array.
     *
     * @param arr - array.
     * @return sorted array.
     */
    public int[] sort(int[] arr) {
        int length = arr.length;
        for (int out = 0; out < length - 1; out++) {
            for (int inner = 0; inner < length - out - 1; inner++) {
                if (arr[inner] > arr[inner + 1]) {
                    int temp = arr[inner];
                    arr[inner] = arr[inner + 1];
                    arr[inner + 1] = temp;
                }
            }
        }
        return arr;
    }
}