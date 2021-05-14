package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.CashRegister;
import se.kth.iv1350.saleProcess.model.DiscountCalculator;
import se.kth.iv1350.saleProcess.model.Sale;

/**
 * All method calls from the view to the model are passed through this class.
 */
public class Controller {
    private final Printer printer;
    private final CashRegister cashRegister;
    private final Inventory inventory;
    private final SaleCatalog saleCatalog;
    private final Accounting accounting;
    private final DiscountCalculator discCalculator;
    private Sale currentSale;

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
        this.discCalculator = new DiscountCalculator();
    }

    /**
     * This method starts a new sale.
     */
    public void startSale(){
        currentSale = new Sale();
    }

    /**
     * This method registers a newly scanned item.
     * @param identifier Used to get the barcode of the newly scanned item
     * @return <code>SaleDTO</code>with the information about item name, price and running total
     * @throws InvalidItemIdentifierException Thrown when an item with the specified identifier is not found
     * @throws OperationFailedException Thrown when the operation fails and the reason is unknown
     */
    public SaleDTO registerItem(ItemIdentifier identifier) throws InvalidItemIdentifierException,
                                                                        OperationFailedException
    {
        try{
            ItemDTO foundItem = inventory.searchItemByBarcode(identifier);
            return currentSale.registerItem(foundItem);
        }
        catch(ServerNotRunningException serverExc){
            throw new OperationFailedException("Item registration failed.", serverExc);
        }
    }

    /**
     * This method makes the correct system calls to the model to end the sale.
     * @return The total price of the sale.
     */
    public Amount endSale(){
        currentSale.calculateTotalPrice();
        return currentSale.getTotalPrice();
    }

    /**
     * This method makes the correct system calls to the model to register payment and print a receipt.
     * @param cashPayment Amount paid by the customer for the current sale
     */
    public void pay(Amount cashPayment){
        currentSale.registerPayment(cashRegister, cashPayment);
        inventory.updateInventory(currentSale);
        accounting.updateAccounting(currentSale);
        saleCatalog.logSale(currentSale);
        currentSale.printReceipt(printer);
    }

}
