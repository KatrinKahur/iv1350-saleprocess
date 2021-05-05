package se.kth.iv1350.saleProcess.model;

/**
 * This class represents a sale receipt.
 */
public class Receipt {
    private Sale currentSale;

    /**
     * This method sends the sale information to the receipt
     * @param currentSale The sale that is sent to the receipt
     */
    void sendSaleToReceipt(Sale currentSale){
        this.currentSale = currentSale;
    }

    /**
     * Creates a formatted string representation of the receipt
     * @return String representation of the receipt
     */
    @Override
    public String toString(){
        StringBuilder receiptToString = new StringBuilder("RECEIPT \n");
        receiptToString.append("Store name: FoodLand\n");
        receiptToString.append("Store address: Kantarellstigen 34, 13411\n" );
        receiptToString.append("Sale time and date: ");
        receiptToString.append(currentSale.getSaleTime());
        receiptToString.append("\n\n");
        receiptToString.append("BOUGHT ITEMS: \n\n");

        for (Item item : currentSale.getListOfItems()){
            receiptToString.append(item);
            receiptToString.append("************\n");
        }

        receiptToString.append("\n");
        receiptToString.append("Total price: ");
        receiptToString.append(currentSale.getTotalPrice());
        receiptToString.append(" SEK\n");
        receiptToString.append("Cash payment: ");
        receiptToString.append(currentSale.getCashPayment());
        receiptToString.append(" SEK\n");
        receiptToString.append("Change: ");
        receiptToString.append(currentSale.getChange());
        receiptToString.append(" SEK\n\n");
        receiptToString.append("Thank you for your purchase!");
        return receiptToString.toString();

    }
}
