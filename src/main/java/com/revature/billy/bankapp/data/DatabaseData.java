package com.revature.billy.bankapp.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

    public ResultSet sendQuery(String query)
    {
        try
        {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }
        catch (Exception e){e.printStackTrace();}
        return null;
    }

    @Override
    public boolean verifyUsername(String username)
    {
        return false;
    }

    @Override
    public User verifyUser(String username, String password)
    {
        ResultSet rs = sendQuery("query");
        try
        {
            if (rs.next())
            {
                User u = new User("username", "password", false); //needs to match database
                rs.getInt("columnname");
            }
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    @Override
    public boolean createUser(User user)
    {
        ArrayList<Account> accounts = user.getAccounts();

        return false;
    }

    @Override
    public boolean updateUser(User user)
    {
        return false;
    }

    @Override
    public ArrayList<User> getUsers()
    {
        return null;
    }

    @Override
    public User getUser(String username)
    {
        return null;
    }
}
