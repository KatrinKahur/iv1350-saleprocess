package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * All method calls from the view to the model are passed through this class.
 */
public class Controller {
    private final Printer printer;
    private final CashRegister cashRegister;
    private final Inventory inventory;
    private final SaleCatalog saleCatalog;
    private final Accounting accounting;
    private final CustomerCatalog customerCatalog;
    private Sale currentSale;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new instance of the Controller class.
     * @param creator Used to get all registries that are used in the program.
     */
    public Controller(CatalogCreator creator){

        this.inventory = creator.getInventory();
        this.saleCatalog = creator.getSaleCatalog();
        this.accounting = creator.getAccounting();
        this.printer = new Printer();
        this.cashRegister = new CashRegister();
        this.customerCatalog = creator.getCustomerCatalog();
    }

    /**
     * This method starts a new sale.
     */
    public void startSale(){
        currentSale = new Sale();
        currentSale.addSaleObservers(saleObservers);
    }

    /**
     * This method registers a newly scanned item.
     * @param identifier Used to get the barcode of the newly scanned item.
     * @return <code>SaleDTO</code>with the information about item name, price and running total
     * @throws InvalidItemIdentifierException Thrown when item with the specified <code>identifier</code> is not found
     * in the inventory database.
     * @throws UnableToPerformOperationException Thrown when the operation cannot be performed and the reason is unknown.
     */
    public SaleDTO registerItem(ItemIdentifier identifier) throws InvalidItemIdentifierException,
                                                                        UnableToPerformOperationException {
        try{
            ItemDTO foundItem = inventory.searchItemByBarcode(identifier);
            return currentSale.registerItem(foundItem);
        }
        catch(ServerNotRunningException serverExc){
            throw new UnableToPerformOperationException("Unable to register the item.", serverExc);
        }
    }

    /**
     * This method makes the correct system calls to the model to end the sale.
     * @return The total price of the sale.
     */
    public Amount endSale(){
        currentSale.calculateVAT();
        return currentSale.getPaymentInformation().getRunningTotal();
    }

    /**
     * This method checks the customer for membership
     * @param searchedCustomer The specified customer that is checked for membership
     * @return The <code>CustomerDTO</code> that has data about customers membership
     */
    public CustomerDTO checkIfCustomerIsAMember(CustomerDTO searchedCustomer){
       return customerCatalog.searchCustomer(searchedCustomer);
    }

    /**
     * This method makes the correct system calls to handle a discount request.
     * @param discountRequestDTO The specified <code>DiscountRequestDTO</code> that has all the data needed to calculate
     *                           a discount.
     * @return The <code>SaleDTO</code>> with the information about the sale, incl. the discount
     */
    public SaleDTO handleDiscountRequest(DiscountRequestDTO discountRequestDTO){
        return currentSale.handleDiscountRequest(discountRequestDTO);
    }



    /**
     * This method makes the correct system calls to the model to register payment and print a receipt.
     * @param paidAmount Amount paid by the customer for the current sale
     */
    public void pay(Amount paidAmount){
        currentSale.registerPayment(cashRegister, paidAmount);
        inventory.updateInventory(currentSale.createSaleDTO());
        accounting.updateAccounting(currentSale.createSaleDTO());
        saleCatalog.logSale(currentSale.createSaleDTO());
        currentSale.printReceipt(printer);
    }

    /**
     * Adds the specified observer that needs to be notified in case of a state change in the <code>Sale</code> object
     * @param observer The specified observer that needs to be notified
     */
    public void addSaleObserver(SaleObserver observer){
        saleObservers.add(observer);
    }

}
