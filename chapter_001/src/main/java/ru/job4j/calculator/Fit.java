package ru.job4j.calculator;
/**
 * Calculation perfect weight.
 */
public class Fit {
    private int kFamale = 110;
    private int kMale = 100;
    private double k2 = 1.15;
    /**
     * Perfect weight for male.
     * @param growth Growth.
     * @return perfect weight.
     */
    public double maleWeight(double growth) {
        return (growth - kMale) * k2;
    }
    /**
     * Perfect weith for female.
     * @param growth Growth.
     * @return perfect weight.
     */
    public double famaleWeight(double growth) {
        return (growth - kFamale) * k2;
    }
}