package com.revature.billy.bankapp.screen;

import com.revature.billy.bankapp.data.Account;
import com.revature.billy.bankapp.data.User;

import java.util.Random;

/**
 * Created by User on 8/10/2018.
 */
public class NewAccountScreen extends Screen
{
    private User user;
    private Random r;
    public NewAccountScreen(User user)
    {
        this.user = user;
    }

    public Screen start()
    {
        System.out.println("\nPlease choose account type:");
        System.out.println("\tchecking");
        System.out.println("\tsavings");
        displayCommands();
        return handleInput();
    }

    public Screen handleInput()
    {
        String input = scanner.nextLine();
        if(input.equals("checking"))
        {
            r = new Random();
            String id = (new Integer(r.nextInt(100000000)+10000000)).toString();
            Account newacc = new Account("checking",id, 0);
            user.addAccount(newacc);
            if(data.updateUser(user))
            {
                System.out.println("New checking account with id: " + id + " was created!");
                return new UserScreen(user);
            }
            else
            {
                System.out.println("There was a problem with the system. Sorry, try again later");
                return new HomeScreen();
            }
        }
        else if(input.equals("savings"))
        {
            r = new Random();
            String id = (new Integer(r.nextInt(100000000)+10000000)).toString();
            Account newacc = new Account("savings",id, 0);
            user.addAccount(newacc);
            if(data.updateUser(user))
            {
                System.out.println("New savings account with id: " + id + " was created!");
                return new UserScreen(user);
            }
            else
            {
                System.out.println("There was a problem with the system. Sorry, try again later");
                return new HomeScreen();
            }
        }
        else if(input.equals("user"))
            return new UserScreen(user);
        else if(input.equals("logout"))
            return new HomeScreen();
        else
        {
            System.out.println("Unrecognized input");
            return handleInput();
        }
    }

    public void displayCommands()
    {
        System.out.println("\t---Commands---");
        System.out.println("To return to user account:\tuser");
        System.out.println("To logout and return home:\tlogout");
        System.out.println("print help:\t\thelp\n");
    }
}
