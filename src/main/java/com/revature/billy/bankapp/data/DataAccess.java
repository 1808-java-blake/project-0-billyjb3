package com.revature.billy.bankapp.data;

/**
 * Created by User on 8/9/2018.
 */
public interface DataAccess
{
    boolean verifyUsername(String username);
    boolean verifyUser(String username, String password);
    boolean createUser(String username, String password);
    Transaction[] getTransactionHistory(String username);

}
