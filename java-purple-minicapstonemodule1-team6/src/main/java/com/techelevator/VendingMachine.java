package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.security.Key;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

public class VendingMachine {

    Scanner userInput = new Scanner(System.in);

    // SARAH
    // Properties: Map<String, VendingItem>, currentMoney
    private BigDecimal currentMoney = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_HALF_UP);
    private Map<String, InventoryItem> inventoryMap = new HashMap<>();
    boolean secondPurchase = false;

    //  Constructor: takes parameter Map<String, VendingItem>

    public VendingMachine(String filePath) {
        // change this constructor so that it takes the csv file and creates the map within the constructor
        Map<String, InventoryItem> inventory = loadMap(filePath);
        inventoryMap = inventory;
    }

    public Map<String, InventoryItem> getInventoryMap() {
        return inventoryMap;
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public void isSecondPurchase(boolean secondPurchase) {
        this.secondPurchase = secondPurchase;
    }

    public void setSecondPurchase(boolean secondPurchase) {
        this.secondPurchase = secondPurchase;
    }

    public Map<String, InventoryItem> getInventoryList() {
        return inventoryMap;
    }



    public Map <String, InventoryItem> loadMap(String filePath) {
        File inventoryCSV = new File(filePath);
        Map<String, InventoryItem> inventory = new HashMap<>();

        try (Scanner reader = new Scanner(inventoryCSV)) {
            while (reader.hasNextLine()) {
                String currentLine = reader.nextLine();
                String[] itemInfo = currentLine.split(",");
                String slot = itemInfo[0];
                String name = itemInfo[1];
                BigDecimal price = new BigDecimal(Double.parseDouble(itemInfo[2])).setScale(2, BigDecimal.ROUND_HALF_UP);
                String type = itemInfo[3];

                InventoryItem item;
                if (type.equals("Munchy")) {
                    item = new MunchyItem(name, price, slot);
                } else if (type.equals("Gum")) {
                    item = new GumItem(name, price, slot);
                } else if (type.equals("Drink")) {
                    item = new DrinkItem(name, price, slot);
                } else { // Item is Candy
                    item = new CandyItem(name, price, slot);
                }

                inventory.put(slot, item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return inventory;
    }


    public List getDisplayList() {
        List<String> displayList = new ArrayList<>();
        for (Map.Entry<String, InventoryItem> currentSet : inventoryMap.entrySet()) {
            String slot = currentSet.getKey();
            InventoryItem item = currentSet.getValue();
            String itemPrintout;
            if (item.getQuantity() <= 0) {
                itemPrintout = slot + " " + item.getName() + " $" + item.getPrice() + " QUANTITY: OUT OF STOCK";
            } else {
                itemPrintout = slot + " " + item.getName() + " $" + item.getPrice() + " QUANTITY:" + item.getQuantity();
            }
            displayList.add(itemPrintout);
        }
        Collections.sort(displayList);
        return displayList;
    }


        // feedMoney (innerMethod: printToSalesLog(String, double))
    public void feedMoney(BigDecimal money, File salesLog) {
        currentMoney = currentMoney.add(money);
        System.out.println("$" + money + " accepted. You have $" + currentMoney + " to spend!");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesLog, true))){
            writer.println(LocalDateTime.now() + " FEED MONEY: $" + money + " $" + getCurrentMoney());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
        // selectProduct
    public void selectProduct(File salesLog) {
        System.out.print("Please enter the key to the item you would like to purchase: ");
        String key = userInput.nextLine().toUpperCase();
        if (inventoryMap.containsKey(key)) {
            InventoryItem item = inventoryMap.get(key);
            if (item.getQuantity() <= 0) {
                System.out.println("Item is out of stock.");
                System.out.println("\n");
            } else{
                buyProduct(item, salesLog);
            }
        } else {
            System.out.println("Invalid item key");
            System.out.println("\n");
        }

        }

        public void buyProduct(InventoryItem item, File salesLog) {
            BigDecimal bogodo = new BigDecimal("1.00");
        // TODO This is wrong. We need to have two if statements for if it's the second item for the discount
            if (secondPurchase == false && item.getPrice().compareTo(currentMoney) == -1 || item.getPrice().compareTo(currentMoney) == 0) {
                item.updateInventory();
                currentMoney = currentMoney.subtract(item.getPrice());
                System.out.println(item.getName() + " costs $" + item.getPrice() + ". You have $" + currentMoney + " left to spend.");
                item.getSound();
                System.out.println("\n");
                setSecondPurchase(true);

                try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesLog, true))){
                    writer.println(LocalDateTime.now() + " " + item.getName() + " " + item.getSlot() + " $" + item.getPrice() + " " + currentMoney);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }

            } else if (secondPurchase == true && (item.getPrice().subtract(bogodo).compareTo(currentMoney) == -1 || item.getPrice().subtract(bogodo).compareTo(currentMoney) == 0)) {
                item.updateInventory();
                currentMoney = currentMoney.subtract(item.getPrice().subtract(bogodo));
                System.out.println("BOGODO SALE! $1.00 off!!");
                System.out.println(item.getName() + " costs $" + item.getPrice().subtract(bogodo) + ". You have $" + currentMoney + " left to spend.");
                item.getSound();
                System.out.println("\n");
                setSecondPurchase(false);

                try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesLog, true))){
                    writer.println(LocalDateTime.now() + " " + item.getName() + " " + item.getSlot() + " " + item.getPrice().subtract(bogodo) + " " + currentMoney);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }

            } else {
                System.out.println("You don't have enough money!");
                System.out.println("\n");
            }

    }
        // finishTransaction
    public void finishTransaction(File salesLog) {

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesLog, true))){
            writer.println(LocalDateTime.now() + "GIVE CHANGE: " + currentMoney + " $0.00");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Here's your change:");
        String change = getChange(currentMoney);
        System.out.println(change);


        System.out.println("\n\n");
    }

    public String getChange(BigDecimal currentMoney) {
        // TODO Make this return String of change
        BigDecimal quartersBD = (currentMoney.divide(new BigDecimal("0.25"))).setScale(2, BigDecimal.ROUND_HALF_UP);
        int quarters = quartersBD.intValue();
        BigDecimal subract = new BigDecimal(quarters * 0.25);
        currentMoney = currentMoney.subtract(subract);
        BigDecimal dimesBD = (currentMoney.divide(new BigDecimal("0.10"))).setScale(2, BigDecimal.ROUND_HALF_UP);
        int dimes = dimesBD.intValue();
        subract = new BigDecimal(dimes * 0.10);
        currentMoney = currentMoney.subtract(subract);
        BigDecimal nickelsBD = (currentMoney.divide(new BigDecimal("0.05"))).setScale(2, BigDecimal.ROUND_HALF_UP);
        int nickels = nickelsBD.intValue();
        subract = new BigDecimal(nickels * 0.05);
        currentMoney = currentMoney.subtract(subract);
        BigDecimal penniesBD = (currentMoney.divide(new BigDecimal("0.01"))).setScale(2, BigDecimal.ROUND_HALF_UP);
        int pennies = penniesBD.intValue();
        subract = new BigDecimal(pennies * 0.01);
        currentMoney = currentMoney.subtract(subract);

        String quarterString = "";
        String dimeString = "";
        String nickelString = "";
        String pennyString = "";

        String change = "";

        if (quarters <= 0) {
            // Nothing added
        } else if (quarters == 1) {
            quarterString = quarters + " quarter";
            change = change + quarterString + " ";
        } else {
            quarterString = quarters + " quarters";
            change = change + quarterString + " ";
        }

        if (dimes <= 0) {
            // Nothing added
        } else if (dimes == 1) {
            dimeString = dimes + " dime";
            change = change + dimeString + " ";
        } else {
            dimeString = dimes + " dimes";
            change = change + dimeString + " ";
        }

        if (nickels <= 0) {
            // Nothing added
        } else if (nickels == 1) {
            nickelString = nickels + " nickel" + " ";
            change = change + nickelString;
        } else {
            nickelString = nickels + "nickels" + " ";
            change = change + nickelString;
        }

        if (pennies <= 0) {
            // Nothing added
        } else if (pennies == 1) {
            pennyString = pennies + " penny";
            change = change + pennyString;
        } else {
            pennyString = pennies + " pennies";
            change = change + pennyString;
        }

        change = change.trim();
        return change;
    }

    public File turnOn(String filePath) {
        File salesLog = new File(filePath);

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesLog, true))){
            writer.println(LocalDateTime.now() + " Machine has been turned on");
        } catch (IOException e) {
            e.getMessage();
        }

        return salesLog;
    }

    public void turnOff(File salesLog) {

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesLog, true))){
            writer.println(LocalDateTime.now() + " We're done for the day");
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
