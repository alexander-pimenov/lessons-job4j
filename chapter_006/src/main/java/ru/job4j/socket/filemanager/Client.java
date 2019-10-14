package ru.job4j.socket.filemanager;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Client {

    public void start() {

        final String propertiesFileName = "app.properties";

        final Settings settings = new Settings(propertiesFileName);
        final String ip = settings.getValue("ip");
        final int port = Integer.valueOf(settings.getValue("port"));
        File storeDir = new File(settings.getValue("storeDir"));

        try (Socket socket = new Socket(InetAddress.getByName(ip), port)) {
            System.out.println("client connected ...");
            Menu menu = new Menu();
            menu.menuNavigate(new Actions(socket, storeDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start client. Only after starting Server.
     * @param args - args.
     */
    public static void main(String[] args) {
        new Client().start();
    }
}