package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineTest {

    VendingMachine sut;

        @Before
        public void setup(){
            sut = new VendingMachine("alternate.csv");
        }

        @Test
        public void inventoryMapReturns_true_for_A2(){
            Map<String, InventoryItem> inventory = sut.getInventoryMap();
            boolean actual = inventory.containsKey("A2");
            Assert.assertTrue(actual);
        }

        @Test
        public void inventoryMapReturns_true_for_C4(){
            Map<String, InventoryItem> inventory = sut.getInventoryMap();
            boolean actual = inventory.containsKey("C4");
            Assert.assertTrue(actual);
        }

        @Test
        public void inventoryMapReturns_false_for_Z3(){
            Map<String, InventoryItem> inventory = sut.getInventoryMap();
            boolean actual = inventory.containsKey("Z3");
            Assert.assertFalse(actual);
        }

         @Test
        public void inventoryMapReturns_false_for_B6(){
            Map<String, InventoryItem> inventory = sut.getInventoryMap();
            boolean actual = inventory.containsKey("B6");
            Assert.assertFalse(actual);
        }

          @Test
        public void inventoryMapReturns_CaramelBar_for_A3getName(){
            Map<String, InventoryItem> inventory = sut.getInventoryMap();
            InventoryItem item = inventory.get("A3");
            String expected = "Caramel Bar";
            String actual = item.getName();
            Assert.assertEquals(expected, actual);
        }

    @Test
    public void inventoryMapReturns_twoTwentyFive_for_A3getPrice(){
        Map<String, InventoryItem> inventory = sut.getInventoryMap();
        InventoryItem item = inventory.get("A3");
        BigDecimal expected = new BigDecimal(2.25);
        BigDecimal actual = item.getPrice();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inventoryMapReturns_Candy_for_A3getType(){
        Map<String, InventoryItem> inventory = sut.getInventoryMap();
        InventoryItem item = inventory.get("A3");
        String expected = "Candy";
        String actual = item.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inventoryMapReturns_five_for_A3getQuantity(){
        Map<String, InventoryItem> inventory = sut.getInventoryMap();
        InventoryItem item = inventory.get("A3");
        int expected = 5;
        int actual = item.getQuantity();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inventoryMapReturns_10quarters_for_twoFifty(){
        String expected = "10 quarters";
        String actual =  sut.getChange(new BigDecimal(2.50));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inventoryMapReturns_10quarters2dimes_for_twoSeventy(){
        String expected = "10 quarters 2 dimes";
        String actual =  sut.getChange(new BigDecimal(2.70));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inventoryMapReturns_1quarter1dime1nickel_for_fortyCents(){
        String expected = "1 quarter 1 dime 1 nickel";
        String actual =  sut.getChange(new BigDecimal(0.40));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inventoryMapReturns_4quarters1dime1nickel1penny_for_oneSixteen(){
        String expected = "4 quarters 1 dime 1 nickel 1 penny";
        String actual =  sut.getChange(new BigDecimal(1.16));
        Assert.assertEquals(expected, actual);
    }


}

