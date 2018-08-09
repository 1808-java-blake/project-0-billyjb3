package com.revature.billy.bankapp.data;

/**
 * Created by User on 8/9/2018.
 */
public class User
{
    String username;
    Account[] accounts;

    public User(String username, Account[] accounts)
    {
        this.username = username;
        this.accounts = accounts;
    }

    public String getUsername()
    {
        return username;
    }
}
