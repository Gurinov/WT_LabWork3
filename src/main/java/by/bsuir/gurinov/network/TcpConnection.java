package by.bsuir.gurinov.network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class TcpConnection {
    private final Socket socket;
    private final Thread thread;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final TcpConnectionListener eventListener;

    public TcpConnection(TcpConnectionListener tcpConnectionListener, String ip, int port) throws IOException {
        this(tcpConnectionListener, new Socket(ip, port));
    }

    public TcpConnection(TcpConnectionListener tcpConnectionListener, Socket socket) throws IOException {
        this.socket = socket;
        eventListener = tcpConnectionListener;
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream(),
                Charset.forName("UTF-8")
        ));
        out = new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream(),
                Charset.forName("UTF-8")
        ));
        thread = new Thread(new Runnable() {
            public void run() {
                try {
                    eventListener.onConnectionReady(TcpConnection.this);
                    while (!thread.isInterrupted()) {
                        eventListener.onReceiveString(
                                TcpConnection.this,
                                in.readLine()
                        );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    eventListener.onDisconnect(TcpConnection.this);
                }
            }
        });
        thread.start();
    }

    /**
     * @param value sends string.
     */
    public synchronized void sendString(String value) {
        try {
            out.write(value + "\r\n");
            out.flush();
        } catch (IOException e) {
            eventListener.onException(TcpConnection.this, e);
            disconnect();
        }
    }

    /**
     * Close connection.
     */
    public synchronized void disconnect() {
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onException(TcpConnection.this, e);
        }
    }

    @Override
    public String toString() {
        return "TcpConnection: "
                + socket.getInetAddress()
                + ": " + socket.getPort();
    }
}