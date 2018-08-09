package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.BankApp;

/**
 * Created by User on 8/9/2018.
 */
public class LoginScreen extends Screen
{
    public LoginScreen(BankApp bankApp)
    {
        super(bankApp);
    }

    public void start()
    {
        System.out.println("\nTO LOGIN PLEASE ENTER USERNAME\n");
        displayCommands();
        handleInput();
    }
    public void handleInput()
    {
        String input = scanner.nextLine();
        if(input.equals("home"))
            bankApp.displayHomeScreen();
        else if(input.equals("help"))
            start();
        else
        {
            String username = input;
            if(bankApp.verifyUsername(username))
            {
                System.out.println("Please enter password");
                String password = scanner.nextLine();
                if(bankApp.login(username, password))
                    bankApp.displayUserScreen();
            }
            else
            {
                System.out.println("Username not found");
                handleInput();
            }
        }
    }
    public void displayCommands()
    {
        System.out.println("\t\t---Commands---");
        System.out.println("Return to home screen:\thome");
        System.out.println("Redisplay options:\t\thelp\n");
    }
}
