package Server;

import java.io.*;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Connection implements Runnable {
    private static Logger log = Logger.getLogger(Server.class.getName());

    private Server server;
    private Socket socket;
    private OutputStreamWriter out;
    private BufferedReader in;
    private BusinessLogic businessLogic = new BusinessLogic();

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            String request;
            while ((request = in.readLine()) != null) {
                log.info("FROM " + getName() + " MESSAGE: " + request + "\n");
                String response = ""+businessLogic.counting(request)+"\n";
                log.info("SEND TO " + getName() + " MESSAGE: " + response + "\n");
                outToClient.writeBytes(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            server.closeConnection(this);
        }
    }

    public Connection(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        FileHandler fileHandler = new FileHandler("logs/JavaLog.log");
        fileHandler.setFormatter(new MyFormatter());
        log.addHandler(fileHandler);
    }

    public String getName() {
        return socket.getRemoteSocketAddress().toString();
    }
}
