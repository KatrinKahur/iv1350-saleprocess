package se.kth.iv1350.saleProcess.model;

/**
 * This class has information about the applied discount.
 */
public class Discount {
    private Amount discountAmount;

    /**
     * Create a new instance of the object. The discount amount is set to 0.
     */
    Discount(){
        this.discountAmount = new Amount();
    }

    /**
     * Assigns a new value to <code>discountAmount</code>
     * @param discountAmount The specified discount <code>Amount</code>
     */
    void setDiscountAmount(Amount discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * @return Returns the value of <code>discountAmount</code>
     */
    public Amount getDiscountAmount(){
        return discountAmount;
    }
}
