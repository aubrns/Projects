package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.JustUsername;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserAccountView;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        String sql = "SELECT user_id FROM tenmo_user WHERE username ILIKE ?;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if (id != null) {
            return id;
        } else {
            return -1;
        }
    }

    @Override
    public List<User> findAll(String username) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public List<JustUsername> findAllUsername(String username) {
        List<JustUsername> users = new ArrayList<>();
        String sql = "SELECT username FROM tenmo_user;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            if(results.getString("username").equals(username)){
                continue;
            }
            JustUsername justUsername = new JustUsername(results.getString("username"));
            users.add(justUsername);
        }
        return users;
    }

    @Override
    public UserAccountView findAccount(int userID){
        UserAccountView userAccountView = null;
        String sql = "SELECT username, balance FROM  account JOIN tenmo_user ON tenmo_user.user_id = account.user_id WHERE account.user_id = " +
                "?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userID);


       if(results.next()) {
          userAccountView = new UserAccountView(results.getString("username"),results.getDouble("balance") );

            // sql rowset results
            /// create user accountview object
            // add to a list of user account view

       }
        return userAccountView;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user WHERE username ILIKE ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()){
            return mapRowToUser(rowSet);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password) {

        // create user
        String sql = "INSERT INTO tenmo_user (username, password_hash) VALUES (?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        Integer newUserId;
        try {
            newUserId = jdbcTemplate.queryForObject(sql, Integer.class, username, password_hash);
        } catch (DataAccessException e) {
            return false;
        }

        // TODO: Create the account record with initial balance

       String sqlAccount = "INSERT INTO account (user_id, balance) VALUES (?, ?) RETURNING account_id";
        double initialBalance = 1000.00;
            int accountId;
        try {
            accountId = jdbcTemplate.queryForObject(sqlAccount,Integer.class, newUserId, initialBalance);
        } catch (DataAccessException e){
            return false;
        }
        return true;
    }




    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }


}
