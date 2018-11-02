package edu.csumb8.grocdb;

import edu.csumb8.grocdb.entitites.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface IGroceryRepository extends MongoRepository<GroceryItem, String> {

    @Query (value = "{'id':?0}")
    GroceryItem findByRepoId(String id);

}
