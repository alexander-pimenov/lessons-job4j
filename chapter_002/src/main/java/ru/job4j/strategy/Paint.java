package ru.job4j.strategy;

/**
 * Paint class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {

    public Paint() {
    }

    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
