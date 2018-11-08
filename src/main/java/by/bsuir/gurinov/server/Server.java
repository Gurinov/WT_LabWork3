package by.bsuir.gurinov.server;

import by.bsuir.gurinov.network.TcpConnection;
import by.bsuir.gurinov.network.TcpConnectionListener;
import by.bsuir.gurinov.server.controller.InfoController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements TcpConnectionListener {

    private final ArrayList<TcpConnection> connections = new ArrayList<>();
    private final HashMap<TcpConnection, String> dictionary = new HashMap<>();
    private final InfoController controller = new InfoController();

    public Server() {
        System.out.println("Server running");
        try (ServerSocket serverSocket = new ServerSocket(8080)){
            while (true) {
                new TcpConnection(this, serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onConnectionReady(TcpConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnection("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TcpConnection tcpConnection, String value) {
        if (!dictionary.containsKey(tcpConnection)) {
            dictionary.put(tcpConnection, value);
            tcpConnection.sendString("ok");
        } else {
            boolean flag = !controller.findInfo(dictionary.get(tcpConnection)).equals("Error");
            if (value.equals("1") && flag){
                tcpConnection.sendString(controller.findInfo(dictionary.get(tcpConnection)));
            } else
            if (value.equals("2") && flag){
                tcpConnection.sendString("edit");
            } else
            if (value.equals("3") && !flag){
                tcpConnection.sendString("add");
            } else {
                String[] subStr = value.split("\\s");
                if(subStr[subStr.length - 1].equals("add")){
                    controller.addInfo(subStr[0], subStr[1], Integer.parseInt(subStr[2]), subStr[3]);
                    tcpConnection.sendString("ok");
                }
                if(subStr[subStr.length - 1].equals("edit")){
                    controller.editInfo(dictionary.get(tcpConnection), subStr[0], subStr[1], Integer.parseInt(subStr[2]), subStr[3]);
                    tcpConnection.sendString("ok");
                }
            }
            tcpConnection.sendString("ok");
        }
    }

    @Override
    public synchronized void onDisconnect(TcpConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnection("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TcpConnection tcpConnection, Exception e) {
        System.out.println("TCP connection exception: " + e);
    }

    private void sendToAllConnection(String value) {
        System.out.println(value);
        /*for (TcpConnection connection : connections) {
            connection.sendString(value);
        }*/
    }
    private void signIn(String value, TcpConnection connection) {
        System.out.println(value);
        connection.sendString(value);
    }
}
