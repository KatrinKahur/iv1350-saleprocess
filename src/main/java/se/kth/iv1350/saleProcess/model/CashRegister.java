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
     * @param currentSale It is used to get the total price of the sale and the paid amount.
     * @return The calculated change after adding the payment
     */
     Amount addPayment(Sale currentSale){
        increaseBalance(currentSale.getTotalPrice());
        return calculateChange(currentSale.getCashPayment(), currentSale.getTotalPrice());
    }


    private void increaseBalance(Amount amount){
        balance = balance.plus(amount);
    }

    private Amount calculateChange(Amount payment, Amount totalPrice){
        return payment.minus(totalPrice);
    }


}
