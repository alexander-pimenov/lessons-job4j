package ru.job4j.tutorial.yandex_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Задача из тестов Яндекса.
 * Вы обслуживаете сайт и отслеживаете возникающие проблемы. Клиент получил ошибку после того,
 * как попытался добавить свой пост в систему. Вы хотите понять на каком из серверов эта ошибка произошла.
 * <p>
 * Есть n серверов, на i-й из них приходится ai процентов запросов, из которых bi процентов завершаются с
 * ошибкой. Для каждого сервера найдите вероятность того, что ошибка произошла именно на нём.
 * <p>
 * Формат ввода
 * В первой строке входного файла содержится одно целое число n (1 ≤n≤ 100) — количество серверов.
 * <p>
 * В каждой из следующих n строк содержится два целых числа ai bi (0 ≤ ai, bi ≤ 100) — вероятность того что
 * запрос пойдет на i-й сервер в процентах и вероятность того что на i-м сервере случится ошибка в процентах.
 * <p>
 * Гарантируется, что сумма всех ai равна 100 и ошибка в системе может произойти.
 * <p>
 * Формат вывода
 * Выведите n строк. В каждой строке должно находиться одно вещественное число (0 ≤ pi ≤ 1) — вероятность,
 * что ошибка произошла на соответствующем сервере.
 * <p>
 * Абсолютная или относительная погрешность каждого из ответов не должна превышать 10-9.
 * <p>
 * Алгоритм работы предельно прост:
 * считываем a, b для n случаев, перемножаем a и b, и сохраняем результат умножения в arr[i], также
 * суммируем результат в переменную total, чтобы посчитать чему равно 100%.
 * Для вывода нужно просто разделить arr[i] на 100%
 */


public class Main {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);//для форматирования вывода с точкой (0.3333, вместо 0,333)
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//Buffered Reader это же что и Scanner, но работает во много раз быстрее, и считывает всю строку целиком
        StringTokenizer tk;//используется для разделения целой строки на переменные


        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        int total = 0;
        for (int i = 0; i < n; ++i) {
            line = br.readLine();
            tk = new StringTokenizer(line);

            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());
            arr[i] = (a * b);
            total += arr[i];
        }

        for (int i = 0; i < n; ++i) {
            System.out.printf("%.12f\n", (double) arr[i] / total);
        }

    }
}

