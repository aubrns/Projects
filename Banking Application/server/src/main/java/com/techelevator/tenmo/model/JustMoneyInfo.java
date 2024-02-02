package com.techelevator.tenmo.model;

public class JustMoneyInfo {

    private int transferId;
    private double transferAmount;
    private String senderUsername;
    private String receiverUsername;

    public JustMoneyInfo(int transferId, double transferAmount, String senderUsername, String receiverUsername) {
        this.transferId = transferId;
        this.transferAmount = transferAmount;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    public JustMoneyInfo() {
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

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
