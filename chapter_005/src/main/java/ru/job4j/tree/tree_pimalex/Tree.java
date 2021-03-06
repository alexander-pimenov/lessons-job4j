package ru.job4j.tree.tree_pimalex;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }


    /**
     * Метод добавляет дочерний элемент к родительскому.
     * Родительский элемент может содержать список дочерних элементов.
     * Если элемент с таким значением уже существует, он вернет false.
     * Если родитель не найден, возвращается false.
     *
     * @param parent родительский узел
     * @param child  потомок родительского узла
     * @return булево значение выполнения операции
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> node = findBy(parent);
            if (node.isPresent()) {
                node.get().add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    /**
     * Определяет бинарное дерево
     * Если дочерних элементов <=2
     * дерево бинароное
     * @return признак бинарного дерева
     */
    public boolean isBinary() {
        boolean rsl = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            List<Node<E>> children = el.getChildren();
            if (children.size() > 2) {
                rsl = false;
                break;
            }
            for (Node<E> child : children) {
                data.offer(child);
            }
        }
        return rsl;
    }
}
