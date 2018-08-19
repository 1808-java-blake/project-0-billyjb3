package com.revature.billy.bankapp.screen;


import com.revature.billy.bankapp.data.User;

/**
 * Created by User on 8/9/2018.
 */
public class LoginScreen extends Screen
{

    public Screen start()
    {
        System.out.println("\nPlease enter your username\n");
        //displayCommands();
        return handleInput();
    }
    public Screen handleInput()
    {
        String input = scanner.nextLine();
        if(input.equals("home"))
            return new HomeScreen();
        else if(input.equals("help"))
            return start();
        else
        {
            String username = input;
            if(data.verifyUsername(username))
            {
                System.out.println("Please enter password");
                String password = scanner.nextLine();
                User user = data.verifyUser(username, password);
                if(user != null)
                {
                    if(user.isAdmin())
                        return new AdminScreen();
                    else
                        return new UserScreen(user);
                }
                else
                {
                    System.out.println("Wrong username and password combination");
                    return handleInput();
                }
            }
            else
            {
                System.out.println("Username not found");
                return handleInput();
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
