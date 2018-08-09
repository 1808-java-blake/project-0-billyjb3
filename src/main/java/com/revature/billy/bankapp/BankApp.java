package com.revature.billy.bankapp;

import com.revature.billy.bankapp.data.DataAccess;
import com.revature.billy.bankapp.data.SerializedData;
import com.revature.billy.bankapp.data.User;
import com.revature.billy.bankapp.screen.*;

/**
 * Created by User on 8/9/2018.
 */
public class BankApp
{
    private HomeScreen homeScreen;
    private LoginScreen loginScreen;
    private RegisterScreen registerScreen;
    private DataAccess data;
    private Session session;

    public BankApp()
    {
        homeScreen = new HomeScreen(this);
        loginScreen = new LoginScreen(this);
        registerScreen = new RegisterScreen(this);
        data = new SerializedData();
        session = new Session();

        displayHomeScreen();
    }

    public void displayHomeScreen()
    {
        homeScreen.start();
    }

    public void displayLoginScreen()
    {
        loginScreen.start();
    }

    public boolean verifyUsername(String username)
    {
        return data.verifyUsername(username);
    }

    public boolean login(String username, String password)
    {
        return false;
    }

    public void displayRegisterScreen()
    {
        registerScreen.start();
    }

    public void register(String username, String password)
    {

    }

    public void displayUserScreen()
    {

    }

    public Session getSession()
    {
        return session;
    }
}
