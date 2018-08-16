package com.revature.billy.bankapp.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 8/9/2018.
 */
public class User implements Serializable
{
    private int userid;
    private String username;
    private String password;
    private boolean isAdmin;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private static final long serialVersionUID = -9207581780330964758L;


    public User(String username, String password, boolean isAdmin)
    {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public void addAccount(Account account)
    {
        accounts.add(account);
    }

    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }

    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public boolean isAdmin()
    {
        return isAdmin;
    }
}
