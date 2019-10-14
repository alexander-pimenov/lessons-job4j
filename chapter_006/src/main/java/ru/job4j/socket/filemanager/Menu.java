package ru.job4j.socket.filemanager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Menu
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Menu implements IMenu {

    private static final String EXIT = "EXIT";
    private static final String SHOW = "SHOW";
    private static final String GO = "/";
    private static final String HOME = "HOME";
    private static final String DOWNLOAD = "DOWNLOAD";
    private static final String UPLOAD = "UPLOAD";

    private final boolean usingForServer;
    private DataInputStream dataInputStream;
    private Scanner console;

    /**
     * Constructor for server.
     * @param socket - socket server.
     * @throws IOException exception.
     */
    public Menu(Socket socket) throws IOException {
        this.usingForServer = true;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
    }

    /**
     * Constructor for client.
     */
    public Menu() {
        this.usingForServer = false;
        this.console = new Scanner(System.in);
    }

    @Override
    public void menuNavigate(IActions actions) {
        boolean userWantsContinue = true;
        String select = null;

        try {
            while (userWantsContinue) {
                if (this.usingForServer) {
                    select = this.dataInputStream.readUTF();
                    System.out.println(select);
                } else {
                    select = this.console.nextLine();
                }

                if (select != null) {
                    //select = select.toUpperCase();

                    if (this.EXIT.equals(select)) {
                        userWantsContinue = false;
                        if (this.usingForServer) {
                            actions.exit();
                        } else {
                            actions.sendMessage(select);
                            actions.exit();
                        }

                    } else if (SHOW.equals(select)) {
                        if (this.usingForServer) {
                            actions.show();
                        } else {
                            actions.sendMessage(select);
                            actions.readMessage();
                        }

                    } else if (select.startsWith(GO)) {
                        if (this.usingForServer) {
                            actions.goToDirectory(select);
                        } else {
                            actions.sendMessage(select);
                            actions.readMessage();
                        }

                    } else if (HOME.equals(select)) {
                        if (this.usingForServer) {
                            actions.toHomeDir();
                        } else {
                            actions.sendMessage(select);
                            actions.readMessage();
                        }

                    } else if (select.startsWith(DOWNLOAD)) {
                        String fileName = select.substring(DOWNLOAD.length() + 1); //todo check out that user put the whole command DOWNLOAD nameFile.*
                        if (this.usingForServer) {
                            actions.upload(fileName);
                        } else {
                            actions.sendMessage(select);
                            actions.download(fileName);
                        }

                    } else if (select.startsWith(UPLOAD)) {
                        String fileName = select.substring(UPLOAD.length() + 1); //todo check out that user put the whole command UPLOAD nameFile.*
                        if (this.usingForServer) {
                            actions.download(fileName);
                        } else {
                            actions.sendMessage(select);
                            actions.upload(fileName);
                        }
                    } else {
                        actions.unknownCommand();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
