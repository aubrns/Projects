package com.techelevator.tenmo.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class PostBalanceView {

    private int transferId;
    private double transferAmount;

    private String fromUser;
    private String toUser;

    public PostBalanceView(int transferId, double transferAmount, String fromUser, String toUser) {
        this.transferId = transferId;
        this.transferAmount = transferAmount;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public PostBalanceView() {
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public PostBalanceView mapRowToPostBalanceView (SqlRowSet rs){
        PostBalanceView postBalanceView = new PostBalanceView();

        postBalanceView.setFromUser(rs.getString("fromUser"));
        postBalanceView.setToUser(rs.getString("toUser"));
        postBalanceView.setTransferAmount(rs.getDouble("transferAmount"));
        postBalanceView.setTransferId(rs.getInt("transferId"));

        return postBalanceView;
    }
}
