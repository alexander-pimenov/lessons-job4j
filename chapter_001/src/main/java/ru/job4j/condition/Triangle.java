package ru.job4j.condition;
/**
 * Calculation area of triangle.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    /**
     * Method of calculating the semi-perimeter along the lengths of the sides.
     *
     * Formula.
     *
     * (ab + ac + bc) / 2
     *
     * @param ab distance within a b
     * @param ac distance within a c
     * @param bc distance within b c
     * @return perimeter.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2d;
    }
    /**
     * Method of calculation area of a triangle.
     *
     * @return Return area of the triangle, if that's exist, but -1.
     */
    public double area() {
        double rsl = -1; // If a triangle isnt exist.
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }
    /**
     * Method checks possibility to make a triangle with the lengths.
     *
     *
     * @param ab Distance between a b.
     * @param ac Distance between a c.
     * @param bc Distance between b c.
     * @return
     */
    private boolean exist(double ab, double ac, double bc) {
        return (ab + ac > bc && ab + bc > ac && ac + bc > ab);
    }
}
