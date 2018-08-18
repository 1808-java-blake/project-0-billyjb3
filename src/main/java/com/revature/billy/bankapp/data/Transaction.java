package com.revature.billy.bankapp.data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Created by User on 8/9/2018.
 */
public class Transaction implements Serializable
{
    private int transactionid;
    private int accountid;
    private String type;
    private double amount;
    private Date date;
    private static final long serialVersionUID = -9207581780330964758L;

    public Transaction(String type, double amount, Date date)
    {
        this.type = type;
        this.amount = amount;
        this.date = date;
        transactionid = -1;
        accountid = -1;
    }

    public int getAccountid()
    {
        return accountid;
    }
    public void setAccountid(int accountid)
    {
        this.accountid = accountid;
    }
    public void setTransactionid(int id){this.transactionid = id;}
    public int getTransactionid(){return transactionid;}
    public String getType()
    {
        return type;
    }
    public double getAmount()
    {
        return amount;
    }
    public Date getDate()
    {
        return date;
    }
}
