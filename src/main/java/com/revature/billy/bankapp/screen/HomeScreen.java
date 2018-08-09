package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.BankApp;

/**
 * Created by User on 8/9/2018.
 */
public class HomeScreen extends Screen
{
    public HomeScreen(BankApp bankApp)
    {
        super(bankApp);
    }

    public void start()
    {
        System.out.println("\nWELCOME TO <INSERT BANK NAME>\n");
        displayCommands();
        handleInput();
    }
    public void handleInput()
    {
        String input = scanner.nextLine();
        if(input.equals("login"))
            bankApp.displayLoginScreen();
        else if(input.equals("register"))
            bankApp.displayRegisterScreen();
        else if(input.equals("help"))
        {
            displayCommands();
            handleInput();
        }
        else
        {
            System.out.println("'"+input+"' not recognized");
            handleInput();
        }
    }
    public void displayCommands()
    {
        System.out.println("\t\t---Commands---");
        System.out.println("User Login:\t\t\t\tlogin");
        System.out.println("New User:\t\t\t\tregister");
        System.out.println("Redisplay Commands:\t\thelp\n");
    }
}
