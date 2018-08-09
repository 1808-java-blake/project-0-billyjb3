package com.revature.billy.bankapp.data;

/**
 * Created by User on 8/9/2018.
 */
public class Transaction
{
    private String type;
    private double amount;
    private String date;

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
