package se.kth.iv1350.saleProcess.integration;

/**
 * This class is the creator of all kinds of registries that are used in the program.
 */
public class CatalogCreator {
    private Inventory inventory = new Inventory();
    private SaleCatalog saleCatalog = new SaleCatalog();
    private Accounting accounting = new Accounting();
    private CustomerCatalog customerCatalog = new CustomerCatalog();

    /**
     * This method gets the value of inventory
     * @return The value of inventory
     */
    public Inventory getInventory(){
        return inventory;
    }

    /**
     * This method gets the value of saleCatalog
     * @return The value of saleCatalog
     */
    public SaleCatalog getSaleCatalog(){
        return saleCatalog;
    }

    /**
     * This method gets the value of accounting
     * @return The value of accounting
     */
    public Accounting getAccounting(){
        return accounting;
    }

    /**
     * This method gets the value of customerCatalog
     * @return The value of customerCatalog
     */
    public CustomerCatalog getCustomerCatalog(){
        return customerCatalog;
    }

}
