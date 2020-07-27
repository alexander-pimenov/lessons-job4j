package ru.job4j.io_pimalex.chat;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Chat {
    private Scanner input = new Scanner(System.in);
    private static final String LN = System.lineSeparator();
    private static final String STOP = "стоп";
    private static final String FINISH = "закончить";
    private static final String CONTINUE = "продолжить";
    private List<String> phrases = new ArrayList<>();
    private Random rnd = new Random();
    private List<Entry> listEntry = new ArrayList<>();

    public static void main(String[] args) {

        Chat chat = new Chat();
//        chat.chatCall();
        chat.doChat();
    }

    /*Метод с использованием вечного цикла while (true)*/
    public void chatCall() {
        StringJoiner joiner = new StringJoiner(LN); //указал разделитель
        joiner.add("Добрый день! Я - Бот, Ваш помощник!");
        joiner.add("Введите Ваш вопрос.");
        joiner.add("<<<<< Чтобы остановить помощника, введите «Стоп» и он замолчит.");
        joiner.add("Введите «Продолжить» и он продолжит общение.");
        joiner.add("Введите «Закончить» для выхода из программы. >>>>>");
        System.out.println(joiner);

        boolean chatWithBot = true;
        String userMessage;
        String botMessage = "Давайте начнем!";
        System.out.println(botMessage);
        writeLog("Bot", botMessage);
        while (true) {
            userMessage = input.nextLine();
            writeLog("User", userMessage);
            if (FINISH.equalsIgnoreCase(userMessage)) {
                botMessage = "До свидания!";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
                break;
            }
            if (STOP.equalsIgnoreCase(userMessage)) {
                chatWithBot = false;
                botMessage = "Я замолкаю. Если вы хотите продолжить, введите «Продолжить».";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
            }
            if (chatWithBot) {
                botMessage = getPhrase();
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
            }
            if (CONTINUE.equalsIgnoreCase(userMessage)) {
                chatWithBot = true;
                botMessage = "Продолжаем разговор!";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
            }
        }
    }

    public void doChat() {
        initPhrases();
        StringJoiner joiner = new StringJoiner(LN); //указал разделитель
        joiner.add("Добрый день! Я - Бот, Ваш помощник!");
        joiner.add("Введите Ваш вопрос.");
        joiner.add("<<<<< Чтобы остановить помощника, введите «Стоп» и он замолчит.");
        joiner.add("Введите «Продолжить» и он продолжит общение.");
        joiner.add("Введите «Закончить» для выхода из программы. >>>>>");
        System.out.println(joiner);

        boolean chatWithBot = true;
        String userMessage;
        String botMessage = "Давайте начнем!";
        System.out.println(botMessage);
        writeLog("Bot", botMessage);

        do {
            userMessage = input.nextLine();
            writeLog("User", userMessage);
            if (FINISH.equalsIgnoreCase(userMessage)) {
                botMessage = "До свидания!";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
                chatWithBot = false;
            }
            if (STOP.equalsIgnoreCase(userMessage)) {
                chatWithBot = false;
                botMessage = "Я замолкаю. Если вы хотите продолжить, введите «Продолжить».";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
            }
            if (chatWithBot) {
                botMessage = getPhrase();
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
            }
            if (CONTINUE.equalsIgnoreCase(userMessage)) {
                chatWithBot = true;
                botMessage = "Продолжаем разговор!";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);

            }
        } while (!userMessage.equalsIgnoreCase(FINISH));
        writeToLogFile();
    }

    private void writeToLogFile() {
        String log = "log2.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                log, true))) {
            for (Entry entry : listEntry) {
                bw.write(entry.toString());
                bw.write(LN);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeLog(String who, String message) {
        listEntry.add(new Entry(new Date(), who, message));
    }

//    private void writeLog(String who, String message) {
//        String log = "log.txt";
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter
//                (log, true))) {
//            bw.write(new Date() + " " + who + ": " + message + LN);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private List<String> initPhrases() {
        URL text = Chat.class.getResource("/response_phrases.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(
                text.getFile()))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                phrases.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private String getPhrase() {
        return phrases.get(rnd.nextInt(phrases.size()));
    }
}

class Entry {
    private Date date;
    private String who;
    private String phrase;

    public Entry(Date date, String who, String phrase) {
        this.date = date;
        this.who = who;
        this.phrase = phrase;
    }

    @Override
    public String toString() {
        return date
                + ", < " + who + " >"
                + " : '" + phrase + '\'';
    }
}
