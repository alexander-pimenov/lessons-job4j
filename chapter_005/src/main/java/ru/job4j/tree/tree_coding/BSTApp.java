package ru.job4j.tree.tree_coding;

public class BSTApp {
    public static void main(String[] args) {
        BST a = new BST();
        Node root = null;
        //8, 3, 6, 10, 4, 7, 1, 14, 13

        root = a.insert(root, 8);
        root = a.insert(root, 3);
        root = a.insert(root, 6);
        root = a.insert(root, 10);
        root = a.insert(root, 4);
        root = a.insert(root, 7);
        root = a.insert(root, 1);
        root = a.insert(root, 14);
        root = a.insert(root, 13);

//        root = a.delete(root, 1);
//        root = a.delete(root, 14);
//        root = a.delete(root, 3);

        a.inOrder(root); //1 3 4 6 7 8 10 13 14
        System.out.println("\n===================");
        a.preOrder(root); //8 3 1 6 4 7 10 14 13
        System.out.println("\n===================");
        a.postOrder(root); //1 4 7 6 3 13 14 10 8
        System.out.println("\n===================");

        System.out.println(a.ifNodePresent(null, 3));
        System.out.println(a.ifNodePresent(root, 3));
        System.out.println(a.ifNodePresent(root, 13));
        System.out.println(a.ifNodePresent(root, 30));
        System.out.println("===================");

        Node parentNode = a.getParentNode(root, 12);
        if (parentNode != null) {
            System.out.println("Parent node of val: " + parentNode.data);
        } else {
            System.out.println("Parent doesn't exists");
        }
        System.out.println("===================");


    }
}
