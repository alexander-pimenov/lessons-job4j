package ru.job4j.isp.belovMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Сlass for defining actions for a tree.
 * Класс определения действий для дерева.
 */
public class TreeAction {
    /**
     * Input tree
     */
    private final Tree tree;

    /**
     * Pairs - name of node and action
     */
    private final Map<String, Action> actions = new HashMap<>();

    public TreeAction(Tree tree) {
        this.tree = tree;
    }
    /**
     * Add action for node in a tree
     * @param name - name of node
     * @param action
     * @return boolean result
     */
    public boolean addAction(String name, Action action) {
        boolean rsl = false;
        Optional<Node> node = this.tree.findBy(name);
        if (node.isPresent()) {
            this.actions.put(name, action);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Perform an action for node in a tree
     * @param name - name of node
     */
    public void select(String name) {
        if (this.actions.containsKey(name)) {
            this.actions.get(name).execute();
        }
    }

}
