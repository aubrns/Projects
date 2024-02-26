package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.*;

import java.security.Policy;
import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.dsig.TransformService;

@RestController
public class TransferController {

    private TransferDao transferDao;
    private AccountDao accountDao;

    private UserDao userDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }




    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "transfers/sendMoney", method = RequestMethod.POST)
    public TransferDTO makeTransfer(@RequestBody TransferDTO newTransfer) {

        return transferDao.sendTransfer1(userDao.findIdByUsername(newTransfer.getFrom()),userDao.findIdByUsername(newTransfer.getTo()), newTransfer.getTransferAmount());

    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/transfers/myTransfers/{userId}", method = RequestMethod.GET)
    public List <PostBalanceView> getViewTransferLIst (@PathVariable int userId){
        return transferDao.viewTransferList(userId);
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/transfers/showTransfers/{transferId}", method = RequestMethod.GET)
    public GetTransferDTO getTransferById (@PathVariable int transferId){
        return transferDao.getTransferInformation(transferId);
    }
}
