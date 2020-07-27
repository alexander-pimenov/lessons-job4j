package ru.job4j.io_pimalex.client_server;

public class App {
    public static void main(String[] args) {
        Thread t = new Thread(new Server());
        t.setDaemon(true);
        t.start();
        new Client().run();
        System.out.println("Main existing");
    }
}
