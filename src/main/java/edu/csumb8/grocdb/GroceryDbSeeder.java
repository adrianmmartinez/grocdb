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
                "A banana is an edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa.", "produce", 35);
        GroceryItem pb = new GroceryItem(null, "Peanut Butter", 2,
                "Peanut butter is a food paste or spread made from ground dry-roasted peanuts. It often contains additional ingredients that modify the taste or texture, such as salt, sweeteners, or emulsifiers.", "produce", 35);
        GroceryItem oj = new GroceryItem(null, "Orange Juice", 4,
                "Orange juice is a liquid extract of the orange tree fruit, produced by squeezing oranges.", "Beverage", 35);
        GroceryItem stringCheese = new GroceryItem(null, "String Cheese", 3,
                "String cheese -- snack-sized servings of low-moisture mozzarella.", "Dairy", 35);
        GroceryItem bread = new GroceryItem(null, "Bread", 2,
                "Bread is a staple food prepared from a dough of flour and water, usually by baking.", "Baked-Goods", 35);
        GroceryItem tritip = new GroceryItem(null, "Tri-tip", 18,
                "Tri-tip steak is cut from a tri-tip roast, which is a small, triangular cut from the sirloin. ", "Meat", 35);
        GroceryItem coffee = new GroceryItem(null, "Coffee Beans", 9,
                "Coffee Contains Caffeine, a Stimulant That Can Enhance Brain Function and Boost Metabolism", "Coffee", 35);

        // Delete any existing items from db.
        grocRepo.deleteAll();

        // Add items to the db.
        List<GroceryItem> groceries = Arrays.asList(banana, pb, oj, stringCheese, bread, tritip, coffee);
        grocRepo.saveAll(groceries);

    }
}
