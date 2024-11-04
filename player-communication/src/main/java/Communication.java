
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Manages the communication between players.
 * Responsibilities:
 * - Initialize players
 * - Start and manage player threads
 * - Handle the communication process
 */
public class Communication {
    private final Player initiator;
    private final Player responder;
    private final ExecutorService executorService;

    public Communication(String initiatorName, String responderName) {
        this.initiator = new Player(initiatorName);
        this.responder = new Player(responderName);
        this.executorService = Executors.newFixedThreadPool(2);

        initiator.setOtherPlayer(responder);
        responder.setOtherPlayer(initiator);
    }

    public void start() {
        executorService.submit(initiator);
        executorService.submit(responder);

        initiator.sendMessage("Initial Message");

        while (initiator.getSentMessageCount() < 10) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        stop();
    }

    public void stop() {
        executorService.shutdownNow();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("Threads did not terminate in time");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}