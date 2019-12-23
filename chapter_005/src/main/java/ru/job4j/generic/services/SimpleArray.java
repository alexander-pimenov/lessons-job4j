package ru.job4j.generic.services;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * В этом задании необходимо сделать универсальную обертку над массивом.
 */

public class SimpleArray<T> implements Iterable<T> {

    private Object[] objects;
    private int cursor = 0;
    private int size = 0;

    /**
     * Конструктор.
     *
     * @param size размер массива.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
        this.size = size;
    }

    /**
     * Добавляет указанный элемент (model) в первую свободную ячейку.
     *
     * @param model
     */
    public void add(T model) {
        sizeCheck(this.cursor);
        this.objects[cursor++] = model;
    }

    /**
     * Заменяет указанным элементом (model) элемент, находящийся по индексу index.
     *
     * @param index индекс, по которому ставим новое значение.
     * @param model новое значение.
     */
    public void set(int index, T model) {
        sizeCheck(index);
        this.objects[index] = model;
    }

    /**
     * Удаляет элемент по указанному индексу, все находящиеся справа элементы при
     * этом необходимо сдвинуть на единицу влево (в середине массива не должно
     * быть пустых ячеек).
     *
     * @param index индекс, по которому удаляем значение.
     */
    public void remove(int index) {
        sizeCheck(index);
        if (index == size - 1) {
            objects[index] = null;
        } else {
            Object[] temp = objects;
            System.arraycopy(temp, index + 1, this.objects, index, cursor - index - 1);
            this.objects[size - 1] = null;
            //cursor--;
        }
    }

    /**
     * Возвращает элемент, расположенный по указанному индексу.
     *
     * @param index
     * @return
     */
    public T get(int index) {
        sizeCheck(index);

// Этот участок закоммнтирован, чтоб мы могли получать null из SimpleArray,
// а не получать NoSuchElementException.
//
//        if (this.objects[index] == null) {
//            throw new NoSuchElementException();
//        }
        return (T) this.objects[index];
    }

    /**
     * Метод проверяет, находится ли index (в аргументе это position) в пределах
     * диапазона массива.
     * Если нет, то выбрасываем исключение IndexOutOfBoundsException (Выход за пределы массива).
     *
     * @param position проверяемый индекс.
     */

    private void sizeCheck(int position) {
        if (position < 0 || position >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int position = 0;

            @Override
            public boolean hasNext() {
                return position < objects.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return (T) objects[position++];
                }
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(objects); //для упрощенного вывода

        //return "SimpleArray{" + "objects=" + Arrays.toString(objects) + '}';

    }
}

    /*public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }*/
    /*    public E remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        @SuppressWarnings("unchecked") E oldValue = (E) es[index];
        fastRemove(es, index);

        return oldValue;
    }
*/
/*      public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }
*/
    /*    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }


        private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }
*/
