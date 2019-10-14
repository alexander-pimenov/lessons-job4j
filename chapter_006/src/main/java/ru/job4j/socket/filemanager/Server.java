package ru.job4j.socket.filemanager;


import java.io.File;
import java.io.IOException;
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

    public void start() {

        final String propertiesFileName = "app.properties";

        Settings settings = new Settings(propertiesFileName);
        int port = Integer.valueOf(settings.getValue("port"));
        File homeDir = new File(settings.getValue("homeDir"));

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();
            System.out.println("server connected ...");
            Menu menu = new Menu(socket);
            menu.menuNavigate(new Actions(socket, homeDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start server.
     * @param args - args.
     */
    public static void main(String[] args) {
        new Server().start();
    }
}