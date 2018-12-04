package edu.csumb8.grocdb;

import edu.csumb8.grocdb.entitites.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class GroceryController {

    @Autowired
    IGroceryRepository grocRepo;

    /**
     * @route /getAll
     * @return A list of existing groceries in the db
     */
    @CrossOrigin()
    @GetMapping("/getAll")
    public List<GroceryItem> getAll() {
        List<GroceryItem> result = grocRepo.findAll();
        return result;
    }

    /**
     * @route /id/{id}
     * @param String id of item
     * @return GroceryItem item with matching id
     */
    @CrossOrigin()
    @GetMapping("/id/{id}")
    public GroceryItem getItemById(@PathVariable String id) {
        GroceryItem item = grocRepo.findByRepoId(id);
        return item;
    }

    /**
     * @route /available/{id}/{amount}
     * @param String item id
     * @param String amount of item to purchase
     * @return boolean availability
     */
    @CrossOrigin()
    @GetMapping("/available/{id}/{amount}")
    public boolean checkAvailability(@PathVariable String id, @PathVariable int amount) {
        GroceryItem item = grocRepo.findByRepoId(id);
        boolean canPurchase = item.available(amount);
        return canPurchase;
    }

    /**
     * @route /purchase
     * @param String [] ids
     * @return boolean on succesful purchase
     */
    @CrossOrigin()
    @PostMapping("/purchase")
    public boolean purchase(@RequestParam String [] ids) {
        List<GroceryItem> cart = new ArrayList<>();
        HashMap<String, GroceryItem> groceryItems = new HashMap<>();
        for (int i = 0; i<ids.length; i++) {
            GroceryItem item;
            String id = ids[i];
            System.out.println(ids[i]);
            if (groceryItems.containsKey(id)) {
                item = groceryItems.get(id);
            }
            else {
                item = grocRepo.findByRepoId(id);
                groceryItems.put(id, item);
                if (item == null) {
                    System.out.println("error: cant find item with id = " + id);
                    return false;
                }
            }

            if (!item.available(1)) {
                return false;
            }
            else {
                item.purchase(1);
                cart.add(item);
            }
        }
        grocRepo.saveAll(cart);
        return true;
    }
}
