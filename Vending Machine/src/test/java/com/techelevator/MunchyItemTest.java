package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MunchyItemTest {


    MunchyItem sut;

    @Before
    public void setup(){
        sut = new MunchyItem("Chips", new BigDecimal(2.75), "B2");
    }

    @Test
    public void returns_chip_for_getName(){
        String actual = sut.getName();
        String expected = "Chips";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_twoSevenFive_for_getPrice(){
        BigDecimal actual = sut.getPrice();
        BigDecimal expected = new BigDecimal(2.75);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_B2_for_getSlot(){
        String actual = sut.getSlot();
        String expected = "B2";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_five_for_getQuantity(){
        int actual = sut.getQuantity();
        int expected = 5;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_Munchy_for_getType(){
        String actual = sut.getType();
        String expected = "Munchy";
        Assert.assertEquals(expected, actual);
    }
}