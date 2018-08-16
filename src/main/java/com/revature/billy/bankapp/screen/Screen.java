package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.data.DataAccess;
import com.revature.billy.bankapp.data.SerializedData;

import java.util.Scanner;

/**
 * Created by User on 8/9/2018.
 */
public abstract class Screen
{
    protected Scanner scanner = new Scanner(System.in);
    protected DataAccess data = DataAccess.DAO;

    public abstract Screen start();
    protected abstract void displayCommands();
    protected abstract Screen handleInput();
}
