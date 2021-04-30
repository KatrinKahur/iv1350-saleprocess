package se.kth.iv1350.saleProcess.model;

/**
 * This class represents a money amount.
 */
public class Amount {
    int amount;

    /**
     * Creates an instance of <code>Amount</code>.
     * @param amount The represented amount
     */
    public Amount(int amount){
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
     * This method adds the specified <code>Amount</code> to this <code>Amount</code>
     * @param amountToAdd The <code>Amount</code> that needs to be added to this <code>Amount</code>
     * @return The new <code>Amount</code> after adding the specified <code>Amount</code>
     */
    public Amount plus (Amount amountToAdd){
        return new Amount(amount + amountToAdd.amount);
    }

    /**
     * This method subtracts the specified <code>Amount</code> from this <code>Amount</code>
     * @param amountToSubtract The <code>Amount</code> that needs to be subtracted from this <code>Amount</code>
     * @return The new <code>Amount</code> after subtracting the specified <code>Amount</code>
     */
    public Amount minus (Amount amountToSubtract){
        return new Amount(amount - amountToSubtract.amount);
    }
}
