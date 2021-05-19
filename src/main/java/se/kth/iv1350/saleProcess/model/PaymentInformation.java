package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.SaleDTO;

/**
 * This class contains information about payment.
 */
public class PaymentInformation {
    private Amount runningTotal;
    private Amount paidAmount;
    private Amount change;

    /**
     * Creates an instance of <code>PaymentInformation</code>.
     */
    PaymentInformation(){
        runningTotal = new Amount();
    }

    /**
     * Updates the running total after adding a new item.
     * @param addedItem The newly added item. The parameter is used to get the price of the item.
     */
    void updateRunningTotal(Item addedItem){
        runningTotal = runningTotal.plus(addedItem.getPriceWithVAT());
    }

    /**
     * Subtracts the discount from the running total.
     * @param discount Has the discount amount that needs to be subtracted from the running total.
     */
    void applyDiscount(Discount discount){
       runningTotal = runningTotal.minus(discount.getDiscountAmount());
    }

    /**
     * Makes the correct system calls to register cash payment.
     * @param paidAmount The amount paid by the customer.
     * @param cashRegister The specified cash register that receives the paid amount.
     */
    void pay(Amount paidAmount, CashRegister cashRegister){
        this.paidAmount = paidAmount;
        cashRegister.registerPayment(this);
        change = cashRegister.getChange(this);
    }

    /**
     * @return Returns the value of <code>change</code>.
     */
    Amount getChange(){
        return change;
    }

    /**
     * @return Returns the value of <code>paidAmount</code>.
     */
    Amount getPaidAmount(){
        return paidAmount;
    }

    /**
     * Returns the value of <code>runningTotal</code>.
     * @return The value of <code>runningTotal</code>
     */
    public Amount getRunningTotal(){
        return runningTotal;
    }
}
