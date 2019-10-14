package ru.job4j.socket.filemanager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;

/**
 * Actions
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Actions implements IActions {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private final File homeDirectory;
    private File currentDirectory;

    private static final String LS = System.getProperty("line.separator");

    /**
     * Constructor.
     *
     * @param socket        - Socket.
     * @param homeDirectory - Home directory.
     */
    public Actions(Socket socket, File homeDirectory) {
        this.currentDirectory = homeDirectory;
        this.homeDirectory = homeDirectory;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exit() {
        try {
            if (this.dataInputStream != null) {
                this.dataInputStream.close();
            }
            if (this.dataOutputStream != null) {
                this.dataOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        StringBuilder stringBuilder = new StringBuilder();

        File[] files = this.currentDirectory.listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                stringBuilder.append("<DIR> ");
            } else {
                stringBuilder.append("      ");
            }

            stringBuilder.append(file.getName());
            stringBuilder.append(LS);
        }
        try {
            this.dataOutputStream.writeUTF(stringBuilder.toString());
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void goToDirectory(String select) {
        File newDir = new File(this.currentDirectory, select);
        if (newDir.isDirectory()) {
            this.currentDirectory = newDir;
        }
        try {
            this.dataOutputStream.writeUTF(this.currentDirectory.getAbsolutePath());
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toHomeDir() {
        this.currentDirectory = this.homeDirectory;
        try {
            this.dataOutputStream.writeUTF(this.currentDirectory.getAbsolutePath());
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upload(String fileName) {
        File file = new File(this.currentDirectory, fileName);
        try {
            if (file.isFile()) {
                long fileSize = file.length();
                System.out.println("upload file: " + file.getAbsolutePath() + "; size: " + fileSize);
                this.dataOutputStream.writeLong(fileSize);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] buffer = new byte[8192];
                long index = 0;
                int count;
                while (fileSize > index) {
                    count = fileInputStream.read(buffer);
                    this.dataOutputStream.write(buffer, 0, count);
                    index += count;
                }
                this.dataOutputStream.flush();
                fileInputStream.close();
                System.out.println("uploaded");
            } else {
                this.dataOutputStream.writeLong(0);
                this.dataOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void download(String fileName) {
        File file = new File(this.currentDirectory, fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long fileSize = this.dataInputStream.readLong();
            System.out.println("download file: " + file.getAbsolutePath() + "; size: " + fileSize);
            long index = 0;
            int count;
            byte[] buffer = new byte[8192];
            while (fileSize > index) {
                count = dataInputStream.read(buffer);
                fileOutputStream.write(buffer, 0, count);
                index += count;
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("downloaded");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unknownCommand() {
        System.out.println("unknown command");
    }

    @Override
    public void sendMessage(String message) {
        try {
            this.dataOutputStream.writeUTF(message);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readMessage() {
        try {
            String message = this.dataInputStream.readUTF();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}