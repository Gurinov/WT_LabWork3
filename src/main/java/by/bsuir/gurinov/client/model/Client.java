package by.bsuir.gurinov.client.model;

import by.bsuir.gurinov.network.TcpConnection;
import by.bsuir.gurinov.network.TcpConnectionListener;

import java.io.IOException;
import java.util.Scanner;

public class Client implements TcpConnectionListener {

    private static final String IP = "";
    private static final int PORT = 8080;
    public TcpConnection connection;
    public boolean isConnected;
    public Scanner input = new Scanner(System.in);
    private final String name;

    public Client() {
        isConnected = true;
        connection = null;
        try {
            connection = new TcpConnection(this, IP, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Write you name");
        name = input.nextLine();
        this.connection.sendString(name);
    }

    @Override
    public void onConnectionReady(TcpConnection tcpConnection) {

    }

    @Override
    public void onReceiveString(TcpConnection tcpConnection, String value) {
        Scanner input1 = new Scanner(System.in);

        if(value.equals("ok")) {
            System.out.println("\n");
            System.out.println("Get info: 1");
            System.out.println("Edit info: 2");
            System.out.println("Add new info: 3");

            connection.sendString(input1.next());
        } else
        if(value.equals("add")) {
            System.out.println("Name: ");
            String name = input1.nextLine();
            System.out.println("Mobile: ");
            String mobile = input1.nextLine();
            System.out.println("Group: ");
            String group = input1.nextLine();
            System.out.println("Faculty: ");
            String faculty = input1.nextLine();

            connection.sendString(name + " " + mobile  + " " + group  + " " + faculty  + " add");
        } else
        if(value.equals("edit")) {
            System.out.println("Name: ");
            String name = input1.nextLine();
            System.out.println("Mobile: ");
            String mobile = input1.nextLine();
            System.out.println("Group: ");
            String group = input1.nextLine();
            System.out.println("Faculty: ");
            String faculty = input1.nextLine();

            connection.sendString(name + " " + mobile  + " " + group  + " " + faculty  + " edit");
        } else {
            printMessage(value);

            System.out.println("\n");
            System.out.println("Get info: 1");
            System.out.println("Edit info: 2");
            System.out.println("Add new info: 3");

            connection.sendString(input1.next());
        }
    }

    @Override
    public void onDisconnect(TcpConnection tcpConnection) {
        printMessage("Disconnection");
        isConnected = false;
    }

    @Override
    public void onException(TcpConnection tcpConnection, Exception e) {

    }

    private synchronized void printMessage(String message) {
        System.out.println(message);
    }
}
