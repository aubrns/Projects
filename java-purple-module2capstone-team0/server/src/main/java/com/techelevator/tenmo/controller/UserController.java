package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    private UserDao userDao;

    private AccountDao accountDao;



    public UserController(UserDao userDao, AccountDao accountDao){
        this.userDao = userDao;
        this.accountDao = accountDao;
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers(String username , Principal principal){
        return userDao.findAll(principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/users/usernames", method = RequestMethod.GET)
    public List<JustUsername> getJustUsersNames(String username, Principal principal){
        return userDao.findAllUsername(principal.getName());
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
    public UserAccountView getAccountView(@PathVariable int userId){

        return userDao.findAccount(userId);
        }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path ="/users/accounts", method = RequestMethod.GET)
    public List<Account> viewAccount(){
        List<Account> accountInfo = new ArrayList<>();
        return accountDao.findAll();
    }


}
