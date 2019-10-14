package ru.job4j.array;
/**
 * FindLoop
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FindLoop {
    /**
     * Find a index.
     * @param data - array.
     * @param number - number.
     * @return index of number or -1.
     */
    public int indexOf(int[] data, int number) {
        int rst = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == number) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}
