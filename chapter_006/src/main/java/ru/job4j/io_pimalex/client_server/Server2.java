package ru.job4j.io_pimalex.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 implements Runnable {
    private Socket client;

    public Server2() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(62666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            String message;
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (!(message = reader.readLine()).isEmpty()) {
                System.out.println("Message from client: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client disconnected");
        }
    }
}
