package ru.job4j.tree.tree_coding;

public class BST {

    /*метод создания новой ноды*/
    public Node createNewNode(int k) {
        Node a = new Node();
        a.data = k;
        a.left = null;
        a.right = null;
        return a;
    }

    /*метод вставки элемента в дерево*/
    public Node insert(Node node, int val) {
        if (node == null) {
            return createNewNode(val);
        }
        if (val < node.data) {
            node.left = insert(node.left, val);
        } else if (val > node.data) {
            node.right = insert(node.right, val);
        }
        return node;
    }

    /*метод удаления элемента из дерева*/
    public Node delete(Node node, int val) {
        if (node == null) {
            return null;
        }
        if (val < node.data) {
            node.left = delete(node.left, val);
        } else if (val > node.data) {
            node.right = delete(node.right, val);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = null;
                temp = node.left == null ? node.right : node.left;

                if (temp == null) {
                    return null;
                } else {
                    return temp;
                }

            } else {
                Node successor = getSuccessor(node);
                node.data = successor.data;
                node.right = delete(node.right, successor.data);
                return node;
            }
        }
        return node;
    }

    public Node getSuccessor(Node node) {
        if (node == null) {
            return null;
        }
        Node temp = node.right;

        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    /*Симметричный обход дерева*/
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    /*Прямой обход дерева*/
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /*Обратный обход дерева*/
    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    /*Метод проверяющий наличие элемента в дереве*/
    public boolean ifNodePresent(Node node, int val) {
        if (node == null) {
            return false;
        }
        boolean isPresent = false;
        while (node != null) {
            if (val < node.data) {
                node = node.left;
            } else if (val > node.data) {
                node = node.right;
            } else {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    /*Метод определяющй родительский узел*/
    public Node getParentNode(Node node, int val) {
        if (node == null) {
            return null;
        }
        Node getParent = null;
        while (node != null) {
            if (val < node.data) {
                getParent = node;
                node = node.left;
            } else if (val > node.data) {
                getParent = node;
                node = node.right;
            } else {
                break;
            }
        }
        return node != null ? getParent : null;
        //return getParent;
    }
}
