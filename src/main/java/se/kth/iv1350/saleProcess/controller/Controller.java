package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.CashRegister;
import se.kth.iv1350.saleProcess.model.DiscountHandeler;
import se.kth.iv1350.saleProcess.model.Sale;

/**
 * All method calls from the view to the model are passed through this class.
 */
public class Controller {
    private Printer printer;
    private CashRegister cashRegister;
    private Inventory inventory;
    private SaleCatalog saleCatalog;
    private CustomerCatalog customerCatalog;
    private Accounting accounting;
    private DiscountCatalog discCatalog;
    private DiscountHandeler discHandeler;
    private Sale currentSale;

    /**
     * Creates a new instance of the Controller class.
     * @param creator Used get all registries that are used in the program.
     */
    public Controller(CatalogCreator creator){

        this.inventory = creator.getInventory();
        this.saleCatalog = creator.getSaleCatalog();
        this.customerCatalog = creator.getCustomerCatalog();
        this.accounting = creator.getAccounting();
        this.discCatalog = creator.getDiscountCatalog();
        this.printer = new Printer();
        this.cashRegister = new CashRegister();
    }

    /**
     * The method that starts a new sale.
     */
    public void startSale(){
        currentSale = new Sale();
        discHandeler = new DiscountHandeler(discCatalog);
    }

    /**
     * This method registers a newly scanned item.
     * @param identifier Used to get the barcode of the newly scanned item
     * @return <code>String</code>> including information about item name, price and running total
     */
    public String registerItem(ItemIdentifier identifier){
        ItemDTO foundItem = inventory.searchItem(identifier);
        SaleDTO saleInformation = currentSale.registerItem(foundItem);
        return saleInformation.toString();
    }

}
