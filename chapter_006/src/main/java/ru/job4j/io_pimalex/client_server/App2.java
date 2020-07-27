package ru.job4j.io_pimalex.client_server;

public class App2 {
    public static void main(String[] args) {
        Thread t = new Thread(new Server2());
        t.setDaemon(true);
        t.start();
        new Client2();
        System.out.println("Main existing");
    }
}
