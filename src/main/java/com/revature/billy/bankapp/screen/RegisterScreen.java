package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.BankApp;

/**
 * Created by User on 8/9/2018.
 */
public class RegisterScreen extends Screen
{
    public RegisterScreen(BankApp bankApp)
    {
        super(bankApp);
    }

    public void start()
    {
        System.out.println("\nTO REGISTER PLEASE ENTER A NEW USERNAME\n");
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
            System.out.println("please enter password");
            String password1 = scanner.nextLine();
            System.out.println("please confirm password");
            String password2 = scanner.nextLine();
            if((username != null && !username.equals("")) || (password1 != null && !password1.equals("")))
            {
                if(password1.equals(password2))
                    bankApp.register(username, password1);
            }
        }
    }
    public void displayCommands()
    {
        System.out.println("\t\t---Commands---");
        System.out.println("Return home:\t\t\thome");
        System.out.println("Redisplay options:\t\thelp\n");
    }
}
