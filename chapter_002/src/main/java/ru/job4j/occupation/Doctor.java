package ru.job4j.occupation;

/**
 * Doctor class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Doctor extends Profession {
    public Diagnose heal(Patient patient) {
        return new Diagnose();
    }
}