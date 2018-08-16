package com.revature.billy.bankapp;

import com.revature.billy.bankapp.screen.HomeScreen;
import com.revature.billy.bankapp.screen.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 8/13/2018.
 */
public class BankApp implements Runnable
{
    private Thread thread;
    private boolean running;
    private int ups = 60;
    private Screen screen;

    public static void main(String[] args)
    {
        BankApp bankapp = new BankApp();
        bankapp.start();
    }
    @Override
    public void run()
    {
        long nano = (long)1e9;
        long updateNano = nano / ups;
        long start = System.nanoTime();
        long now;
        long delta;
        int updates = 0;

        while(running)
        {
            now = System.nanoTime();
            delta = now - start;
            if(delta >= nano)
            {
                updates = 0;
                start = System.nanoTime();
            }

            if(delta >= updateNano*updates)
            {
                update();
                updates++;
            }

        }
        stop();
    }

    private void stop()
    {
        running = false;
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void start()
    {
        screen = new HomeScreen();
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void update()
    {
        screen = screen.start();
    }
}
