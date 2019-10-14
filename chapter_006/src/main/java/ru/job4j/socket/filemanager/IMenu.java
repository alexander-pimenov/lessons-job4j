package ru.job4j.socket.filemanager;

/**
 * IMenu
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface IMenu {

    /**
     * Wake up an action due user request from the menu.
     * @param actions - actions.
     */
    void menuNavigate(IActions actions);
}