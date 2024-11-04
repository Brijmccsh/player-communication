import java.io.*;
import java.net.*;

public class PlayerProcess {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PlayerProcess <name> <port>");
            System.exit(1);
        }

        String name = args[0];
        int port = Integer.parseInt(args[1]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(name + " waiting for connection on port " + port);
            try (Socket socket = serverSocket.accept();
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                Player player = new Player(name);
                while (true) {
                    try {
                        Message message = (Message) in.readObject();
                        System.out.println(name + " received: " + message);

                        if (player.getSentMessageCount() < 10) {
                            String responseContent = message.getContent() + " " + (player.getSentMessageCount() + 1);
                            Message response = new Message(responseContent, player.getSentMessageCount() + 1);
                            out.writeObject(response);
                            out.flush();
                        } else {
                            break;
                        }
                    } catch (EOFException e) {
                        System.out.println(name + ": Connection closed by peer.");
                        break;
                    }
                }
            }
        } catch (BindException e) {
            System.err.println("Port " + port + " is already in use. Please choose a different port.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}