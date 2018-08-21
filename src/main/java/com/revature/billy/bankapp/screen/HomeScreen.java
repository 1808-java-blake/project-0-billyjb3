package com.revature.billy.bankapp.screen;


/**
 * Created by User on 8/9/2018.
 */
public class HomeScreen extends Screen
{
    public Screen start()
    {
        System.out.println("        .                                                      .    ");
        System.out.println("       / \\                                                    / \\ ");
        System.out.println("      / | \\                                                  / | \\  ");
        System.out.println("      | | |                                                  | | |");
        System.out.println("      | | |                                                  | | |     ");
        System.out.println("      | | |                MIDIEVAL  BANKING                 | | |       ");
        System.out.println("      | | |                                                  | | |    ");
        System.out.println("      | | |     II====================================II     | | |   ");
        System.out.println("      | | |     II              COMMANDS              II     | | |    ");
        System.out.println("      | | |     II                                    II     | | |       ");
        System.out.println("      | | |     II  User Loggin:            login     II     | | |                  ");
        System.out.println("      | | |     II  New User:               register  II     | | |                    ");
        System.out.println("      | | |     II  Redisplay Commands:     help      II     | | |                          ");
        System.out.println("      | | |     II  Close Application:      exit      II     | | |                           ");
        System.out.println("      | | |     II                                    II     | | |      ");
        System.out.println("  ____|_|_|____ II====================================II ____|_|_|____           ");
        System.out.println("  \\___________/                                          \\___________/              ");
        System.out.println("      |>-<|                                                  |>-<|");
        System.out.println("      |>-<|                                                  |>-<|");
        System.out.println("      |>-<|                                                  |>-<|");
        System.out.println("      |>-<|                                                  |>-<|");
        System.out.println("      \\<.>/                                                  \\<.>/");
        System.out.println("       \\ /                                                    \\ /");

        //displayCommands();
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
        System.out.println("II====================================II");
        System.out.println("II              COMMANDS              II");
        System.out.println("II                                    II");
        System.out.println("II  User Login:             login     II");
        System.out.println("II  New User:               register  II");
        System.out.println("II  Redisplay Commands:     help      II");
        System.out.println("II  Close app:              exit      II");
        System.out.println("II                                    II");
        System.out.println("II====================================II");
    }
}
