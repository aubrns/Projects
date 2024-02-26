package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CandyItemTest {

    CandyItem sut;

    @Before
    public void setup(){
        sut = new CandyItem("skittles", new BigDecimal(1.50), "A1");
    }

    @Test
    public void returns_skittles_for_getName(){
        String actual = sut.getName();
        String expected = "skittles";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_oneFifty_for_getPrice(){
        BigDecimal actual = sut.getPrice();
        BigDecimal expected = new BigDecimal(1.5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_A1_for_getSlot(){
        String actual = sut.getSlot();
        String expected = "A1";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_five_for_getQuantity(){
        int actual = sut.getQuantity();
        int expected = 5;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_candy_for_getType(){
        String actual = sut.getType();
        String expected = "Candy";
        Assert.assertEquals(expected, actual);
    }
}