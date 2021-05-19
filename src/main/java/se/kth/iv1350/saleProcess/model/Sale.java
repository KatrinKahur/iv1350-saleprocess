package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the current sale.
 */
public class Sale {
    private final Receipt receipt;
    private String saleTime;
    private final List<Item> listOfItems;
    private Item recentlyScannedItem;
    private final PaymentInformation paymentInformation;
    private Amount totalVAT;
    private final Discount discount;
    private final List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates an instance of <code>Sale</code>
      */
    public Sale(){
        setSaleTime();
        receipt = new Receipt();
        listOfItems = new ArrayList<>();
        paymentInformation = new PaymentInformation();
        discount = new Discount();
    }

    /**
     * This method adds the scanned item to the sale
     * @param foundItem The fetched item from the inventory
     * @return <code>SaleDTO</code> with the description of the scanned item and the running total
     */
    public SaleDTO registerItem(ItemDTO foundItem){
        Item existingItem = checkForExistingItem(foundItem);

        if (existingItem == null) {
            setRecentlyScannedItem(new Item(foundItem));
            addRecentlyScannedItemToTheItemList();
        }
        else {
            existingItem.increaseQuantity();
            setRecentlyScannedItem(existingItem);
        }

        paymentInformation.updateRunningTotal(recentlyScannedItem);
        return createSaleDTO();
    }

    /**
     * This method makes the correct system calls to register a cash paymentInformation.
     * @param cashRegister The cash register used to register cash payments
     * @param paidAmount Paid <code>Amount</code>
     */
    public void registerPayment(CashRegister cashRegister, Amount paidAmount){
        paymentInformation.pay(paidAmount, cashRegister);
        notifyObservers();
    }

    /**
     * This method calls the printer to print out the receipt
     * @param printer The object representing the printer
     */
    public void printReceipt(Printer printer){
        receipt.sendSaleToReceipt(createSaleDTO());
        printer.printReceipt(receipt);
    }

    /**
     * Adds the specified observer that needs to be notified about a certain state change.
     * @param observer The specified observer
     */
    public void addSaleObserver(SaleObserver observer){
        saleObservers.add(observer);
    }

    /**
     * Adds the specified observers that need to be notified about a certain state change.
     * @param observers The specified observers
     */
    public void addSaleObservers(List<SaleObserver> observers){
        saleObservers.addAll(observers);
    }

    /**
     * This method handles a discount request by first checking if there are any discounts the customer is eligible for
     * and then applying them to the total price.
     * @param discountRequestDTO The specified <code>DiscountRequestDTO</code> that contains all the data needed to
     *                           calculate a discount
     * @return <code>SaleDTO</code>> with the information about current sale, incl. the discount
     */
    public SaleDTO handleDiscountRequest(DiscountRequestDTO discountRequestDTO){
        Amount discountAmount = DiscountRuleFactory.getFactory().getDefaultDiscountRule().tryDiscount(discountRequestDTO);
        discount.setDiscountAmount(discountAmount);
        paymentInformation.applyDiscount(discount);
        return createSaleDTO();
    }

    /**
     * This method gets the <code>Amount</code> of VAT for the entire sale.
     */
    public void calculateVAT(){
        totalVAT = new Amount();
        for(Item item : listOfItems)
            totalVAT = totalVAT.plus(item.getVATConvertedIntoAmount());
    }

    /**
     * Creates a DTO of <code>Sale</code>
     * @return The created <code>SaleDTO</code> that is a copy of <code>Sale</code>
     */
    public SaleDTO createSaleDTO(){
        return new SaleDTO(saleTime, recentlyScannedItem, listOfItems, paymentInformation, totalVAT, discount);
    }

    /**
     * @return Returns the <code>paymentInformation</code> object.
     */
    public PaymentInformation getPaymentInformation(){
        return paymentInformation;
    }

    private void notifyObservers(){
        for (SaleObserver observer : saleObservers)
            observer.newPaymentAddedToSale(paymentInformation);
    }

    private void setSaleTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        saleTime = LocalDateTime.now().format(formatter);
    }

    private void setRecentlyScannedItem(Item newItem){
        recentlyScannedItem = newItem;
    }

    private void addRecentlyScannedItemToTheItemList(){
        listOfItems.add(recentlyScannedItem);
    }

    private Item checkForExistingItem(ItemDTO foundItem){
        for (Item item : listOfItems){
            if (foundItem.getBarcode() == item.getBarcode())
                return item;
        }
        return null;
    }
}
