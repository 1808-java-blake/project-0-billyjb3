package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.data.Account;
import com.revature.billy.bankapp.data.Transaction;
import com.revature.billy.bankapp.data.User;

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
        System.out.println("To view a users account info:\t\t\taccount <username>");
        System.out.println("To view transactions on an account:\t\ttrans <username> <first 4 digits of acc.#>");
        System.out.println("To logout and return to home screen:\tlogout");
        System.out.println("To view commands:\t\t\t\t\t\thelp");
    }

    @Override
    protected Screen handleInput()
    {
        String[] input = scanner.nextLine().split(" ");
        if (input[0].equals("users"))
        {
            ArrayList<User> users = data.getUsers();
            for(int i = 0; i < users.size(); i++)
                System.out.println(users.get(i).getUsername());
            return handleInput();
        }
        else if (input[0].equals("account"))
        {
            if(input.length != 2)
            {
                System.out.println("Must include username");
                return handleInput();
            }
            User user = data.getUser(input[1]);
            ArrayList<Account> accounts = user.getAccounts();
            for(int i = 0; i < accounts.size(); i++)
            {
                String type = accounts.get(i).getType();
                String id = accounts.get(i).getID();
                double balance = accounts.get(i).getBalance();
                System.out.println("Type: "+type+" | ID: "+id+" | balance: "+balance);
            }
            return handleInput();
        }
        else if(input[0].equals("trans"))
        {
            if(input.length != 3)
            {
                System.out.println("must include username and first 4 digits of account number");
                return handleInput();
            }
            String username = input[1];
            String acc = input[2];
            User user = data.getUser(username);
            ArrayList<Account> accounts = user.getAccounts();
            Account account = null;
            for(int i = 0; i < accounts.size(); i++)
            {
                if(acc.equals(accounts.get(i).getID().substring(0,4)))
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
                String date = trans.get(i).getDate();
                String type = trans.get(i).getType();
                double amount = trans.get(i).getAmount();
                System.out.println(date+" | type: "+type+" | amount: "+amount);
            }
            return handleInput();
        }
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
}
