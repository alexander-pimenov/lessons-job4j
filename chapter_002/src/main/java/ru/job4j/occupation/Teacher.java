package ru.job4j.occupation;

/**
 * Teacher class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Teacher extends Profession {
    public Diploma teach(Student student) {
        return new Diploma();
    }
}