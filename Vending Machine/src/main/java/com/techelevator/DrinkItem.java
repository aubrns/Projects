package com.techelevator;

import java.math.BigDecimal;

public class DrinkItem extends InventoryItem{

    public DrinkItem(String name, BigDecimal price, String slot) {
        super(name,"Drink", price, slot);
    }

    @Override
    public void getSound() {
        System.out.println("Glug Glug, Yum!");
    }
}
