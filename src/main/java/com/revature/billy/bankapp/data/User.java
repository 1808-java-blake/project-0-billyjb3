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
    private String firstname;
    private String lastname;
    private boolean isAdmin;
    private boolean isOpen = true;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private static final long serialVersionUID = -9207581780330964758L;


    public User(String username, String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public void setLastname(String lastname){this.lastname = lastname;}
    public String getLastname(){return lastname;}
    public void setFirstname(String firstname){this.firstname = firstname;}
    public String getFirstname(){return firstname;}
    public void setUserid(int id){this.userid = id;}
    public int getUserid(){return userid;}
    public void addAccount(Account account)
    {
        accounts.add(account);
    }
    public void setAccounts(ArrayList<Account> accounts){this.accounts = accounts;}
    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }
    public String getUsername()
    {
        return username;
    }
    public void setAdmin(boolean isAdmin){this.isAdmin = isAdmin;}
    public boolean isAdmin()
    {
        return isAdmin;
    }
    public void setIsOpen(boolean isOpen){this.isOpen = isOpen;}
}
