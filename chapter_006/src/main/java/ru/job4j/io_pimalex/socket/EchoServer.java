package ru.job4j.io_pimalex.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс java.net.Socket позволяет получить сообщение и передать его.
 */

public class EchoServer {
    public static void main(String[] args) throws IOException {
        /*Создаем Сервер.
         * ServerSocket создает сервер.
         * 9000 - это порт. По умолчанию адрес будет localhost*/
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                /*server.accept() - ожидает, когда к нему обратиться клиент.
                Программа переходит в режим ожидания.*/
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                        /*В программе читается весь входной поток.*/
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    /*В ответ мы записываем строчку.*/
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println("Работает сервер!");
                        System.out.println(str);
                        System.out.println("************");
                        if(str.contains("bye")){
                            System.out.println("Хочу закрыть сервер!");
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
