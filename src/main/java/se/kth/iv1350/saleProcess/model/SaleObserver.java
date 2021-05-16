package se.kth.iv1350.saleProcess.model;

/**
 * A listener interface observing the state changes in the <code>Sale</code> object. Classes implementing this interface
 * are interested in being notified whenever a certain state change has occurred in the <code>Sale</code>.
 */
public interface SaleObserver{
    /**
     * Invoked when a new payment has been registered.
     * @param totalPrice The total price of a sale.
     */
    void newPaymentAddedToSale(Amount totalPrice);

}
