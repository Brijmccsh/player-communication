
/**
 * Main class to run the communication system.
 * Responsibilities:
 * - Initialize and start the communication process
 */
public class Main {
    public static void main(String[] args) {
        Communication communication = new Communication("Player1", "Player2");
        communication.start();
    }
}