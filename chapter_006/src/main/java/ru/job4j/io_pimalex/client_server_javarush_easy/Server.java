package ru.job4j.io_pimalex.client_server_javarush_easy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static PrintWriter out; // поток записи в сокет
//    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!"); // хорошо бы серверу объявить о своем запуске
                while (true) {
                    clientSocket = server.accept(); // accept() будет ждать пока кто-нибудь не захочет подключиться
                    Thread.sleep(100);
                    try {
                        // установив связь и воссоздав сокет для общения с клиентом можно перейти
                        // к созданию потоков ввода/вывода.
                        // теперь мы можем принимать сообщения
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        // и отправлять
                        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));

                        String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                        System.out.println(word);
                        if (word.contains("stop")) {
                            break;
                        }

                        // не долго думая отвечает клиенту
                        out.println("HTTP/1.1 200 OK\r\n\r\r");
                        out.println("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
//                    out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
//                    out.flush(); // выталкиваем все из буфера
                    } finally { // в любом случае сокет будет закрыт
                        clientSocket.close();
                        // потоки тоже хорошо бы закрыть
                        in.close();
                        out.close();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}