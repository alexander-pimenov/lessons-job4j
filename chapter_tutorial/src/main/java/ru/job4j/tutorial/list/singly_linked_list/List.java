package ru.job4j.tutorial.list.singly_linked_list;

public class List {

    //голова нашего списка
    private LinkList head;


    //конструктор - для инициализации нашей головы head как null

    public List() {
        this.head = null;
    }


    //метод проверяющий, пустой наш список или нет
    private boolean isEmpty() {
        return this.head == null;
    }

    //метод добавляющий элементы в начало списка
    public void add(int data) {

        //создаем новый узел
        LinkList temp = new LinkList(data);

        //укажем ссылку на следующий элемент.
        //говорим нашей промежуточной переменной temp, указывая ссылку на next,
        //что будет равняться предыдущему значению, т.е. голове head
        temp.next = head;

        //а сама голова head будет равняться temp
        head = temp;
    }

    //метод удаления элементов из списка из начала списка
    public void remove() {

        //просто переуказываем ссылку head на следующую ссылку
        head = head.next;
    }

    //метод удаления по ключу
    public void removeAt(int key) {
        //нужны две переменные:
        //одна текущая
        LinkList cur = head;
        //вторая переменная предыдущая
        LinkList prev = head;

        //проходим по  списку пока наш ключ не будет найден
        while (cur.data != key) {
            //ставим проверку на пустоту списка
            if (isEmpty()) {
                System.out.println("List is empty.");
                return; //выход из цикла
            } else {
                //путешествуем далее: prev равно текущему элементу,
                //а текущий элемент идет дальше по нашему списку
                prev = cur;
                cur = cur.next;
            }

            //определимся является ли наш текущий элемент головой

            if (cur == head) {
                head = head.next;
            } else {
                prev.next = cur.next;
            }
        }
    }

    //метод поиска индекса элемента
    public int find(int key) {
        LinkList temp = head;

        //промежуточная переменная чтоб вернуть индекс позиции key
        int c = 0;

        while (temp != null) {
            if (temp.data == key) {
                return c;
            }
            temp = temp.next;
            c++;
        }

        //если такого элемента нет вообще
        return -1;
    }

    //метод для вывода результата в консоль
    public void print() {

        //промежуточная переменная равняется нашей голове,
        //т.е. будем проходиться от начала нашего списка и будем идти до самого низа
        LinkList temp = head;

        //пока temp не равен null перебераем наш список
        while (temp != null) {
            System.out.println(temp.data);

            //аналог i++, т.е. увеличение на 1
            temp = temp.next;
        }

    }


}
