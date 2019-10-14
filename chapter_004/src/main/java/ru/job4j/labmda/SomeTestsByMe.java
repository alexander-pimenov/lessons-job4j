package ru.job4j.labmda;

/**
 * SomeTestsByMe
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SomeTestsByMe {
    InterfaceByMe reverse = (str) -> {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    };

    InterfaceByMe upCase = (str) -> {
        return str.toUpperCase();
    };

    InterfaceByMe removeWhitespaces = (str) -> {
        String result = "";
        for (int i = 0; i <= str.length() - 1; i++) {
            if (str.charAt(i) != ' ') {
                result += str.charAt(i);
            }
        }
        return result;
    };
}
