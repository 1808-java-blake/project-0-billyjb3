package com.revature.billy.bankapp.data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by User on 8/16/2018.
 */
public class ConnectionUtil
{
    public static final ConnectionUtil cu = new ConnectionUtil();
    private Properties properties = new Properties();

    private ConnectionUtil()
    {
        try{
            properties.load(new FileInputStream("src/main/resources/database.properties"));}
        catch (Exception e){e.printStackTrace();}
    }

    public Connection getConnection() throws Exception
    {
        return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
    }

}
