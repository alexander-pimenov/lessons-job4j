package ru.job4j.loop;
/**
 * Factorial
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * Factorial.
     * @param n - nummber.
     * @return Factorial of n.
     */
    public int calc(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
