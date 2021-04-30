package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.integration.CatalogCreator;
import se.kth.iv1350.saleProcess.integration.Printer;
import se.kth.iv1350.saleProcess.model.CashRegister;

/**
 * All method calls from the view to the model are passed through this class.
 */
public class Controller {
    private CatalogCreator creator;
    private Printer printer;
    private CashRegister cashRegister;

}
