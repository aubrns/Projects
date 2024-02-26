package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class GumItemTest {

    GumItem sut;

    @Before
    public void setup(){
        sut = new GumItem("Juicy Fruit", new BigDecimal(3.50), "A3");
    }

    @Test
    public void returns_juicyFruit_for_getName(){
        String actual = sut.getName();
        String expected = "Juicy Fruit";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_threeFifty_for_getPrice(){
        BigDecimal actual = sut.getPrice();
        BigDecimal expected = new BigDecimal(3.50);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_A3_for_getSlot(){
        String actual = sut.getSlot();
        String expected = "A3";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_five_for_getQuantity(){
        int actual = sut.getQuantity();
        int expected = 5;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_Gum_for_getType(){
        String actual = sut.getType();
        String expected = "Gum";
        Assert.assertEquals(expected, actual);
    }
}