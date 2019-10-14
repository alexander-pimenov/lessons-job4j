package ru.job4j.labmda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Labmda
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Labmda {
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (double i = start; i != end; i++) {
            result.add(func.apply(i));
        }
        return result;
    }
}
