package se.kth.iv1350.saleProcess.integration;

/**
 * This class is the creator of all kinds of registries that are used in the program.
 */
public class CatalogCreator {
    private Inventory inventory = new Inventory();
    private SaleCatalog saleCatalog = new SaleCatalog();
    private Accounting accounting = new Accounting();

    /**
     * This method gets the value of <code>inventory</code>
     * @return The value of <code>inventory</code>
     */
    public Inventory getInventory(){
        return inventory;
    }

    /**
     * This method gets the value of <code>saleCatalog</code>
     * @return The value of <code>saleCatalog</code>
     */
    public SaleCatalog getSaleCatalog(){
        return saleCatalog;
    }

    /**
     * This method gets the value of <code>accounting</code>
     * @return The value of <code>accounting</code>
     */
    public Accounting getAccounting(){
        return accounting;
    }

}
