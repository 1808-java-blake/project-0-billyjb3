package com.revature.billy.bankapp.screen;


/**
 * Created by User on 8/9/2018.
 */
public class HomeScreen extends Screen
{
    public Screen start()
    {
        System.out.println("\nTHIS IS A BANK\n");
        displayCommands();
        return handleInput();
    }

    protected Screen handleInput()
    {
        String input = scanner.nextLine().toLowerCase().trim();
        if(input.equals("login"))
            return new LoginScreen();
        else if(input.equals("register"))
            return new RegisterScreen();
        else if(input.equals("help"))
        {
            displayCommands();
            return handleInput();
        }
        else if(input.equals("exit"))
        {
            System.exit(0);
            return null;
        }
        else
        {
            System.out.println("'"+input+"' not recognized");
            return handleInput();
        }
    }

    protected void displayCommands()
    {
        System.out.println("_____________________________________");
        System.out.println("|          --- Commands ---         |");
        System.out.println("|                                   |");
        System.out.println("|  User Login:               login  |");
        System.out.println("|  New User:              register  |");
        System.out.println("|  Redisplay Commands:        help  |");
        System.out.println("|  Close app:                 exit  |");
        System.out.println("|___________________________________|");
    }
}
