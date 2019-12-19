package ru.job4j.array.bubble_sort;

/**
 * Рассмотрим программу сортировки пузырьком на Java.
 * <p>
 * Внешний цикл for отвечает за номер прохода, а внутренний - за перебор элементов
 * в одном проходе. Обмен значений производится с помощью временной переменной tmp.
 * Во внутреннем цикле перебираем значения начиная с последнего (array.length - 1)
 * и в каждом следующем проходе уменьшаем количество просмотренных элементов (j > i).
 */

class BubbleSorter {
    static void sort(int[] array) {
        //i - номер прохода
        //for (int i = 0; i < array.length - 1; i++) {
        for (int i = array.length - 1; i >= 0; i--) {
            //внутренний цикл прохода
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}
