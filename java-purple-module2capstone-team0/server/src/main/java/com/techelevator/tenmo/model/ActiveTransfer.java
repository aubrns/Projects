package com.techelevator.tenmo.model;

public class ActiveTransfer {

    private int sendUserId;
    private int receiveUserId;
    private double transferAmount;
    private double senderBalance;
    private double receiverBalance;

    public ActiveTransfer(int sendUserId, int receiveUserId, double transferAmount, double senderBalance, double receiverBalance) {
        this.sendUserId = sendUserId;
        this.receiveUserId = receiveUserId;
        this.transferAmount = transferAmount;
        this.senderBalance = senderBalance;
        this.receiverBalance = receiverBalance;
    }

    public ActiveTransfer() {
    }

    public int getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(int sendUserId) {
        this.sendUserId = sendUserId;
    }

    public int getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(int receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public double getSenderBalance() {
        return senderBalance;
    }

    public void setSenderBalance(double senderBalance) {
        this.senderBalance = senderBalance;
    }

    public double getReceiverBalance() {
        return receiverBalance;
    }

    public void setReceiverBalance(double receiverBalance) {
        this.receiverBalance = receiverBalance;
    }

    public ActiveTransfer makeTransfer(int sendUserId, int receiveUserId, double transferAmount, double senderBalance, double receiverBalance){
        ActiveTransfer transfer = new ActiveTransfer(sendUserId, receiveUserId,transferAmount, senderBalance,receiverBalance);
        return transfer;
    }
}
