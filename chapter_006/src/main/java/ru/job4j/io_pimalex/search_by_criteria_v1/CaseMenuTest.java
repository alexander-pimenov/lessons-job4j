package ru.job4j.io_pimalex.search_by_criteria_v1;

import java.util.Arrays;
import java.util.Scanner;

/************************************************************
 * Классическое консольное меню.
 *
 * private void startPO() {
 *         boolean exit = false;
 *         while (!exit) {
 *             programMenu();
 *             int menuinput = sc.nextInt();
 *             if (menuinput == 1) {
 *                //действие1
 *             } else if (menuinput == 2) {
 *                 //действие2
 *             } else if (menuinput == 3) {
 *                 //действие3
 *             } else if (menuinput == 4) {
 *                 //действие3
 *             } else if (menuinput == 5) {
 *                 exit = true;
 *             }
 *         }
 *     }
 *     private void programMenu() {
 *         System.out.println(
 *                 "Выберете пункт меню:" + "\n"
 *                         + "1. блаблабла" + "\n"
 *                         + "2. бла бла" + "\n"
 *                         + "3. блаблаблабла" + "\n"
 *                         + "4. блабла" + "\n"
 *                         + "5. exit" + "\n"
 *
 *         );
 *     }
 *
 *
 * Или такой вариант реализации меню.
 *
 * public static void main(String[] args) {
 *         Scanner scan = new Scanner(System.in);
 *         int x = 0;
 *         String s ="";
 *
 *         while (!"4".equals(s)){
 *             System.out.println("1. Для создания массива из нечетных числе введите 1");
 *             System.out.println("2. Для создания массива из чисел Фибоначчи введите 2");
 *             System.out.println("3. Для создания двумерного массива введите 3");
 *             System.out.println("4. Для выхода из приложения введите 4");
 *             s = scan.next();
 *
 *             try {
 *                 x = Integer.parseInt(s);
 *             } catch (NumberFormatException e){
 *                 System.out.println("Неверный ввод");
 *             }
 *
 *             switch (x){
 *                 case 1:
 *                     // вызов метода 1
 *                     break;
 *                 case 2:
 *                     // вызов метода 2
 *                     break;
 *                 case 3:
 *                     // вызов метода 3
 *             }
 *         }
 *         System.out.println("До свидания!");
 *     }
 *************************************************************/


public class CaseMenuTest {
    public static void main(String[] args) {
        new CaseMenu(new Scanner(System.in)).start();
    }
}

class CaseMenu {
    private final Scanner scanner;

    public CaseMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printMenu() {
        System.out.println("1. Создание массива из нечетных чисел;");
        System.out.println("2. Создание массива из чисел Фибоначчи;");
        System.out.println("3. Создания двумерного массива;");
        System.out.println("4. Выход из приложения;");
    }

    public void start() {
        if (this.scanner != null) {
            int key;
            do {
                printMenu();
                System.out.print("Введите номер меню: ");
                key = this.scanner.nextInt();
                switch (key) {
                    case 1:
                        int[] array = CreateArrays.createRangeArray(1, 99, 2);
                        CreateArrays.printArray("Массив нечётных чисел [1..99]:\n", array);
                        break;
                    case 2:
                        int[] fibonacci = CreateArrays.createFibonacci(20);
                        CreateArrays.printArray("Массив Фибоначчи [20]:\n", fibonacci);
                        break;
                    case 3:
                        int[][] matrix = CreateArrays.createMatrix(8, 5, 10, 99);
                        CreateArrays.printMatrix("Матрица 8x5 в диапазоне [10..99]:\n", matrix);
                        break;
                    case 4:
                        System.out.println("Завершение программы...");
                        break;
                    default:
                        System.out.println("Вы ввели неверное значение меню...\n");
                }
            } while (key != 4);
        }
    }

}

class CreateArrays {
    public static int[] createRangeArray(int start, int end, int step) {
        int[] array = new int[(end - start) / step + 1];
        int count = 0;
        for (int index = start; index <= end; index += step) {
            array[count++] = index;
        }
        return array;
    }

    public static int[] createFibonacci(int length) {
        int[] array = new int[length];
        for (int index = 0; index < length; index++) {
            if (index < 2) {
                array[index] = 1;
            } else {
                array[index] = array[index - 1] + array[index - 2];
            }
        }
        return array;
    }

    public static int[][] createMatrix(int height, int width, int min, int max) {
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = (int) (Math.random() * (max - min + 1) + min);
            }
        }
        return matrix;
    }

    public static void printArray(String text, int[] array) {
        System.out.println(text + Arrays.toString(array) + "\n");
    }

    public static void printMatrix(String text, int[][] matrix) {
        System.out.println(text);
        for (int[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }
}

