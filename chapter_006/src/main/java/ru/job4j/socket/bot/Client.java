package ru.job4j.socket.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Client {
    private static final int PORT = 5000;
    private static final String IP = "127.0.0.1";
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String request = null;
        String response = null;
        do {
            request = console.nextLine();
            out.println(request);
            if (!"exit".equalsIgnoreCase(request)) {
                response = in.readLine();
                while (!response.isEmpty()) {
                    System.out.println(response);
                    response = in.readLine();
                }
            }
        } while (!("exit".equalsIgnoreCase(request)));
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName(IP), PORT)) {
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
