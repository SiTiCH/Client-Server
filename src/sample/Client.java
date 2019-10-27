package sample;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private static final int PORT = 4646;

    private DataOutputStream outputServer;
    private BufferedReader inFromServer;
    private Socket clientSocket;

    public void startClient() throws IOException {
        clientSocket = new Socket("localhost", PORT);
        outputServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void disconnect() throws IOException {
        clientSocket.close();
    }

    public void request(String massenge) throws IOException {
        outputServer.writeBytes(massenge + "\n");
    }

    public String response() throws IOException {
        return inFromServer.readLine();
    }

    public boolean getKeepAlive() throws SocketException {
        return clientSocket.getKeepAlive();
    }

    public boolean isClosed() throws SocketException {
        return clientSocket.isClosed();
    }
}

