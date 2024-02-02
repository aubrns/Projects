package com.techelevator;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class InventoryItem {
    //     TODO AUSTIN
//     TODO Properties: Name, Price, Type, Quantity (default 5)
    private String name;
    private String type;
    private BigDecimal price;
    private int quantity;
    private String slot;

//     Constructor that takes parameters (Name, Price, Type) (done)
//      Methods:
//         abstract getSound (different for each type/subclass of vending items) (done)
//         updateInventory (subtracts 1 from inventory) (done)

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSlot() {
        return slot;
    }

    public InventoryItem(String name, String type, BigDecimal price, String slot) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = 5;
        this.slot = slot;
    }

    public void updateInventory (){
        quantity -= 1;
    }

    // 4 subclasses: Munchy, Drink, Candy, and Gum
    public abstract void getSound();






}
