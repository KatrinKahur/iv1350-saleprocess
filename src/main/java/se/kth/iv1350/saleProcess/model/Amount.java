package se.kth.iv1350.saleProcess.model;

/**
 * This class represents a money amount.
 */
public class Amount {
    private final double amount;

    /**
     * Creates an instance of <code>Amount</code>.
     */
    public Amount(){
        this(0);
    }

    /**
     * Creates an instance of <code>Amount</code>.
     * @param amount The represented amount
     */
    public Amount(double amount){
        this.amount = amount;
    }

    /**
     * This method compares two instances of <code>Amount</code> to determine whether they are equal.
     * @param amountToCompare The compared <code>Amount</code>
     * @return <code>true</code> if the compared amount is equal to this amount, <code>false</code> if it is not equal.
     */
    public boolean equals(Object amountToCompare){
        if (amountToCompare == null || !(amountToCompare instanceof Amount)){
            return false;
        }

        Amount comparedAmount = (Amount) amountToCompare;
        return amount == comparedAmount.amount;
    }

    /**
     * This method performs addition between two <code>Amount</code>s
     * @param amountToAdd The <code>Amount</code> that needs to be added to this <code>Amount</code>
     * @return The result of the addition
     */
    public Amount plus (Amount amountToAdd){
        return new Amount(amount + amountToAdd.amount);
    }

    /**
     * This method performs subtraction between two <code>Amount</code>s
     * @param amountToSubtract The <code>Amount</code> that needs to be subtracted from this <code>amount</code>
     * @return The result of the subtraction
     */
    public Amount minus (Amount amountToSubtract){
        return new Amount(amount - amountToSubtract.amount);
    }

    /**
     * This method performs multiplication between two <code>Amount</code>s
     * @param amountToMultiply <code>Amount</code> that this <code>amount</code> needs to be multiplied by
     * @return The result of the multiplication
     */
    public Amount multiply(Amount amountToMultiply){
        return new Amount(amount * amountToMultiply.amount);
    }

    /**
     * This method converts <code>Amount</code> into <code>String</code>
     * @return <code>String</code> representation of <code>Amount</code>
     */
    public String toString(){
        return Double.toString(amount);
    }

    /**
     * This method gets the value of amount
     * @return The value of amount
     */
    double getAmount(){
        return amount;
    }
}
