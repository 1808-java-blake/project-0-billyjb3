package com.revature.billy.bankapp;

import com.revature.billy.bankapp.data.Account;
import com.revature.billy.bankapp.data.User;

/**
 * Created by User on 8/9/2018.
 */
public class Session
{
    private User user;
    private boolean admin = false;
    private int inactiveTime = 0;
    private boolean active = false;

    public Session()
    {
        user = null;
    }

    public void newSession(User user, boolean admin)
    {
        this.user = user;
        this.admin = admin;
    }

    public void startTimer()
    {

    }

    public User getUser()
    {
        return user;
    }
    public boolean isAdmin()
    {
        return admin;
    }
}
