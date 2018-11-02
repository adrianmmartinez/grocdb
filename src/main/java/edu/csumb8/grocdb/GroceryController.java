package edu.csumb8.grocdb;


import edu.csumb8.grocdb.entitites.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroceryController {

    @Autowired
    IGroceryRepository grocRepo;

    @GetMapping ("/getAll")
    public List<GroceryItem> getAll () {
        List<GroceryItem> result = grocRepo.findAll();
        System.out.println(result.toString());
        return result;
    }

    @GetMapping ("/id/{id}")
    public GroceryItem getItemById (@PathVariable String id) {
        GroceryItem item = grocRepo.findByRepoId(id);
        return item;
    }

    @GetMapping ("/available/{id}/{amount}")
    public boolean checkAvailability (@PathVariable String id, @PathVariable int amount) {
        GroceryItem item = grocRepo.findByRepoId(id);
        boolean canPurchase = item.available(amount);
        return canPurchase;
    }

    @PostMapping("/purchase")
    public boolean purchase (@RequestParam String id, @RequestParam int amount) {
        GroceryItem item = grocRepo.findByRepoId(id);
        if(item == null) {
            System.out.println("error: cant find item with id = " + id);
            return false;
        }
        boolean purchased = item.purchase(amount);
        System.out.println(item.getItems());
        grocRepo.save(item);
        return purchased;
    }
}
