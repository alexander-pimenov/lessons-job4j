package ru.job4j.io_pimalex.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client implements Runnable {
    @Override
    public void run() {
        try {
            URL url = new URL("http://127.0.0.1:62666");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            /*connection.setDoOutput(true); - Устанавливает значение поля {doOutput}
             * для этого {URLConnection} в указанное значение.
             * URL-соединение может быть использовано для ввода и / или вывода.
             * Установите для флага doOutput значение true, если вы собираетесь
             * использовать соединение URL для вывода, и false, если нет.
             * По умолчанию установлено значение false.*/
            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
            connection.connect();

            PrintWriter writer = new PrintWriter(connection.getOutputStream());
            writer.println("Hello");
            writer.flush(); //очищает поток
            System.out.println("flushed");
            int responseCode = connection.getResponseCode();
            writer.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("closed");
            System.out.println("response code = " + responseCode);
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("client read " + line);
            }
            reader.close();
            System.out.println("Client existing");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
