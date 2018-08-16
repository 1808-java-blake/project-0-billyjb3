package com.revature.billy.bankapp.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 8/9/2018.
 */
public class Account implements Serializable
{
    private String type;
    private String id;
    private double balance;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static final long serialVersionUID = -9207581780330964758L;
    private boolean isOpen = true;

    public Account(String type, String id, double balance)
    {
        this.type = type;
        this.id = id;
        this.balance = balance;
    }

    public void closeAccount()
    {
        isOpen = false;
    }
    public void addTransaction(Transaction transaction)
    {
        transactions.add(transaction);
        balance += transaction.getAmount();
    }
    public ArrayList<Transaction> getTransactions()
    {
        return transactions;
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
