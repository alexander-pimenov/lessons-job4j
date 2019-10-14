package ru.job4j.occupation;

/**
 * Engineer class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {
    public Building build(Building building) {
        return new Building();
    }
}