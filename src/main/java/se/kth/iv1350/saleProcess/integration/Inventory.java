package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Amount;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains calls to the external inventory system that has descriptions and prices of all grocery items.
 */
public class Inventory {

    List <ItemDTO> itemList = new ArrayList<>();

    Inventory(){
        addItems();
    }

    /**
     * This class adds items to <code>itemList</code>
     */
    private void addItems(){
        itemList.add(new ItemDTO("milk", new Amount(25), 15, new ItemIdentifier(1, 5)));
        itemList.add(new ItemDTO("pasta", new Amount(14), 15, new ItemIdentifier(2, 5)));
        itemList.add(new ItemDTO("yoghurt", new Amount(27), 20, new ItemIdentifier(3, 5)));
        itemList.add(new ItemDTO("chicken filet", new Amount(48), 20, new ItemIdentifier(4, 5)));
        itemList.add(new ItemDTO("meatballs", new Amount(42), 15, new ItemIdentifier(5, 5)));
        itemList.add(new ItemDTO("fruit", new Amount(8), 10, new ItemIdentifier(6, 10)));
        itemList.add(new ItemDTO("cornflakes", new Amount(17), 10, new ItemIdentifier(7, 5)));
        itemList.add(new ItemDTO("pizza", new Amount(100), 15, new ItemIdentifier(8, 5)));
        itemList.add(new ItemDTO("vegetable", new Amount(10), 15, new ItemIdentifier(9, 10)));
        itemList.add(new ItemDTO("juice", new Amount(19), 15, new ItemIdentifier(10, 5)));
        itemList.add(new ItemDTO("soda", new Amount(12), 15, new ItemIdentifier(11, 5)));
        itemList.add(new ItemDTO("chocolate bar", new Amount(15), 10, new ItemIdentifier(12, 5)));
        itemList.add(new ItemDTO("toilet paper", new Amount(31), 10, new ItemIdentifier(13, 5)));
        itemList.add(new ItemDTO("ice cream", new Amount(39), 20, new ItemIdentifier(14, 5)));
        itemList.add(new ItemDTO("shampoo", new Amount(26), 15, new ItemIdentifier(15, 5)));
        itemList.add(new ItemDTO("rice", new Amount(19), 20, new ItemIdentifier(16, 5)));
        itemList.add(new ItemDTO("salt", new Amount(11), 15, new ItemIdentifier(17, 5)));
        itemList.add(new ItemDTO("sugar", new Amount(13), 15, new ItemIdentifier(18, 5)));
        itemList.add(new ItemDTO("pepper", new Amount(13), 15, new ItemIdentifier(19, 5)));
        itemList.add(new ItemDTO("baking powder", new Amount(22), 20, new ItemIdentifier(20, 5)));
    }
}
