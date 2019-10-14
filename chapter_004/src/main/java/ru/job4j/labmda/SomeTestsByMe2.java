package ru.job4j.labmda;

import java.util.function.Function;

public class SomeTestsByMe2 {
        public String workWithString(Function<String, String> fun, String str) {
            return fun.apply(str);
        }
}
