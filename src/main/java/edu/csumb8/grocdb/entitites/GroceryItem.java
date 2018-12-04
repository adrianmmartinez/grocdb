package edu.csumb8.grocdb.entitites;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GroceryItem")
public class GroceryItem {

    private String id;
    private String name;
    private int price;
    private String desc;
    private String type;
    private String img;
    private int items;


    public GroceryItem(String id, String name, int price, String desc, String type, int items) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.type = type;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public boolean available(int count) {
        if (this.items >= count) {
            return true;
        }
        return false;
    }

    /**
     * Function that purchases an amount `count` of an item.
     *
     * @param int count, count of items to purchase
     * @return boolean succesful purchase
     */
    public boolean purchase(int count) {
        if (this.items >= count) {
            this.items = this.items - count;
            return true;
        }
        return false;
    }

}
