package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLOutput;

public class GumItem extends InventoryItem{

    public GumItem(String name, BigDecimal price, String slot) {
        super(name, "Gum", price, slot);
    }

    @Override
    public void getSound() {
        System.out.println("Chew Chew, Yum!");
    }
}
