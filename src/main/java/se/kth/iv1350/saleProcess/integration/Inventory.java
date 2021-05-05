package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.Sale;

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
        addItemsToItemList();
    }


    private void addItemsToItemList(){
        itemList.add(new ItemDTO("Milk", new Amount(25), 25, new ItemIdentifier(1)));
        itemList.add(new ItemDTO("Pasta", new Amount(14), 12, new ItemIdentifier(2)));
        itemList.add(new ItemDTO("Yoghurt", new Amount(27), 12, new ItemIdentifier(3)));
        itemList.add(new ItemDTO("Chicken filet", new Amount(48), 12, new ItemIdentifier(4)));
        itemList.add(new ItemDTO("Meatballs", new Amount(42), 6, new ItemIdentifier(5)));
        itemList.add(new ItemDTO("Orange", new Amount(8), 25, new ItemIdentifier(6)));
        itemList.add(new ItemDTO("Cornflakes", new Amount(17), 25, new ItemIdentifier(7)));
        itemList.add(new ItemDTO("Pizza Hawaii", new Amount(100), 12, new ItemIdentifier(8)));
        itemList.add(new ItemDTO("Tomato", new Amount(10), 6, new ItemIdentifier(9)));
        itemList.add(new ItemDTO("Orange juice", new Amount(19), 6, new ItemIdentifier(10)));
        itemList.add(new ItemDTO("Soda", new Amount(12), 20, new ItemIdentifier(11)));
        itemList.add(new ItemDTO("Chocolate bar", new Amount(15), 6, new ItemIdentifier(12)));
        itemList.add(new ItemDTO("Toilet paper", new Amount(31), 25, new ItemIdentifier(13)));
        itemList.add(new ItemDTO("Strawberry ice cream", new Amount(39), 25, new ItemIdentifier(14)));
        itemList.add(new ItemDTO("Shampoo", new Amount(26), 6, new ItemIdentifier(15)));
        itemList.add(new ItemDTO("Basmati rice", new Amount(19), 25, new ItemIdentifier(16)));
        itemList.add(new ItemDTO("Salt", new Amount(11), 25, new ItemIdentifier(17)));
        itemList.add(new ItemDTO("Sugar", new Amount(13), 12, new ItemIdentifier(18)));
        itemList.add(new ItemDTO("Pepper", new Amount(13), 12, new ItemIdentifier(19)));
        itemList.add(new ItemDTO("Baking powder", new Amount(22), 6, new ItemIdentifier(20)));
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


    public void sendSaleToInventory(Sale currentSale){
    }


}
