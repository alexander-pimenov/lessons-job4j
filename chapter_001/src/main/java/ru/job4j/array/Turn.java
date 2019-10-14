package ru.job4j.array;
/**
 * Turn
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     * Turn out the array
     * @param array - array.
     * @return array.
     */
    public int[] turn(int[] array) {
        for (int i = 0, j = (array.length - 1); i < array.length / 2; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }
}

