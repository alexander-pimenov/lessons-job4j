package ru.job4j.io_pimalex.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer4 {
    public static void main(String[] args) {
        try (ServerSocket serverSock = new ServerSocket(62666);) {
            for (; ; ) {
                /*server.accept() - ожидает, когда к нему обратиться клиент.
                 * Здесь программа переходит в режим ожидания.
                 * Искомый ждёт пока кто-либо не захочет подсоединится к нему,
                 * и когда это происходит возвращает объект типа Socket, то есть
                 * воссозданный клиентский сокет. И вот когда сокет клиента создан
                 * на стороне сервера, можно начинать двухстороннее общение. */
                Socket socket = serverSock.accept();

                System.out.println("From IP:" + socket.getInetAddress());
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
                    String line;
                    while (!(line = reader.readLine()).isEmpty()) {
//                    while ((line = reader.readLine()) != null) { //эта строка хуже отрабатывает, чем (!(line = reader.readLine()).isEmpty())

                        System.out.println("\t:" + line);
                    }
                    writer.println("HTTP/1.1 200 OK\r\n");
                    writer.println("Testing123");
                    System.out.println("Server existing");
                    serverSock.close(); //после одного обращения клиента к серверу программа закрывается
                    break; //выход из вечного цикла
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
