package se.kth.iv1350.saleProcess.controller;

/**
 * Thrown when an operation fails without a known reason.
 */
public class OperationFailedException extends Exception{
    /**
     * Creates a new instance of the object with the specified message and cause
     * @param msg The message that contains information about why the exception was thrown
     */
    public OperationFailedException(String msg, Exception cause){
        super(msg,cause);
    }
}
