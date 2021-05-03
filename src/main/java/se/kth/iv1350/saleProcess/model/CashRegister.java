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
     * This method adds payment to the cash register
     * @param cashPayment The payment that needs to be added to the cash register
     * @param currentSale It is used to get the total price of the sale
     * @return The calculated change after adding the payment
     */
    public Amount addPayment(Amount cashPayment, Sale currentSale){
        increaseBalance(currentSale.getTotalPrice());
        return calculateChange(cashPayment, currentSale.getTotalPrice());
    }

    /**
     * This method increases <code>balance</code> by adding the specified amount
     * @param amount The specified amount that the balance is increased by
     */
    private void increaseBalance(Amount amount){
        balance = balance.plus(amount);
    }

    /**
     * This method calculates the change.
     * @param payment The payment from which the change is calculated
     * @param totalPrice The total price of the sale
     * @return The calculated change after adding the payment
     */
    private Amount calculateChange(Amount payment, Amount totalPrice){
        return payment.minus(totalPrice);
    }


}
