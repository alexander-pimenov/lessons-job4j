package ru.job4j.bank;

public class BankException extends RuntimeException {
    public BankException(String msg) {
        super(msg);
    }
}