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
        root = a.insert(root, 5);
        root = a.insert(root, 15);
        root = a.insert(root, 2);
        root = a.insert(root, 18);

//        root = a.delete(root, 1);
//        root = a.delete(root, 14);
//        root = a.delete(root, 3);

        a.inOrder(root); //1 2 3 4 5 6 7 8 10 13 14 15 18
        System.out.println("\n===================");
        a.preOrder(root); //8 3 1 2 6 4 5 7 10 14 13 15 18
        System.out.println("\n===================");
        a.postOrder(root); //2 1 5 4 7 6 3 13 18 15 14 10 8
        System.out.println("\n===================");
        a.walkWide(root); //8 3 10 1 6 14 2 4 7 13 15 5 18
        System.out.println("\n===================");
        a.walkDeep(root); //8 3 1 2 6 4 5 7 10 14 13 15 18
        System.out.println("\n===================");


        System.out.println(a.ifNodePresent(null, 3));
        System.out.println(a.ifNodePresent(root, 3));
        System.out.println(a.ifNodePresent(root, 13));
        System.out.println(a.ifNodePresent(root, 30));
        System.out.println(a.ifNodePresent(root, 2));
        System.out.println("===================");

        Node parentNode = a.getParentNode(root, 12);
        if (parentNode != null) {
            System.out.println("Parent node is " + parentNode.data);
        } else {
            System.out.println("Parent doesn't exists");
        }
        System.out.println("===================");

        Node siblingNode = a.getSiblingNode(root, 13);
        if (siblingNode != null) {
            System.out.println("Sibling node is " + siblingNode.data);
        } else {
            System.out.println("Sibling doesn't exists");
        }
        System.out.println("===================");

        Node inorderParent = a.getInorderParent(root, 8);
        if (inorderParent != null) {
            System.out.println("Inorder Parent Node is " + inorderParent.data);
        } else {
            System.out.println("Inorder Parent Node doesn't exists");
        }
        System.out.println("===================");

        Node inorderSuccessor = a.inorderSuccessor(root, 24);
        if (inorderSuccessor != null) {
            System.out.println("Inorder Successor Node is " + inorderSuccessor.data);
        } else {
            System.out.println("Inorder Successor Node doesn't exists");
        }
        System.out.println("===================");

        System.out.println(a.getDifferenceEvenOddLevel(root));
        System.out.println("===================");

        System.out.println("Max value in BST = " + a.getMax(root));
        System.out.println("===================");

        System.out.println("Min value in BST = " + a.getMin(root));
        System.out.println("===================");

        System.out.println("Sum of values of BST (deep) = " + a.sumDeep(root));
        System.out.println("===================");

        System.out.println("Sum of values of BST (wide) = " + a.sumWide(root));
        System.out.println("===================");

    }
}
