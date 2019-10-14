package ru.job4j.strategy;

/**
 * Square class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++");
        pic.append("++++");
        pic.append("++++");
        pic.append("++++");
        return pic.toString();
    }
}
