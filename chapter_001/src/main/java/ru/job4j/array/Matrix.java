package ru.job4j.array;
/**
 * Matrix
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Matrix {
    /**
     * Create multiplication table.
     * @param size - number, the size both sides.
     * @return multiplication table.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int out = 0; out < table.length; out++) {
            for (int inner = 0; inner < table.length; inner++) {
                table[out][inner] = (out + 1) * (inner + 1);
            }
        }
        return table;
    }
}
