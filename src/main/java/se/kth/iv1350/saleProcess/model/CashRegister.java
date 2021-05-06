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
     * @param currentSale Used to get the total price of the sale.
     */
    void registerPayment(Sale currentSale){
        increaseBalance(currentSale.getTotalPrice());
    }

    /**
     * This method gets the change.
     * @param currentSale Used to get the paid amount and the total price of the sale.
     * @return The calculated change
     */
    Amount getChange(Sale currentSale){
        return currentSale.getCashPayment().minus(currentSale.getTotalPrice());
    }

    private void increaseBalance(Amount amount){
        balance = balance.plus(amount);
    }





}
