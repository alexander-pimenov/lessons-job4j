package ru.job4j.socket.filemanager;

/**
 * IActions
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface IActions {

    void exit();

    /**
     * Show files on directory.
     */
    void show();

    /**
     * Go to a directory.
     * @param select - Directory name.
     */
    void goToDirectory(String select);

    /**
     * Back to home directory.
     */
    void toHomeDir();

    /**
     * Sent a file.
     * @param fileName - file name.
     */
    void upload(String fileName);

    /**
     * Take a sent file.
     * @param fileName - file name.
     */
    void download(String fileName);

    /**
     * Wrong command.
     */
    void unknownCommand();

    /**
     * @param message - massage.
     */
    void sendMessage(String message);

    void readMessage();
}