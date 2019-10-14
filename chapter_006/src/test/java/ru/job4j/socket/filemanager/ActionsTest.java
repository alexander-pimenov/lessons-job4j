package ru.job4j.socket.filemanager;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ActionsTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class ActionsTest {
    private File homeDirectory;
    private static final String LS = System.getProperty("line.separator");
    private static final String FS = System.getProperty("file.separator");

    @Before
    public void createFilesForTesting() throws IOException {
        File dirTest = new File(System.getProperty("java.io.tmpdir") + "/MainFM");
        dirTest.mkdir();
        File firstDir = new File(dirTest, "firstDir");
        File secondDir = new File(dirTest, "secondDir");
        File rootFile = new File(dirTest, "rootFile.txt");
        firstDir.mkdir();
        secondDir.mkdir();
        rootFile.createNewFile();
        new File(firstDir, "firstFile.txt").createNewFile();
        new File(secondDir, "secondFile.txt").createNewFile();
        FileWriter writer = new FileWriter(rootFile);
        writer.write("123");
        writer.flush();
        writer.close();
        this.homeDirectory = new File(dirTest.getAbsolutePath());
    }

    @Test
    @Ignore
    public void whenShowThenShow() throws IOException {
        StringBuilder expected = new StringBuilder();
        expected.append("<DIR> firstDir");
        expected.append(LS);
        expected.append("      rootFile.txt");
        expected.append(LS);
        expected.append("<DIR> secondDir");
        expected.append(LS);

        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);

        Actions actions = new Actions(socket, homeDirectory);
        createFilesForTesting();
        actions.show();
        assertThat(out.toString().substring(2), is(expected.toString()));
    }

    @Test
    public void whenGoToThenGoAndShowAfterGoBack() throws IOException {
        String expected = this.homeDirectory.getAbsolutePath() + FS + "firstDir";

        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);

        Actions actions = new Actions(socket, homeDirectory);
        createFilesForTesting();
        actions.goToDirectory("firstDir");
        assertThat(out.toString().substring(2), is(expected));

        out.reset();

        actions.toHomeDir();
        assertThat(out.toString().substring(2), is(this.homeDirectory.getAbsolutePath()));
    }

    @Test
    public void whenUpload() throws IOException {
        byte[] expected = {'1', '2', '3'};

        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);

        Actions actions = new Actions(socket, homeDirectory);
        createFilesForTesting();
        actions.upload("rootFile.txt");
        //at the first place the method /upload() sending fileSize... so cut off that.
        byte[] result = new byte[3];
        System.arraycopy(out.toByteArray(), 8, result, 0, 3);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDownLoad() throws IOException {
        //DownloadedFile.txt
        //long fileSize = 4 and .txt file with "1234".
        final byte[] input = {0, 0, 0, 0, 0, 0, 0, 4, '1', '2', '3', '4'};
        final byte[] expected = {'1', '2', '3', '4'};
        byte[] result = new byte[4];

        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input);
        when(socket.getInputStream()).thenReturn(in);

        Actions actions = new Actions(socket, homeDirectory);
        createFilesForTesting();
        actions.download("DownloadedFile.txt");

        File file = new File(this.homeDirectory, "DownloadedFile.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(result);
        fileInputStream.close();

        assertThat(result, is(expected));
        file.delete();
    }

    @Test
    public void whenSendMessage() throws IOException {
        byte[] expected = {'1', '2', '3'};

        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);

        Actions actions = new Actions(socket, homeDirectory);
        createFilesForTesting();
        actions.sendMessage("123");

        //at the first place the method /sendMessage() sending fileSize... so cut off that.
        byte[] result = new byte[3];
        System.arraycopy(out.toByteArray(), 2, result, 0, 3);

        assertThat(result, is(expected));
    }

    @Test
    public void whenReadMessage() throws IOException {
        final byte[] input = {0, 4, '1', '2', '3', '4'};
        String expected = "1234" + LS;

        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input);
        when(socket.getInputStream()).thenReturn(in);

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setOut(new PrintStream(result));

        Actions actions = new Actions(socket, homeDirectory);
        createFilesForTesting();
        actions.readMessage();

        assertThat(result.toString(), is(expected));
    }
}