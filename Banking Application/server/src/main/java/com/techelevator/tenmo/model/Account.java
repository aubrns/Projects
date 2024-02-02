package com.techelevator.tenmo.model;

import com.techelevator.tenmo.dao.UserDao;

public class Account {

    private int accountId;

    private double accountBalance = 1000.00;

    boolean activeAccount;
    private int user_id;

    public Account(int accountId, double accountBalance, boolean activeAccount) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.activeAccount = activeAccount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean isActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(boolean activeAccount) {
        this.activeAccount = activeAccount;
    }



    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountBalance=" + accountBalance +
                ", activeAccount=" + activeAccount +
                '}';
    }
}
