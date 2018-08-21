package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.data.Account;
import com.revature.billy.bankapp.data.Transaction;
import com.revature.billy.bankapp.data.User;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by User on 8/11/2018.
 */
public class AdminScreen extends Screen
{

    @Override
    public Screen start()
    {
        System.out.println("\nWelcome to admin control\n");
        displayCommands();
        return handleInput();
    }

    @Override
    protected void displayCommands()
    {
        System.out.println("\t\t\t\t---commands---");
        System.out.println("To view all users:\t\t\t\t\t\tusers");
        System.out.println("To view user information\t\t\t\tuser <username>");
        System.out.println("To view a users account info:\t\t\taccount <username>");
        System.out.println("To view transactions on an account:\t\ttrans <username> <account number>");
        System.out.println("To logout and return to home screen:\tlogout");
        System.out.println("To view commands:\t\t\t\t\t\thelp");
    }

    @Override
    protected Screen handleInput()
    {
        String[] input = scanner.nextLine().toLowerCase().trim().split(" ");
        for(int i = 0; i < input.length; i++)
            input[i].trim();
        if (input[0].equals("users"))
            return viewUsers();
        else if(input[0].equals("user"))
            return viewUser(input);
        else if (input[0].equals("accounts"))
            return viewAccounts(input);
        else if(input[0].equals("trans"))
            return viewTransactions(input);
        else if(input[0].equals("logout"))
            return new HomeScreen();
        else if(input[0].equals("help"))
        {
            displayCommands();
            return handleInput();
        }
        else
        {
            System.out.println("'"+input[0]+"' not recognized");
            return start();
        }
    }

    public Screen viewUser(String[] input)
    {
        if(input.length != 2)
        {
            System.out.println("Usage: user <username>");
            return handleInput();
        }
        User user = data.getUser(input[1]);
        if(user == null)
        {
            System.out.println("Could not find user with the username: "+input[1]);
            return handleInput();
        }
        String username = input[1];
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        int userid = user.getUserid();
        boolean isAdmin = user.isAdmin();
        boolean isOpen = user.isOpen();
        System.out.println("Username: "+username);
        System.out.println("User ID: "+userid);
        System.out.println("First Name: "+firstname);
        System.out.println("Last Name: "+lastname);
        System.out.println("Admin: "+isAdmin);
        System.out.println("Active: "+isOpen);
        return handleInput();
    }

    public Screen viewUsers()
    {
        ArrayList<String> users = data.getUsernames();
        for(int i = 0; i < users.size(); i++)
            System.out.println(users.get(i));
        return handleInput();
    }

    public Screen viewAccounts(String[] input)
    {
        if(input.length != 2)
        {
            System.out.println("Must include username");
            return handleInput();
        }
        User user = data.getUser(input[1]);
        if(user == null)
        {
            System.out.println("Could not find user with the username: "+input[1]);
            return handleInput();
        }
        ArrayList<Account> accounts = user.getAccounts();
        for(int i = 0; i < accounts.size(); i++)
        {
            String type = accounts.get(i).getType();
            int id = accounts.get(i).getID();
            double balance = accounts.get(i).getBalance();
            System.out.println("Type: "+type+" | ID: "+id+" | balance: $"+balance);
        }
        return handleInput();
    }

    public Screen viewTransactions(String[] input)
    {
        if(input.length != 3)
        {
            System.out.println("must include username and account number");
            return handleInput();
        }
        String username = input[1];
        String acc = input[2];
        User user = data.getUser(username);
        ArrayList<Account> accounts = user.getAccounts();
        Account account = null;
        for(int i = 0; i < accounts.size(); i++)
        {
            if(stringToInt(acc) == accounts.get(i).getID())
                account = accounts.get(i);
        }
        if(account == null)
        {
            System.out.println("Could not find account");
            return handleInput();
        }
        ArrayList<Transaction> trans = account.getTransactions();
        for(int i = 0; i < trans.size(); i++)
        {
            Date date = trans.get(i).getDate();
            String type = trans.get(i).getType();
            double amount = trans.get(i).getAmount();
            System.out.println(date+" | type: "+type+" | amount: $"+amount);
        }
        return handleInput();
    }

    public Integer stringToInt(String number)
    {
        try
        {
            int converted = Integer.parseInt(number);
            return converted;
        }
        catch (Exception e)
        {
            System.out.println("Could not convert string into an integer");
            return null;
        }
    }
}
