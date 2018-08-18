package com.revature.billy.bankapp.data;

import java.util.ArrayList;

/**
 * Created by User on 8/10/2018.
 */
public interface DataAccess
{
    DataAccess DAO = DatabaseData.DATABASE_DAO;

    boolean verifyUsername(String username);
    User verifyUser(String username, String password);
    boolean createUser(User user, String username);
    boolean updateUser(User user);
    ArrayList<String> getUsernames();
    User getUser(String username);
}
