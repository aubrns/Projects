package com.techelevator.tenmo.model;

import java.util.ArrayList;
import java.util.List;

public class Transfer {

    private int userId;

    private int transferId;
    private String transferStatus;

    private int senderAccountId;
    private int receiverAccountId;
    private double transferAmount;


    public Transfer(int userId, int transferId, String transferStatus, int senderAccountId, int receiverAccountId, double transferAmount) {
        this.userId = userId;
        this.transferId = transferId;
        this.transferStatus = transferStatus;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.transferAmount = transferAmount;
    }

    public Transfer() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }



    public int getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(int senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(int receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }


    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }




    public List<User> goodUser(String username){
        List<User> activeUser = new ArrayList<>();

        if(username != null){
            User user = new User(username);
            activeUser.add(user);
        }
        return activeUser;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "userId=" + userId +
                ", transferId=" + transferId +
                ", transferStatus='" + transferStatus + '\'' +
                ", senderAccountId=" + senderAccountId +
                ", receiverAccountId=" + receiverAccountId +
                ", transferAmount=" + transferAmount +
                '}';
    }
}
