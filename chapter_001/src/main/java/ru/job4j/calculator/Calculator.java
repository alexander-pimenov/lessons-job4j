package ru.job4j.calculator;
/**
 * Calculator.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Calculator {
    private double result;
    /**
     * sum.
     * @param fist - nummber + d.
     * @param second - nummber + d.
     */
    public void add(double fist, double second) {
        this.result = fist + second;
    }
    /**
     * subtract.
     * @param fist - nummber + d.
     * @param second - nummber + d.
     */
    public void subtract(double fist, double second) {
        this.result = fist - second;
    }
    /**
     * div.
     * @param fist - nummber + d.
     * @param second - nummber + d.
     */
    public void div(double fist, double second) {
        this.result = fist / second;
    }
    /**
     * multiply.
     * @param fist - nummber + d.
     * @param second - nummber + d.
     */
    public void multiply(double fist, double second) {
        this.result = fist * second;
    }
    /**
     * Method getResults.
     * @return result - nummber + d.
     */
    public double getResult() {
        return this.result;
    }
}