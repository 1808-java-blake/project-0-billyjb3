package com.revature.billy.bankapp.data;

/**
 * Created by User on 8/9/2018.
 */
public class Account
{
    String type;
    String id;
    double balance;
    Transaction[] transactions;

    public Account(String type, String id, double balance)
    {
        this.type = type;
        this.id = id;
        this.balance = balance;
    }

    public String getType()
    {
        return type;
    }
    public String getID()
    {
        return id;
    }
    public double getBalance()
    {
        return balance;
    }
}
