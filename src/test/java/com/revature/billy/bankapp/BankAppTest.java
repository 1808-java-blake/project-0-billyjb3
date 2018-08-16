package com.revature.billy.bankapp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.Scanner;

/**
 * Created by User on 8/14/2018.
 */
public class BankAppTest
{
    public static BankApp bankapp = new BankApp();
    private Scanner scanner = new Scanner(System.in);

    @Test
    public void testHomeInvalidCommand()
    {
        bankapp.start();
        System.out.println("invalid");
        String res = scanner.nextLine();
        assertEquals("'invalid' not recognized", res);
    }

    @Test
    public void test1()
    {

    }

}
