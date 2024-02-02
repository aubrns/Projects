package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.*;

import java.util.List;

public interface TransferDao {

    //todo: user_id, account_id, sender_balance, receiver_balance, transfer_status


    GetTransferDTO getTransferInformation (int transferId);

    List<PostBalanceView> viewTransferList(int userId);

    TransferDTO sendTransfer1(int senderAccountId, int receiverAccountId, double transferAmount);



}
