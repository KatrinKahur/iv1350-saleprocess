package se.kth.iv1350.saleProcess.controller;

/**
 * Thrown when an operation cannot be performed without a known reason.
 */
public class UnableToPerformOperationException extends Exception{
    /**
     * Creates a new instance of the object.
     * @param msg The message that contains information about why the exception was thrown.
     * @param cause The <code>Exception</code> that caused this <code>Exception</code>
     */
    public UnableToPerformOperationException(String msg, Exception cause){
        super(msg,cause);
    }
}
