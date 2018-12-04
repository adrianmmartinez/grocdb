package edu.csumb8.grocdb;

/**
 * Used to add or delete items from the db
 */

import edu.csumb8.grocdb.entitites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GroceryDbSeeder implements CommandLineRunner {

    @Autowired
    IGroceryRepository grocRepo;

    @Override
    public void run(String... args) throws Exception {

        // Make Groceries here.
        GroceryItem banana = new GroceryItem(null, "Banana", 1,
                "this is a banana", "produce", 35);
        GroceryItem pb = new GroceryItem(null, "Peanut Butter", 2,
                "this is a banana", "produce", 35);
        GroceryItem oj = new GroceryItem(null, "Orange Juice", 4,
                "this is orange juice", "Beverage", 35);
        GroceryItem stringCheese = new GroceryItem(null, "String Cheese", 3,
                "this is string cheese", "Dairy", 35);
        GroceryItem bread = new GroceryItem(null, "Bread", 2,
                "this is bread", "Baked-Goods", 35);
        GroceryItem tritip = new GroceryItem(null, "Tri-tip", 18,
                "this is tri-tip", "Meat", 35);
        GroceryItem napkins = new GroceryItem(null, "Napkins", 2,
                "these are napkins", "Essentials", 35);
        GroceryItem coffee = new GroceryItem(null, "Coffee Beans", 9,
                "this is coffee beans", "Coffee", 35);

        // Delete any existing items from db.
        grocRepo.deleteAll();

        // Add items to the db.
        List<GroceryItem> groceries = Arrays.asList(banana, pb, oj, stringCheese, bread, tritip, napkins);
        grocRepo.saveAll(groceries);

    }
}
