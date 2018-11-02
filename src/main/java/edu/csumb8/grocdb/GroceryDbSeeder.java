package edu.csumb8.grocdb;

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
        GroceryItem banana = new GroceryItem(null, "Banana", "$0.50/lb",
                "this is a banana", "produce", 35);
        GroceryItem pb = new GroceryItem(null, "Peanut Butter", "$2.50",
                "this is a banana", "produce", 35);

        grocRepo.deleteAll();
        List<GroceryItem> groceries = Arrays.asList(banana, pb);

        grocRepo.saveAll(groceries);

    }
}
