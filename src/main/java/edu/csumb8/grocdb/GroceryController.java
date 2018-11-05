package edu.csumb8.grocdb;

import edu.csumb8.grocdb.entitites.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GroceryController {

    @Autowired
    IGroceryRepository grocRepo;

    /**
     * @route /getAll
     * @return A list of existing groceries in the db
     */
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
    @PostMapping("/purchase")
    public boolean purchase(@RequestParam String [] ids) {
        List<GroceryItem> cart = new ArrayList<>();
        for (int i = 0; i<ids.length; i++) {
            System.out.println(ids[i]);
            String id = ids[i];
            GroceryItem item = grocRepo.findByRepoId(id);
            if (item == null) {
                System.out.println("error: cant find item with id = " + id);
                return false;
            }
            if (!item.available(1)) {
                return false;
            }
            else {
                item.purchase(1);
                grocRepo.save(item);
            }
        }
        return true;
    }
}
