package ru.job4j.converter;
/**
 * Currency Converter
 */
public class Converter {
    public double rateOfEuro = 63.34d;
    public double rateOfDollar = 73.23d;
    /**
     * To convert ruble to euro.
     * @param value - ruble.
     * @return - euro.
     */
    public double rubleToEuro(double value) {
        return value / rateOfEuro;
    }
    /**
     * To convert ruble to dollar.
     * @param value - ruble.
     * @return dollar.
     */
    public double rubleToDollar(double value) {
        return value / rateOfDollar;
    }
    /**
     * To convert euro to ruble.
     * @param value - euro.
     * @return ruble.
     */
    public double euroToRuble(int value) {
        return value * rateOfEuro;
    }
    /**
     * To convert dollar to ruble.
     * @param value - dollar.
     * @return ruble.
     */
    public double dollarToRuble(int value) {
        return value * rateOfDollar;
    }
}