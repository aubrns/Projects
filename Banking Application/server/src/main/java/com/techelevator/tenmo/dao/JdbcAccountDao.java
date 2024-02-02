package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}


    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, balance, user_id FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Account account = mapRowToAccount(results);
            accounts.add(account);

        }
         return accounts;
    }

    @Override
    public Account findAccountById(int accountId) {
        Account account = new Account();

        String sql = "SELECT account_id, balance FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()){
            account.setAccountId(results.getInt("account_id"));
            account.setAccountBalance(results.getDouble("balance"));

        }


        return account;
    }

    // I don't know how to do this becauae it really doesn't make sense to check for it this way
    // this is why i added the second method which searches for the info about the active account with the accountID
    @Override
    public boolean activateAccount(boolean activeAccount) {
        return false;
    }

    @Override
    public boolean activateAccount(int accountId) {

        String sql = "SELECT activeAccount FROM account WHERE accountId = ?";

        // Execute the SQL query and retrieve the result
        Boolean isActive = jdbcTemplate.queryForObject(sql, Boolean.class, accountId);

        // If isActive is not null, return its boolean value; otherwise, return false
        return isActive != null ? isActive : false;
    }


    private Account mapRowToAccount(SqlRowSet rs){
        Account account = new Account();
        account.setAccountBalance(rs.getDouble("balance"));
        account.setAccountId(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        return account;
    }

}
