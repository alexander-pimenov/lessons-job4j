package ru.job4j.io_pimalex.client_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/*Это то, что вам нужно сделать, если вы хотите,
 * чтобы клиент отправил сообщение на сервер с помощью URL-соединения*/
public class Client2 {
    public Client2() {
        try {
            URL url = new URL("http://127.0.0.1:62666");
            URLConnection urlConnection = url.openConnection();
            PrintWriter writer = new PrintWriter(urlConnection.getOutputStream());
            writer.println("Hello world!");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
