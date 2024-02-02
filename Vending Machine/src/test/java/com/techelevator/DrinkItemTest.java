package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class DrinkItemTest {

    DrinkItem sut;

    @Before
    public void setup(){
        sut = new DrinkItem("Pepsi", new BigDecimal(3), "C4");
    }

    @Test
    public void returns_pepsi_for_getName(){
        String actual = sut.getName();
        String expected = "Pepsi";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_three_for_getPrice(){
        BigDecimal actual = sut.getPrice();
        BigDecimal expected = new BigDecimal(3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_C4_for_getSlot(){
        String actual = sut.getSlot();
        String expected = "C4";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_five_for_getQuantity(){
        int actual = sut.getQuantity();
        int expected = 5;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_Drink_for_getType(){
        String actual = sut.getType();
        String expected = "Drink";
        Assert.assertEquals(expected, actual);
    }
}