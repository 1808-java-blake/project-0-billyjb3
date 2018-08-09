package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.BankApp;

import java.util.Scanner;

/**
 * Created by User on 8/9/2018.
 */
public abstract class Screen
{
    protected Scanner scanner = new Scanner(System.in);
    protected BankApp bankApp;

    public Screen(BankApp bankApp)
    {
        this.bankApp = bankApp;
    }

    public abstract void start();
    public abstract void displayCommands();
    public abstract void handleInput();
}
