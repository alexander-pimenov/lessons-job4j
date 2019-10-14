package ru.job4j.exam;

/**
 * Exam
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Exam {
    public int[] merge(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < result.length; k++) {
            if (i > array1.length - 1) {
                int a = array2[j];
                result[k] = a;
                j++;
            } else if (j > array2.length - 1) {
                int a = array1[i];
                result[k] = a;
                i++;
            } else if (array1[i] < array2[j]) {
                int a = array1[i];
                result[k] = a;
                i++;
            } else {
                int b = array2[j];
                result[k] = b;
                j++;
            }
        }
        return result;
    }
}
