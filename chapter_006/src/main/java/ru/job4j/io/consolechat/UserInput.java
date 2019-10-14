package ru.job4j.io.consolechat;


import java.util.Scanner;

/**
 * UserInput
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserInput implements Input {
    private Scanner input = new Scanner(System.in);

    @Override
    public String ask() {
        //System.out.println(question);
        return input.nextLine();
    }
}
