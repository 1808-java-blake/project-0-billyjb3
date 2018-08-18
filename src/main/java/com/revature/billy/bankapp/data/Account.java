package com.revature.billy.bankapp.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 8/9/2018.
 */
public class Account implements Serializable
{
    private String type;
    private int accountid;
    private int userid;
    private double balance;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static final long serialVersionUID = -9207581780330964758L;
    private boolean isOpen = true;
    private boolean updated = false;

    public Account(String type, int userid)
    {
        this.type = type;
        this.userid = userid;
        accountid = -1;
        balance = 0;
    }

    public int getUserid()
    {
        return userid;
    }
    public boolean isOpen()
    {
        return isOpen;
    }
    public void setIsOpen(boolean isOpen)
    {
        this.isOpen = isOpen;
    }
    public void addTransaction(Transaction transaction)
    {
        transactions.add(transaction);
        balance += transaction.getAmount();
    }
    public void setUpdated(boolean updated)
    {
        this.updated = updated;
    }
    public ArrayList<Transaction> getTransactions()
    {
        return transactions;
    }
    public void setTransactions(ArrayList<Transaction> transactions){this.transactions = transactions;}
    public String getType()
    {
        return type;
    }
    public int getID()
    {
        return accountid;
    }
    public void setID(int id){this.accountid = id;}
    public double getBalance()
    {
        return balance;
    }
    public void setBalance(double balance){this.balance = balance;}
}
