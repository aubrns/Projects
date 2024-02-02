package com.techelevator;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;

public class CandyItem extends InventoryItem{

    public CandyItem(String name, BigDecimal price, String slot) {
        super(name, "Candy", price, slot);
    }

    @Override
    public void getSound() {
        System.out.println("Yummy Yummy, So Sweet!");
    }
}
