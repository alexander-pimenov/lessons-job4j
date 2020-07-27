package ru.job4j.io_pimalex.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSock = new ServerSocket(62666);
            for (; ; ) {
                Socket socket = serverSock.accept();
                System.out.println("From IP:" + socket.getInetAddress());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("\t:" + line);
                }
                writer.println("Testing123");
                writer.close();
                reader.close();
                System.out.println("Server existing");
                serverSock.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
