package com.techelevator.tenmo.model;

public class GetTransferDTO {


      private int senderAccountId;
      private int receiverAccountId;
      private int transferId;
      private String transferStatus;
      private double transferAmount;


    public GetTransferDTO(int senderAccountId, int receiverAccountId, int transferAccountId, String transferStatus, double transferAmount) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.transferId = transferAccountId;
        this.transferStatus = transferStatus;
        this.transferAmount = transferAmount;
    }

    public GetTransferDTO() {
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

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }
}
