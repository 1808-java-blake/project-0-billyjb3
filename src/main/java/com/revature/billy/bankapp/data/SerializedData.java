package com.revature.billy.bankapp.data;

/**
 * Created by User on 8/9/2018.
 */
public class SerializedData implements DataAccess
{

    public boolean verifyUsername(String username)
    {
        return false;
    }

    public boolean verifyUser(String username, String password)
    {
        return false;
    }

    public boolean createUser(String username, String password)
    {
        return false;
    }

    public Transaction[] getTransactionHistory(String username)
    {
        return new Transaction[0];
    }
}
