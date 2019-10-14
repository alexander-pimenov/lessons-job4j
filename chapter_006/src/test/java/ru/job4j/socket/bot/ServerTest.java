package ru.job4j.socket.bot;

//import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ServerTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ServerTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenTypeExitThenExit() throws IOException {
        this.testServer("exit", String.format("exit%s", LN));
    }

    @Test
    public void whenTypeHiThenHello() throws IOException {
        this.testServer(String.format(
                "Hi%sExit", LN),
                String.format("Hello, dear friend, I'm a oracle.%s%sExit%s", LN, LN, LN)
        );
    }

    @Test
    public void whenTypeBlaBlaThenDontKnow() throws IOException {
        this.testServer(
                String.format("BlaBla%sExit", LN),
                String.format("This is incomprehensible. Would you mind trying again?%s%sExit%s", LN, LN, LN)
        );
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Server server = new Server(socket);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        server.start();
        assertThat(out.toString(), is(expected));
    }
}
