package se.kth.iv1350.saleProcess.integration;

/**
 * Thrown when there is a connection problem with a database server.
 */
public class ServerNotRunningException extends RuntimeException {
    /**
     * Creates a new instance of the object.
     */
    public ServerNotRunningException(){
        super("Cannot reach the server.");
    }
}
