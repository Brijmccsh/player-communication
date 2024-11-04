

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents a player in the communication system.
 * Responsibilities:
 * - Manage player's message queue
 * - Send and receive messages
 * - Keep track of sent message count
 */
public class Player implements Runnable, Serializable {
    private final String name;
    private final BlockingQueue<Message> messageQueue;
    private Player otherPlayer;
    private int sentMessageCount;

    public Player(String name) {
        this.name = name;
        this.messageQueue = new LinkedBlockingQueue<>();
        this.sentMessageCount = 0;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public void sendMessage(String content) {
        sentMessageCount++;
        Message message = new Message(content, sentMessageCount);
        otherPlayer.receiveMessage(message);
    }

    public void receiveMessage(Message message) {
        messageQueue.offer(message);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Message receivedMessage = messageQueue.take();
                System.out.println(name + " received: " + receivedMessage);
                
                if (sentMessageCount < 10) {
                    String responseContent = receivedMessage.getContent() + " " + (sentMessageCount + 1);
                    sendMessage(responseContent);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getName() {
        return name;
    }

    public int getSentMessageCount() {
        return sentMessageCount;
    }
}