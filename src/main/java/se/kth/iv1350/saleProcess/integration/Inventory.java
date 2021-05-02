package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Amount;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains calls to the external inventory system that has descriptions and prices of all grocery items.
 */
public class Inventory {

    List <ItemDTO> itemList = new ArrayList<>();

    /**
     * Creates an instance of <code>Inventory</code>
     */
    Inventory(){
        addItems();
    }

    /**
     * This class adds items to <code>itemList</code>
     */
    private void addItems(){
        itemList.add(new ItemDTO("Milk", new Amount(25), 15, new ItemIdentifier(1, 20)));
        itemList.add(new ItemDTO("Pasta", new Amount(14), 15, new ItemIdentifier(2, 20)));
        itemList.add(new ItemDTO("Yoghurt", new Amount(27), 20, new ItemIdentifier(3, 20)));
        itemList.add(new ItemDTO("Chicken filet", new Amount(48), 20, new ItemIdentifier(4, 20)));
        itemList.add(new ItemDTO("Meatballs", new Amount(42), 15, new ItemIdentifier(5, 20)));
        itemList.add(new ItemDTO("Orange", new Amount(8), 10, new ItemIdentifier(6, 20)));
        itemList.add(new ItemDTO("Cornflakes", new Amount(17), 10, new ItemIdentifier(7, 20)));
        itemList.add(new ItemDTO("Pizza Hawaii", new Amount(100), 15, new ItemIdentifier(8, 20)));
        itemList.add(new ItemDTO("Tomato", new Amount(10), 15, new ItemIdentifier(9, 20)));
        itemList.add(new ItemDTO("Orange juice", new Amount(19), 15, new ItemIdentifier(10, 20)));
        itemList.add(new ItemDTO("Soda", new Amount(12), 15, new ItemIdentifier(11, 20)));
        itemList.add(new ItemDTO("Chocolate bar", new Amount(15), 10, new ItemIdentifier(12, 20)));
        itemList.add(new ItemDTO("Toilet paper", new Amount(31), 10, new ItemIdentifier(13, 20)));
        itemList.add(new ItemDTO("Strawberry ice cream", new Amount(39), 20, new ItemIdentifier(14, 20)));
        itemList.add(new ItemDTO("Shampoo", new Amount(26), 15, new ItemIdentifier(15, 20)));
        itemList.add(new ItemDTO("Basmati rice", new Amount(19), 20, new ItemIdentifier(16, 20)));
        itemList.add(new ItemDTO("Salt", new Amount(11), 15, new ItemIdentifier(17, 20)));
        itemList.add(new ItemDTO("Sugar", new Amount(13), 15, new ItemIdentifier(18, 20)));
        itemList.add(new ItemDTO("Pepper", new Amount(13), 15, new ItemIdentifier(19, 20)));
        itemList.add(new ItemDTO("Baking powder", new Amount(22), 20, new ItemIdentifier(20, 20)));
    }

    /**
     * This class searches the matching item in the <code>itemList</code>
     * @param searchedIdentifier Item identifier of the specified item
     * @return <code>ItemDTO</code> which barcode matches <code>searchedIdentifier</code>s barcode
     */
    public ItemDTO searchItem(ItemIdentifier searchedIdentifier){
        int searchedItemBarcode = searchedIdentifier.getBarcode();

        for (ItemDTO item : itemList ){
            if(searchedItemBarcode == item.getBarcode())
                return item;
        }

        return null;
    }
}
