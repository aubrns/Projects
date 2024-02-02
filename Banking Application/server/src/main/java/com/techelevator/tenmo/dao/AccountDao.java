package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;


import java.util.List;

public interface AccountDao {



    List<Account> findAll();

    Account findAccountById (int accountId);
    boolean activateAccount (boolean activeAccount);

    boolean activateAccount(int accountId);
}
