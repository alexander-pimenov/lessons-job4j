package ru.job4j.tree.tree_arhiser;

public class TreeApp {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Node root = new Node(20,
                new Node(7,
                        new Node(4, null, new Node(6)), new Node(9)),
                new Node(35,
                        new Node(31, new Node(28), null),
                        new Node(40, new Node(38), new Node(52))));

        System.out.println("Сумма дерева: " + tree.sumRecursive(root));
        System.out.println("=======");
        System.out.println("Сумма дерева: " + tree.sumDeep(root));
        System.out.println("=======");
        System.out.println("Сумма дерева: " + tree.sumWide(root));
    }
}
