package ru.job4j.socket.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Server {
    private static final int PORT = 5000;
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask = null;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equalsIgnoreCase(ask) || "hi".equalsIgnoreCase(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if (!"exit".equalsIgnoreCase(ask)) {
                out.println("This is incomprehensible. Would you mind trying again?");
                out.println();
            }
        } while (!("exit".equalsIgnoreCase(ask)));
        out.println(ask);
        in.close();
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(PORT).accept()) {
            System.out.println("Server is up ...");
            Server server = new Server(socket);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}