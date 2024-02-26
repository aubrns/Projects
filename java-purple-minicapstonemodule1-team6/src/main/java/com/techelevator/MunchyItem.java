package com.techelevator;

import java.math.BigDecimal;

public class MunchyItem extends InventoryItem {

    public MunchyItem(String name, BigDecimal price, String slot) {
        super(name,"Munchy", price, slot);
    }

    @Override
    public void getSound() {
        System.out.println("Crunch Crunch, Yum!");
    }
}
