package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.*;
import com.revature.billy.bankapp.data.*;

/**
 * Created by User on 8/9/2018.
 */
public class UserScreen extends Screen
{
    public UserScreen(BankApp bankApp)
    {
        super(bankApp);
    }

    public void start()
    {
        Session session = bankApp.getSession();
        User user = session.getUser();
        System.out.println("Welcome "+user.getUsername()+"!");
    }

    public void displayCommands()
    {
        System.out.println("Commands:");
        System.out.println("View accounts:\taccounts");
        System.out.println("View transactions:\ttrans <first 4 digits of acc.#>");
        System.out.println("to logout and return home:\tlogout");
    }

    public void handleInput()
    {

    }
}
