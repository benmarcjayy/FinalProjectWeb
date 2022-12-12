package ca.sait.services;

import ca.sait.dataaccess.ItemDB;
import ca.sait.models.Item;
import ca.sait.models.User;
import java.util.List;

/**
 *
 * @author Benmarc Agulto
 */
public class ItemService {

    public List<Item> getAll(User user) {
        ItemDB itemDB = new ItemDB();
        List<Item> item = itemDB.getAll();
        return item;
    }

    public void deleteItem(Item item) {
        if (item != null) {
            ItemDB itemDb = new ItemDB();

            itemDb.deleteItem(item);
        }
    }

    public boolean updateItem(Item item) {
        if (item != null) {
            ItemDB itemDb = new ItemDB();

            return itemDb.updateItem(item);
        }

        return false;
    }

    public boolean createItem(Item item) {
        if (item != null) {
            item.setItemId(getNextItemIdNumber());
            ItemDB itemDb = new ItemDB();

            return itemDb.createItem(item);
        }
        return false;
    }

    private int getNextItemIdNumber() {
        ItemDB itemDb = new ItemDB();

        List<Item> itemList = itemDb.getAll();

        return itemList.get(itemList.size() - 1).getItemId() + 1;
    }
}
