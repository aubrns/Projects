package com.techelevator.tenmo.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UserAccountView {

    private String username;
    private double balance = 1000.00;

    public UserAccountView(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public UserAccountView() {
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private UserAccountView mapRowToUserAccountView(SqlRowSet rs){

        UserAccountView userAccountView = new UserAccountView();
        userAccountView.setBalance(rs.getDouble("balance"));
        userAccountView.setUsername(rs.getString("username"));

        return userAccountView;
    }

    @Override
    public String toString() {
        return "UserAccountView{" +
                "username='" + username + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
