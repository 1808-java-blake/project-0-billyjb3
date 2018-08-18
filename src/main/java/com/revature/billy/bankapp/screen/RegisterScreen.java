package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.data.User;

/**
 * Created by User on 8/9/2018.
 */
public class RegisterScreen extends Screen
{
    public Screen start()
    {
        System.out.println("\nPlease enter a new username 6 to 12 characters long");
        //displayCommands();
        return handleInput();
    }
    public Screen handleInput()
    {
        String input = scanner.nextLine().trim().toLowerCase();

        if(input.equals("home"))
            return new HomeScreen();
        else if(input.equals("help"))
            return start();
        else if(input.equals("exit"))
        {
            System.exit(0);
            return null;
        }
        else
            return createUser(input);
    }
    private Screen createUser(String input)
    {
        String username = input;

        if(username.length() < 6 || username.length() > 12)
        {
            System.out.println("username was an invalid length");
            return start();
        }

        if(!data.verifyUsername(username))
        {
            System.out.println("username already taken");
            return handleInput();
        }

        System.out.println("username = "+username+" | enter 'cancel' to restart");
        System.out.println("please enter password 8 to 15 characters long");
        String password1 = scanner.nextLine();

        if(password1.equals("cancel"))
            return start();

        if(password1.length() < 8 || password1.length() > 15)
        {
            System.out.println("password was an invalid length");
            return start();
        }

        System.out.println("please confirm password");
        String password2 = scanner.nextLine();

        if(password2.equals("cancel"))
            return start();
        if(!password1.equals(password2))
        {
            System.out.println("Passwords didn't match");
            return start();
        }

        System.out.println("enter your first name:");
        String firstname = scanner.nextLine();

        System.out.println("enter your last name:");
        String lastname = scanner.nextLine();

        if(data.createUser(new User(username, firstname, lastname), password1))
        {
            System.out.println("New user account created!");
            return new HomeScreen();
        }
        else
        {
            System.out.println("failed to create new user");
            return start();
        }
    }

    public void displayCommands()
    {
        System.out.println("_____________________________________");
        System.out.println("|          --- Commands ---         |");
        System.out.println("|                                   |");
        System.out.println("|  Return home:               home  |");
        System.out.println("|  Redisplay options:         help  |");
        System.out.println("|  To close the app:          exit  |");
        System.out.println("|___________________________________|");
    }
}
