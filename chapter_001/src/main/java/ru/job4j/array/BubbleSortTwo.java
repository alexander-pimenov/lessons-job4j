package ru.job4j.array;

import java.util.ArrayList;

public class BubbleSortTwo {
    private ArrayList<Long> array;   //ссылка на контейнер

    public BubbleSortTwo() {    //конструктор класса
        this.array = new ArrayList<>();          //создание контейнера
    }

    public void into(long value) {   //метод вставки элемента в контейнер
        array.add(value);
    }

    public void printer() {          //метод вывода массива в консоль
        for (Long value : array) {
            System.out.println(value);   //вывести в консоль
        }
        System.out.println("----Окончание вывода массива----");
    }

    private int size() {           //метод возврашает длину контейнера
        return array.size();
    }

    private void toSwap(int first, int second) { //метод меняет местами пару чисел контейнера
        long dummy = array.get(first);      //во временную переменную помещаем первый элемент
        array.set(first, array.get(second));       //на место первого ставим второй элемент
        array.set(second, dummy);          //вместо второго элемента пишем первый из временной памяти
    }

    public void bubbleSorter() {     //МЕТОД ПУЗЫРЬКОВОЙ СОРТИРОВКИ
        int size = this.size();
        for (int out = size - 1; out >= 1; out--) {  //Внешний цикл
            for (int in = 0; in < out; in++) {       //Внутренний цикл
                if (array.get(in) > array.get(in + 1)) {               //Если порядок элементов нарушен
                    toSwap(in, in + 1);             //вызвать метод, меняющий местами
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSortTwo array = new BubbleSortTwo(); //Создаем контейнер

        array.into(163);       //заполняем массив
        array.into(300);
        array.into(184);
        array.into(191);
        array.into(174);

        array.printer();            //выводим элементы до сортировки
        array.bubbleSorter();       //ИСПОЛЬЗУЕМ ПУЗЫРЬКОВУЮ СОРТИРОВКУ
        array.printer();            //снова выводим отсортированный йсписок
    }

}
