package ru.job4j.io_pimalex.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {
    public static void main(String[] args) throws IOException {

//        try (ServerSocket server = new ServerSocket(9000)) {
//            while (true) {
//                Socket socket = server.accept();
//                try (OutputStream out = socket.getOutputStream();
//                        /*В программе читается весь входной поток.*/
//                     BufferedReader in = new BufferedReader(
//                             new InputStreamReader(socket.getInputStream()))) {
//                    String str;
//                    if (!(str = in.readLine()).isEmpty()) {
//                        System.out.println(str);
//                        str = str.replace("GET /?msg=", "").replace("HTTP/1.1", "");
//                        if (str.contains("bye")) {
//                            System.out.println("finish");
//                            break;
//                        }
//                        out.write(("HTTP/1.1 200 " + str + "\r\n\\").getBytes());
//                    }
//                }
//            }
//        }

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
//            while (true) { //вариат-2
                Socket socket = server.accept();
//                System.out.println("===server.isClosed()==>" + server.isClosed());
                try (OutputStream out = socket.getOutputStream();
                        /*В программе читается весь входной поток.*/
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    System.out.println(str);
//                    String answer = str.substring(str.lastIndexOf("=") + 1, str.indexOf("HTTP")) + "\r\n";
                    if (str.startsWith("GET") && str.contains("exit")) {
                        break;
                    }
                    if (str.startsWith("GET") && ((str.contains("msg=Bye")) || str.contains("msg=bye"))) {
                        out.write("Server close.\r\n".getBytes());
                        System.out.println("Server close.");
//                        break; //вариат-2
//                        server.close();
                    } else {
                        /*Вычитывает оставшиеся строчки из потока in и выводит их*/
                        while (!(str = in.readLine()).isEmpty()) {
                            System.out.println(str);
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
//                        out.write(answer.getBytes());
                }
//                System.out.println("===server.isClosed()==>" + server.isClosed());
            }
        }
    }
}



