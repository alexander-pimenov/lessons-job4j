package ru.job4j.io_pimalex.socket;

import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*Перейдите в http://127.0.0.1:9000/ из браузера или
 http://localhost:9000/ вы начнете "разговор" с сервером.*/
/*В браузере Chrome ответ выводится очень долго, "нет соединения",
 но через cUrl и Internet Explorer всё работает отлично.*/

public class EchoServer3 {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) { //Создав объект типа  ServerSocket необходимо выяснить, что с сервером кто-то хочет соединиться.
            System.out.println("Сервер запущен!");
            boolean flag = false;
            while (!flag) {
                /*server.accept() - ожидает, когда к нему обратиться клиент.
                 * Здесь программа переходит в режим ожидания.
                 * Искомый ждёт пока кто-либо не захочет подсоединится к нему,
                 * и когда это происходит возвращает объект типа Socket, то есть
                 * воссозданный клиентский сокет. И вот когда сокет клиента создан
                 * на стороне сервера, можно начинать двухстороннее общение. */
                Socket socket = server.accept();
                Thread.sleep(100);
                try (BufferedWriter out =
                             new BufferedWriter(
                                     new OutputStreamWriter(
                                             socket.getOutputStream()));
                     BufferedReader in =
                             new BufferedReader(
                                     new InputStreamReader(
                                             socket.getInputStream()))) {
                    String str = in.readLine();
                    if (!str.isEmpty()) {
                        String[] line = str.split("\\s");
                        int index = line[1].lastIndexOf('=');
//                        System.out.println("номер символа '=' =>" + index);
                        String argument = line[1].substring(index + 1);
//                        System.out.println("Аргумент для ответа " + argument);
                        String answer;
                        if ("Hello".equalsIgnoreCase(argument)) {
                            answer = "Hello, dear friend.";
                        } else if ("Exit".equalsIgnoreCase(argument)) {
                            answer = "Exit. Server shut down.";
                            flag = true;
                        } else {
                            answer = argument;
                        }
                        /*Вывод информации о пришедшем сообщении, о request от Клиента.*/
                        System.out.println("=====start of request information=====");
                        while (!str.isEmpty()) {
                            System.out.println(str);
                            str = in.readLine();
                        }
                        System.out.println("=====end of request information=====");
                        /*Для того, чтобы браузер выводил ответ сервера,
                         * оформим ответ в соответствии с правилами ответа HTTP протокола.
                         */
                        String response =
                                "HTTP/1.1 200 OK\r\n"
                                        + "Content-Type: text/html\r\n"
                                        + "\r\n" //пустая строка
                                        + "<html>\r\n"
                                        + "<head>\r\n"
                                        + "</head>\r\n"
                                        + "<body>\r\n"
                                        + "<h2>Hello from Server.</h2>\r\n"
                                        + "<p>" + answer + "</p>\r\n"
                                        + "<p> #This is the server response# </p>\r\n"
                                        + "</body>\r\n"
                                        + "</html>\r\n";
                        out.write(response); //Отправляет ответ(response) Клиенту
                        out.flush();

//                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
//                        out.write((answer + "\r\n").getBytes());
//                        System.out.println(str);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        try (ServerSocket server = new ServerSocket(9000)) {
//            while (true) {
//                Socket socket = server.accept();
//                try (OutputStream out = socket.getOutputStream();
//                     BufferedReader in = new BufferedReader(
//                             new InputStreamReader(
//                                     socket.getInputStream()
//                             ))) {
//                    String str = in.readLine();
//                    if (str.contains("Exit")) {
//                        server.close();
//                        System.out.println("Server closed");
//                        break;
//                    } else if (str.contains("Hello")) {
//                        out.write("Hello, dear friend\n".getBytes());
//                    } else {
//        out.write("HTTP/1.1 200 OK\r\n".getBytes());

//                        out.write(((str.substring((str.indexOf("=") + 1))).substring(0, (str.indexOf(" ") + 1)) + "\n").getBytes());
//                    }
//                    System.out.println(str);
//                }
//            }
//        }

//        try (ServerSocket server = new ServerSocket(9000)) {
//            while (!server.isClosed()) {
////            while (true) { //вариат-2
//                Socket socket = server.accept();
////                System.out.println("===server.isClosed()==>" + server.isClosed());
//                try (OutputStream out = socket.getOutputStream();
//                        /*В программе читается весь входной поток.*/
//                     BufferedReader in = new BufferedReader(
//                             new InputStreamReader(socket.getInputStream()))) {
//                    String str = in.readLine();
//                    System.out.println(str);
////                    String answer = str.substring(str.lastIndexOf("=") + 1, str.indexOf("HTTP")) + "\r\n";
//
//                    if (str.startsWith("GET") && (str.contains("msg=Hello"))) {
//                        out.write("\nHello.\r\n\\".getBytes());
////                        System.out.println(str);
//                    }
//                    if (str.startsWith("GET") && (str.contains("msg=exit") || str.contains("msg=Exit"))) {
//                        out.write("Server close.\r\n\\".getBytes());
////                        System.out.println("Server close.");
////                        break;
//                        server.close();
//                    }
////                    if (str.startsWith("GET") && ((str.contains("msg=Bye")) || str.contains("msg=bye"))) {
////                        out.write("Server close.\r\n".getBytes());
////                        System.out.println("Server close.");
//////                        break; //вариат-2
////                        server.close();
////                    }
//                    else {
//                        /*Вычитывает оставшиеся строчки из потока in и выводит их*/
//                        while (!(str = in.readLine()).isEmpty()) {
//                            System.out.println(str);
//                        }
//                    }
//                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
////                    out.write("Hello, dear friend.".getBytes());
////                        out.write(answer.getBytes());
//                }
////                System.out.println("===server.isClosed()==>" + server.isClosed());
//            }
//        }

//        try (ServerSocket server = new ServerSocket(9000)) {
//            while (!server.isClosed()) {
//                Socket socket = server.accept();
//                try (OutputStream out = socket.getOutputStream();
//                        /*В программе читается весь входной поток.*/
//                     BufferedReader in = new BufferedReader(
//                             new InputStreamReader(socket.getInputStream()))) {
//                    String str = in.readLine();
//                    while (!(str.isEmpty())) {
//                        if (str.contains("exit")) {
//                            out.write("Server close.\r\n".getBytes());
//                            server.close();
//                        } else if (str.contains("hello")) {
//                            out.write("Hello, dear friend.\r\n".getBytes());
//                            break;
//                        } else {
////                            String substring = str.substring((str.indexOf("=") + 1), str.indexOf("H"));
////                            str = str.substring(str.indexOf("=") + 1);
////                            int x = str.indexOf("HTTP");
////                            str= str.substring(0,x);
////                            out.write((substring + "\r\n").getBytes());
//                            out.write("Возможный ответ \r\n".getBytes());
//                        }
//                    }
//                    /*Вычитывает оставшиеся строчки из потока in и выводит их*/
////                    while (!(str = in.readLine()).isEmpty()) {
////                        System.out.println(str);
////                    }
////                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
//                }
//            }
//        }
    }
}
