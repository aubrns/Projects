package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override

    public GetTransferDTO getTransferInformation(int transferId) {
        //TODO: send/receiver userId, sender/receiveraccountId,
        // sender/receiverBalance transfer amount
        GetTransferDTO transfer = new GetTransferDTO();

        String sql = "SELECT\n" +
                "\ttransfer.transfer_id,transfer.sender_account_id,sender_user.username AS sender_username,\n" +
                "\ttransfer.receiver_account_id,receiver_user.username AS receiver_username,\n" +
                "\ttransfer.transfer_status,\n" +
                "\ttransfer.transfer_amount\n" +
                "FROM\n" +
                "\ttransfer\n" +
                "JOIN\n" +
                "\taccount ON transfer.sender_account_id = account.account_id\n" +
                "JOIN\n" +
                "\ttenmo_user AS sender_user ON account.user_id = sender_user.user_id\n" +
                "JOIN\n" +
                "\taccount AS receiver_account ON transfer.receiver_account_id = receiver_account.account_id\n" +
                "JOIN\n" +
                "\ttenmo_user AS receiver_user ON receiver_account.user_id = receiver_user.user_id\n" +
                "WHERE\n" +
                "\ttransfer.transfer_id = ?\n" +
                "ORDER BY\n" +
                "\tsender_user.username, receiver_user.username;";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,transferId);
        if(rs.next()){
            transfer.setSenderAccountId(rs.getInt("sender_account_id"));
            transfer.setReceiverAccountId(rs.getInt("receiver_account_id"));
            transfer.setTransferId(rs.getInt("transfer_id"));
            transfer.setTransferStatus(rs.getString("transfer_status"));
            transfer.setTransferAmount(rs.getDouble("transfer_amount"));
        }

        return transfer;
    }


    @Override
    public List<PostBalanceView> viewTransferList (int userId){
        String sql = "SELECT * FROM transfer";
        List <PostBalanceView> postBalanceViews = new ArrayList<>();


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId, userId, userId);

        while(results.next()){
            int transferId = results.getInt("transfer_id");
            double transferAmount = results.getDouble("transfer_amount");
            String senderUsername = results.getString("sender_username");
            String receiverUsername = results.getString("receiver_username");

            PostBalanceView postBalanceView1 = new PostBalanceView(transferId, transferAmount, senderUsername, receiverUsername);
            postBalanceViews.add(postBalanceView1);
        }
        return postBalanceViews;
    }

    public String getUserById (int id){
        String sql = "SELECT username FROM tenmo_user "+
          "JOIN account on account.user_id = tenmo_user.user_id "+
        "WHERE tenmo_user.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if(results.next()){
            return results.getString("username");
        }
        return null;
    }






    @Override
    public TransferDTO sendTransfer1(int senderAccountId, int receiverAccountId, double transferAmount) {

        TransferDTO newTransfer = new TransferDTO();



        // Check if sender has enough balance
        String getSenderBalanceSql = "SELECT balance, account_id, user_id FROM account WHERE user_id = ?;";
        SqlRowSet senderBalanceResult = jdbcTemplate.queryForRowSet(getSenderBalanceSql, senderAccountId);
        SqlRowSet receiverBalanceResult = jdbcTemplate.queryForRowSet(getSenderBalanceSql, receiverAccountId);


        int actualSenderAccountId = 0;
        int actualReceiverAccountId = 0;
        String receiverUsername = null;
        String senderUsername = null;
        if (senderBalanceResult.next() && receiverBalanceResult.next()) {
            double myBalance = senderBalanceResult.getDouble("balance");
            actualSenderAccountId = senderBalanceResult.getInt("account_id");
            actualReceiverAccountId = receiverBalanceResult.getInt("account_id");
            receiverUsername = getUserById(receiverBalanceResult.getInt("user_id"));
            senderUsername = getUserById(senderBalanceResult.getInt("user_id"));
            if (senderBalanceResult.getDouble("balance") < transferAmount) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender cannot be receiver");
            }
        }


        // Insert transfer record
        String sqlInsertTransfer = "INSERT INTO transfer (sender_account_id, receiver_account_id, transfer_status, transfer_amount) VALUES (?, ?, 'Approved', ?) RETURNING transfer_id;";
        int transferId = jdbcTemplate.queryForObject(sqlInsertTransfer, Integer.class, actualSenderAccountId, actualReceiverAccountId, transferAmount);


        // Update sender's balance
        String sqlUpdateSenderBalance = "UPDATE account SET balance = balance - ? WHERE account_id = ?;";
        jdbcTemplate.update(sqlUpdateSenderBalance, transferAmount, senderAccountId);


        // Update receiver's balance
        String sqlUpdateReceiverBalance = "UPDATE account SET balance = balance + ? WHERE account_id = ?;";
        jdbcTemplate.update(sqlUpdateReceiverBalance, transferAmount, receiverAccountId);


        // Set properties of newTransfer from the inserted transfer
        String sqlSelectTransfer = "SELECT transfer_id, sender_account_id, receiver_account_id, transfer_status, transfer_amount FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectTransfer, transferId);


        if (results.next()) {
            newTransfer.setTransferId(results.getInt("transfer_id"));
            newTransfer.setTransferAmount(results.getDouble("transfer_amount"));
            newTransfer.setFrom(senderUsername);
            newTransfer.setTo(receiverUsername);

        }


        return newTransfer;

    }









    private Transfer mapRowToTransfer (SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setUserId(rs.getInt("user_id"));
        transfer.setSenderAccountId(rs.getInt("sender_account_id"));
        transfer.setReceiverAccountId(rs.getInt("receiver_account_id"));
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferStatus(rs.getString("transfer_status"));
        transfer.setTransferAmount(rs.getDouble("transfer_amount"));
        return transfer;
    }
}
