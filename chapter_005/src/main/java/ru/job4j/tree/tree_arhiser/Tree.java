package ru.job4j.tree.tree_arhiser;

public class Tree {

    /**
     * Метод вычисляющий сумму элементов дерева
     * с помощью рекурсивного обхода его
     *
     * @param root корневой узел дерева
     * @return сумма всех элементов дерева
     */
    public int sumRecursive(Node root) {
        int sum = root.value;

        /*если левый от корня узел не null, то спускаемся к нему
         * Запоминаем значение корня и вызываем рекурсивно опять sumRecursive
         * Проводим опять проверку на ненулевой левый потомок*/
        if (root.left != null) {
            sum += sumRecursive(root.left);
        }

        /*если правый от корня узел не null, то спускаемся к нему
         * Запоминаем значение корня и вызываем рекурсивно опять sumRecursive
         * Проводим опять проверку на ненулевой правый потомок*/
        if (root.right != null) {
            sum += sumRecursive(root.right);
        }
        return sum;
    }

    /**
     * Итеративный обход дерева в глубину
     * Для него понадобиться стек и цикл
     *
     * @param root корень дерева
     * @return сумма всех узлов дерева
     */
    public int sumDeep(Node root) {
        //Используем стек, который сами реализовали
        SimpleStack<Node> stack = new SimpleStack<>();
        stack.push(root);

        int sum = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();

            //Для того чтобы было нагляднее, как обходятся числа в дереве
            System.out.println(node.value);

            sum = sum + node.value;

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return sum;
    }

    /**
     * Итеративный обход дерева в ширину
     * Для него понадобится Очередь и цикл
     *
     * @param root корень дерева
     * @return сумма всех узлов дерева
     */
    public int sumWide(Node root) {
        //Используем Очередь, которую сами реализовали
        SimpleQueue<Node> queue = new SimpleQueue<>();
        queue.add(root);

        int sum = 0;
        while (!queue.isEmpty()) {
            Node node = queue.remove();

            //Для того чтобы было нагляднее, как обходятся числа в дереве
            System.out.println(node.value);

            sum = sum + node.value;

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return sum;

    }


}
