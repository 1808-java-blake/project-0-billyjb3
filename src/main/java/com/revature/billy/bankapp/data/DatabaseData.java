package com.revature.billy.bankapp.data;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

/**
 * Created by User on 8/16/2018.
 */
public class DatabaseData implements DataAccess
{
    public static final DataAccess DATABASE_DAO = new DatabaseData();
    private ConnectionUtil cu = ConnectionUtil.cu;
    private Connection connection;

    public DatabaseData()
    {
        try
        {
            connection = cu.getConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }


    @Override
    public boolean verifyUsername(String username)
    {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                if(rs.getInt(1) > 0)
                    return true;
                return false;
            }
            else
            {
                System.out.println("Verify username query did not return");
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Verify username query could not execute");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User verifyUser(String username, String password)
    {
        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                if (rs.getInt(1) == 1)
                    return getUser(username);
                else
                    return null;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.print("could not verify user");
            return null;
        }
    }

    @Override
    public boolean createUser(User user, String password)
    {
        //username, password, firsname, lastname, isadmin, isopen
        String query = "INSERT INTO users (username, pass, firstname, lastname, isadmin, isopen) VALUES (?, ?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query, new String[] {"userid"});
            user.setUserid(getNextUserId());
            ps.setString(1, user.getUsername());
            ps.setString(2, password);
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setBoolean(5, false);
            ps.setBoolean(6, true);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                user.setUserid(rs.getInt("userid"));
                return true;
            }
            else
            {
                System.out.println("Create user query did not return");
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println("Could not create user");
            e.printStackTrace();
            return false;
        }
    }

    public void createAccount(Account account)
    {
        String query = "INSERT INTO accounts (userid, accounttype, balance, isopen) VALUES (?, ?, ?, ?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query, new String[] {"accountid"});
            ps.setInt(1, account.getUserid());
            ps.setString(2,account.getType());
            ps.setDouble(3, account.getBalance());
            ps.setBoolean(4, account.isOpen());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                account.setID(rs.getInt("accountid"));
        }
        catch (Exception e)
        {
            System.out.println("Could not create new account");
        }
    }

    public void createTransaction(Transaction transaction)
    {
        String query = "INSERT INTO transactions (accountid, transactiontype, amount, datetime) VALUES (?, ?, ?, ?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query, new String[] {"transactionid"});
            ps.setInt(1, transaction.getAccountid());
            ps.setString(2, transaction.getType());
            ps.setDouble(3, transaction.getAmount());
            ps.setDate(4, transaction.getDate());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                transaction.setTransactionid(rs.getInt("transactionid"));
        }
        catch (Exception e)
        {
            System.out.println("Could not create transaction");
        }
    }

    @Override
    public boolean updateUser(User user)
    {
        ArrayList<Account> accounts = user.getAccounts();
        try
        {
            for (int i = 0; i < accounts.size(); i++)
            {
                if(accounts.get(i).getID() == -1)
                    createAccount(accounts.get(i));
                else
                {
                    String query = "UPDATE accounts SET balance = ?, isopen = ? WHERE accountid = ?";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setDouble(1, accounts.get(i).getBalance());
                    ps.setBoolean(2, accounts.get(i).isOpen());
                    ps.setInt(3, accounts.get(i).getID());
                    ps.executeUpdate();

                    ArrayList<Transaction> transactions = accounts.get(i).getTransactions();
                    for(int j = 0; j < transactions.size(); j++)
                    {
                        if(transactions.get(j).getTransactionid() == -1)
                            createTransaction(transactions.get(j));
                    }
                }
            }
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Could not update user");
            return false;
        }
    }

    @Override
    public ArrayList<String> getUsernames()
    {
        String query = "SELECT * FROM users";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> users = new ArrayList<>();
            while(rs.next())
                users.add(rs.getString("username"));
            return users;
        }
        catch (Exception e)
        {
            System.out.println("could not retrieve users");
            return null;
        }
    }

    @Override
    public User getUser(String username)
    {
        String query = "SELECT * FROM users WHERE username = ?";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int userid = rs.getInt("userid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                boolean isadmin = rs.getBoolean("isadmin");
                boolean isopen = rs.getBoolean("isopen");
                User user = new User(username, firstname, lastname);
                user.setUserid(userid);
                user.setAdmin(isadmin);
                user.setIsOpen(isopen);
                user.setAccounts(getAccounts(userid));
                return user;
            }
            else
            {
                System.out.println("Could not retreive user in getuser");
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Get user query failed");
            return null;
        }
    }

    public ArrayList<Account> getAccounts(int userid)
    {
        String query = "SELECT * FROM accounts WHERE userid = ?";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            ArrayList<Account> accounts = new ArrayList<>();
            while(rs.next())
            {
                int accountid = rs.getInt("accountid");
                String accounttype = rs.getString("accounttype");
                double balance = rs.getDouble("balance");
                boolean isOpen = rs.getBoolean("isopen");

                Account account = new Account(accounttype, userid);
                account.setIsOpen(isOpen);
                account.setID(accountid);
                account.setBalance(balance);
                account.setTransactions(getTransactions(accountid));
                accounts.add(account);
            }
            return accounts;
        }
        catch(Exception e)
        {
            System.out.println("Could not find Accounts");
            return null;
        }
    }

    public ArrayList<Transaction> getTransactions(int accountid)
    {
        String query = "SELECT * FROM transactions WHERE accountid = ?";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountid);
            ResultSet rs = ps.executeQuery();
            ArrayList<Transaction> transactions = new ArrayList<>();
            while(rs.next())
            {
                int transid = rs.getInt(1);
                int accid = rs.getInt(2);
                String transtype = rs.getString(3);
                double amount = rs.getDouble(4);
                Date date = rs.getDate(5);
                Transaction transaction = new Transaction(transtype,amount,date);
                transaction.setTransactionid(transid);
                transaction.setAccountid(accid);
                transactions.add(transaction);
            }
            return transactions;
        }
        catch (Exception e)
        {
            System.out.println("Could not find Transactions");
            return null;
        }
    }

    public Integer getNextUserId()
    {
        String query = "SELECT MAX(userid) FROM users";
        try
        {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            if(rs.next())
            {
                int id = rs.getInt(1);
                return ++id;
            }
            else
            {
                System.out.println("Could not get next userid");
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Could not get next userid");
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("could not close connection");
            System.exit(0);
        }
    }

}
