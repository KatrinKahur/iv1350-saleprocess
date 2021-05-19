package se.kth.iv1350.saleProcess.model;

/**
 * This class represents a cash register that registers cash payments.
 */
public class CashRegister {
    private Amount balance;

    /**
     * Creates a new instance of <code>CashRegister</code>
     */
    public CashRegister(){
        balance = new Amount();
    }

    /**
     * This method adds paymentInformation to the cash register
     * @param paymentInformation Used to get the paid amount for the sale.
     */
    void registerPayment(PaymentInformation paymentInformation){
        increaseBalance(paymentInformation.getPaidAmount());
    }

    /**
     * This method calculates the change.
     * @param paymentInformation Used to get the paid amount and the running total.
     * @return The calculated change
     */
    Amount getChange(PaymentInformation paymentInformation){
        return paymentInformation.getPaidAmount().minus(paymentInformation.getRunningTotal());
    }

    private void increaseBalance(Amount paidAmount){
        balance = balance.plus(paidAmount);
    }





}
