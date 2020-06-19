package ru.job4j.tree.tree_coding;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    /*Возвращает узел со следующим наивысшим значением после перехода
    delNode к правому потомку, а затем к правому потомку левого потомка
    Successor - приемник*/
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
            System.out.println("Tree is Empty!");
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    /*Прямой обход дерева*/
    public void preOrder(Node node) {
        if (node == null) {
            System.out.println("Tree is Empty!");
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /*Обратный обход дерева
    * Обход в глубину
    * РЕКУРСИВНЫЙ*/
    public void postOrder(Node node) {
        if (node == null) {
            System.out.println("Tree is Empty!");
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    /*Обход дерева в ширину*/
    public void walkWide(Node root) {
        if (root == null) {
            System.out.println("Tree is Empty!");
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data + " ");

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        //System.out.println();
    }

    /*Обход дерева в глубину
    * ИТЕРАТИВНЫЙ
    *с помощью цикла и стека*/
    public void walkDeep(Node root) {
        if (root == null) {
            System.out.println("Tree is Empty!");
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + " ");

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        //System.out.println();
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

    /*Метод определяющий братьев узла*/
    public Node getSiblingNode(Node node, int val) {
        //первая проверка на то что дерево не пустое и
        //вторая проверка если val = корню дерева,
        //у корня дерева нет братьев.
        if (node == null || node.data == val) {
            return null;
        }
        Node parentNode = null;
        //стартуем с корня дерева
        while (node != null) {
            if (val < node.data) {
                parentNode = node;
                node = node.left;
            } else if (val > node.data) {
                parentNode = node;
                node = node.right;
            } else {
                break;
            }
        }
        //Проверяем полученный val и берем его брата
        //первая часть parentNode.left != null - это проверка на NullPointerException
        if (parentNode.left != null && val == parentNode.left.data) {
            return parentNode.right;
        }
        if (parentNode.right != null && val == parentNode.right.data) {
            return parentNode.left;
        }
        return null;
    }

    /*Получить Inorder Parent для заданного значения в дереве двоичного поиска*/
    public Node getInorderParent(Node node, int val) {

        if (node == null) {
            return null;
        }
        Node inorderParent = null;
        while (node != null) {
            if (val < node.data) {
                inorderParent = node;
                node = node.left;
            } else if (val > node.data) {
                node = node.right;
            } else {
                break;
            }
        }
        return node != null ? inorderParent : null;
    }

    /*Получить Inorder наследника заданного значения в дереве двоичного поиска
     * будет выдавать тех кто больше указанного val*/
    public Node inorderSuccessor(Node node, int val) {
        if (node == null) {
            return null;
        }
        Node inorderSuccessor = null;
        while (node != null) {
            if (val < node.data) {
                inorderSuccessor = node;
                node = node.left;
            } else if (val > node.data) {
                node = node.right;
            } else {
                if (node.right != null) {
                    inorderSuccessor = getSuccessor(node);
                }
                break;
            }
        }
        return node != null ? inorderSuccessor : null;
    }

    /*Получить разницу значений четного и нечетного уровня*/
    public int getDifferenceEvenOddLevel(Node node) {
        if (node == null) {
            return 0;
        }
        return node.data - getDifferenceEvenOddLevel(node.left) - getDifferenceEvenOddLevel(node.right);
    }

    /*Получить элемент максимального значения в бинарном дереве поиска*/
    public int getMax(Node node) {
        if (node == null) {
            System.out.println("Tree is Empty!");
            return -1;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }

    /*Получить элемент минимального значения в бинарном дереве поиска*/
    public int getMin(Node node) {
        if (node == null) {
            System.out.println("Tree is Empty!");
            return -1;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    /*Итеративный обход дерева в глубину
     * Понадобится стек и цикл*/
    public int sumDeep(Node root) {
        if (root == null) {
            System.out.println("Tree is Empty!");
            return -1;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        int sum = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + " ");
            sum = sum + node.data;

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
        return sum;
    }

    /*Итеративный обход дерева в ширину
     * Понадобится очередь и цикл*/
    public int sumWide(Node root) {
        if (root == null) {
            System.out.println("Tree is Empty!");
            return -1;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int sum = 0;
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data + " ");
            sum = sum + node.data;

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println();
        return sum;
    }


}
