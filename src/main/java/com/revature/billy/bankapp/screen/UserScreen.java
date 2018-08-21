package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.*;
import com.revature.billy.bankapp.data.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by User on 8/9/2018.
 */
public class UserScreen extends Screen
{
    private User user;
    public UserScreen(User user)
    {
        this.user = user;
    }

    public Screen start()
    {
        System.out.println("\nWelcome "+user.getUsername()+"!\n");
        displayCommands();
        return handleInput();
    }

    public Screen handleInput()
    {
        String[] input = scanner.nextLine().trim().toLowerCase().split(" ");
        if(input[0].equals("accounts"))
            return handleAccounts();
        else if(input[0].equals("trans") && input.length == 2)
            return handleTransactions(input[1]);
        else if(input[0].equals("newacc"))
            return new NewAccountScreen(user);
        else if(input[0].equals("close") && input.length == 2)
            return handleClose(input[1]);
        else if(input[0].equals("withdraw") && input.length == 2)
            return handleWithdraw(input[1]);
        else if(input[0].equals("deposit") && input.length == 2)
            return handleDeposit(input[1]);
        else if(input[0].equals("help"))
            return start();
        else if(input[0].equals("logout"))
        {
            System.out.println("You are now logged out");
            return new HomeScreen();
        }
        else
        {
            System.out.println("'"+String.join(" ",input)+"' not recognized");
            return handleInput();
        }
    }

    public void displayCommands()
    {
        System.out.println("\t\t\t\t---Commands---");
        System.out.println("View accounts:\t\t\t\t\taccounts");
        System.out.println("View transactions:\t\t\t\ttrans <Account Number>");
        System.out.println("Create new account:\t\t\t\tnewacc");
        System.out.println("Close existing account:\t\t\tclose <Account Number>");
        System.out.println("Make a withdraw:\t\t\t\twithdraw <Account Number>");
        System.out.println("Make a deposit:\t\t\t\t\tdeposit <Account Number>");
        System.out.println("Logout and return home:\t\t\tlogout");
        System.out.println("Redisplay commands:\t\t\t\thelp");
    }

    //////////////////////////HANDLE METHODS////////////////////////////////////////
    public Screen handleAccounts()
    {
        ArrayList<Account> accounts = user.getAccounts();
        if(accounts.size() == 0)
        {
            System.out.println("User has no open accounts");
            return handleInput();
        }

        printAccounts(accounts);
        return handleInput();
    }

    public Screen handleTransactions(String acc)
    {
        Account account = getAccount(acc);
        if(account == null)
            return handleInput();

        ArrayList<Transaction> transactions = account.getTransactions();
        printTransactions(transactions);

        return handleInput();
    }

    public Screen handleClose(String acc)
    {
        Account account = getAccount(acc);
        if(account == null)
            return handleInput();
        double balance = account.getBalance();
        if(balance != 0)
            createTransaction(account, ""+balance, "withdraw");
        account.setIsOpen(false);
        data.updateUser(user);
        System.out.println("Account was successfully closed");
        return handleInput();
    }

    public Screen handleWithdraw(String acc)
    {
        Account account = getAccount(acc);
        if(account == null)
            return handleInput();

        System.out.println("Enter the amount you wish to withdraw");
        System.out.println("Enter 'cancel' to cancel the withdraw");
        String input = scanner.nextLine();

        if(input.equals("cancel"))
            return start();

        createTransaction(account, input, "withdraw");
        data.updateUser(user);
        return handleInput();
    }

    public Screen handleDeposit(String acc)
    {
        Account account = getAccount(acc);
        if(account == null)
            return handleInput();

        System.out.println("Enter the amount you wish to deposit");
        System.out.println("Enter 'cancel' to cancel the deposit");
        String input = scanner.nextLine();

        if(input.equals("cancel"))
            return start();

        createTransaction(account, input, "deposit");
        data.updateUser(user);
        return handleInput();
    }

    ////////////////////HELPER METHODS//////////////////////////////////
    public Account getAccount(String acc)
    {
        ArrayList<Account> accounts = user.getAccounts();
        Account account = null;
        for(int i = 0; i < accounts.size(); i++)
        {
            if(accounts.get(i).getID() == stringToInt(acc))
                account = accounts.get(i);
        }
        if(account == null)
            System.out.println("could not find account");
        return account;
    }

    public void createTransaction(Account account, String input, String type)
    {
        double amount = 0;
        try
        {
            amount = Double.parseDouble(input);
            if(type.equals("withdraw"))
                amount = -amount;

            if(amount < 0 && account.getBalance() < -amount)
            {
                System.out.println("Insufficient funds");
                return;
            }

            Transaction transaction = new Transaction(type, amount, new Date(System.currentTimeMillis()));
            transaction.setAccountid(account.getID());
            account.addTransaction(transaction);
            System.out.println(type+" was successful");
            data.updateUser(user);
        }
        catch (Exception e) {System.out.println("Amount must be an number amount");}
    }

    public void printAccounts(ArrayList<Account> accounts)
    {
        for (int i = 0; i < accounts.size(); i++)
        {
            if(accounts.get(i).isOpen())
            {
                String type = accounts.get(i).getType();
                int id = accounts.get(i).getID();
                double balance = accounts.get(i).getBalance();
                System.out.println("Account type: " + type + "  | ID: " + id + " | Balance: $" + balance);
            }
        }
    }

    public void printTransactions(ArrayList<Transaction> transactions)
    {
        for(int i = 0; i < transactions.size(); i++)
        {
            Date date = transactions.get(i).getDate();
            String type = transactions.get(i).getType();
            double amount = transactions.get(i).getAmount();
            System.out.println("Date: "+date+" | Type: "+type+" | Amount: $"+amount);
        }
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
            System.out.println("Could not convert input into an integer");
            return null;
        }
    }
}
