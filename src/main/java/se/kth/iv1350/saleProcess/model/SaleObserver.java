package se.kth.iv1350.saleProcess.model;

/**
 * A listener interface observing the state changes in the <code>Sale</code> object. Classes implementing this interface
 * are interested in being notified whenever a certain state change has occurred in the <code>Sale</code>.
 */
public interface SaleObserver{
    /**
     * Invoked when a new payment has been registered.
     * @param paymentInformation Has the total price of the sale.
     */
    void newPaymentAddedToSale(PaymentInformation paymentInformation);

}
