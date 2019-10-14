package ru.job4j.io.consolechat;

import java.io.*;
import java.util.*;

/**
 * ConsoleChat
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleChat {
    private final Input input;
    private static final String LN = System.lineSeparator();
    private static final String STOP = "Stop";
    private static final String END = "End";
    private static final String GO = "Go on";


    public ConsoleChat(Input input) {
        this.input = input;
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat(new UserInput());
        consoleChat.toStartChat();
    }

    private void writeLog(String who, String massage) {
        String log = System.getProperty("java.io.tmpdir") + "/log.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
            bw.write(new Date() + who + massage + LN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void toStartChat() {
        StringJoiner joiner = new StringJoiner(LN);
        joiner.add("Hello Ser! My name is David, I'm personal assistant!");
        joiner.add("Type your request.");
        joiner.add("In order to stop our assistant David type 'Stop' and 'Go on' to continue.Type 'End' to exit.");
        System.out.println(joiner);

            boolean userWantToChatWithBot = true;
            String userMassage;
            String botMassage = "Let's get started!";
            System.out.println(botMassage);
            this.writeLog(" Bot: ", botMassage);
            while (true) {
                userMassage = this.input.ask();
                writeLog(" User: ", userMassage);
                if (this.END.equalsIgnoreCase(userMassage)) {
                    botMassage = "Bye bye!";
                    this.writeLog(" Bot: ", botMassage);
                    break;
                }
                if (this.STOP.equalsIgnoreCase(userMassage)) {
                    userWantToChatWithBot = false;
                    botMassage = "Have a good day, if u want to continue, type 'Go on'.";
                    System.out.println(botMassage);
                    this.writeLog(" Bot: ", botMassage);
                }
                if (userWantToChatWithBot) {
                    botMassage = this.getPhrase();
                    System.out.println(botMassage);
                    this.writeLog(" Bot: ", botMassage);
                }
                if (this.GO.equalsIgnoreCase(userMassage)) {
                    userWantToChatWithBot = true;
                    System.out.println(botMassage);
                    botMassage = "Hello again! My life in serving to you!";
                    this.writeLog(" Bot: ", botMassage);
                }
            }
    }

    private String getPhrase() {
        ArrayList<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("phrases.txt"))))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random rnd = new Random();
        return phrases.get(rnd.nextInt(phrases.size()));
    }
}
