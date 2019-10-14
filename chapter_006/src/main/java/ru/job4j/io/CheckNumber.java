package ru.job4j.io;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * CheckNumber
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CheckNumber {
    boolean isNumber(InputStream in) {
        var result = false;
        try {
            byte[] lineOfBytes = in.readAllBytes();
            for (byte value : lineOfBytes) {
                //System.out.println(value);
                if (value < 48 || value > 57) {
                    throw new NumberFormatException();
                }
            }
            int lastSymbol = lineOfBytes[lineOfBytes.length - 1];
            if (lastSymbol % 2 == 0) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            System.out.println("Необходимо ввести число!");
            nfe.printStackTrace();
        }
        return result;
    }
}
