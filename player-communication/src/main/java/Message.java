
import java.io.Serializable;

/**
 * Represents a message in the communication system.
 * Responsibilities:
 * - Store message content and counter
 */
public class Message implements Serializable {
    private final String content;
    private final int counter;

    public Message(String content, int counter) {
        this.content = content;
        this.counter = counter;
    }

    public String getContent() {
        return content;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "Message{content='" + content + "', counter=" + counter + "}";
    }
}