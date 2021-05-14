package se.kth.iv1350.saleProcess.integration;

/**
 * Thrown everytime when the user tries to fetch an item with barcode 20 from the inventory.
 */
public class ServerNotRunningException extends RuntimeException {
    /**
     * Creates a new instance of the object.
     */
    public ServerNotRunningException(){
        super("Cannot reach the server.");
    }
}
