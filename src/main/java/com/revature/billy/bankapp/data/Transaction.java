package com.revature.billy.bankapp.data;

import java.io.Serializable;

/**
 * Created by User on 8/9/2018.
 */
public class Transaction implements Serializable
{
    private String type;
    private double amount;
    private String date;
    private static final long serialVersionUID = -9207581780330964758L;

    public Transaction(String type, double amount, String date)
    {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String getType()
    {
        return type;
    }
    public double getAmount()
    {
        return amount;
    }
    public String getDate()
    {
        return date;
    }
}
