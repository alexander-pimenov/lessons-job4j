package ru.job4j.loop;
/**
 * Counter
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    /**
     * Sum of even numbers within start and finish.
     * @param start - nummber.
     * @param finish - nummber.
     * @return Sum of even numbers.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum = sum + i;
            }
        }
        return sum;
    }
}
