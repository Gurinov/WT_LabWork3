package by.bsuir.gurinov.network;

import by.bsuir.gurinov.client.model.Message;

public interface TcpConnectionListener {
    /**
     * Start readies connection.
     */
    void onConnectionReady(TcpConnection tcpConnection);

    /**
     * Get string.
     */
    void onReceiveString(TcpConnection tcpConnection, String message);

    /**
     * Disconnection.
     */
    void onDisconnect(TcpConnection tcpConnection);

    /**
     * @param e exception in network.
     */
    void onException(TcpConnection tcpConnection, Exception e);
}
