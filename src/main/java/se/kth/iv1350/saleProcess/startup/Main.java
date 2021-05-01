package se.kth.iv1350.saleProcess.startup;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.integration.CatalogCreator;
import se.kth.iv1350.saleProcess.view.View;

/**
 * Starts the program. Contains the main method to start the program.
 */
public class Main {
    /**
     * The main method starts the program.
     * @param args The method does not take any parameters.
     */
    public static void main (String[] args){
        CatalogCreator creator = new CatalogCreator();
        Controller contr = new Controller(creator);
        View view = new View(contr);

        view.fakeProgramExecution();

    }
}
