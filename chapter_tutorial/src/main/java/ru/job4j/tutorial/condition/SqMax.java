package ru.job4j.tutorial.condition;

import com.sun.xml.bind.v2.runtime.reflect.opt.FieldAccessor_Ref;

/**
 * класс вычисляет формулу площади.
 */

public class SqMax {
    public static int max(int first, int second, int third, int forth) {
        int result = forth;
        if (first > second) {
            if (first > third) {
                if (first > forth) {
                    result = first;
                }
            }
        } else if (second > third) {
            if (second > forth) {
                result = first;
            }
        } else if (third > forth) {
            result = second;
        }
        return result;
    }
}
