package com.revature.billy.bankapp.data;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by User on 8/9/2018.
 */
public class SerializedData implements DataAccess
{
    private String path = "src/main/resources/users/";
    public static final SerializedData SERIALIZED_DAO = new SerializedData();

    private SerializedData(){}

    public boolean verifyUsername(String username)
    {
        File file = new File(path+username+".txt");
        if(file.exists())
            return true;
        return false;
    }

    public User verifyUser(String username, String password)
    {
        File file = new File(path+username+".txt");
        if(!file.exists())
            return null;
        try
        {
            ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file));
            User user = (User) oos.readObject();
            if(password.equals(user.getPassword()))
                return user;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public User getUser(String username)
    {
        File file = new File(path+username+".txt");
        if(!file.exists())
            return null;
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            return (User)ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createUser(User user)
    {
        File file = new File(path+user.getUsername()+".txt");
        if(file.exists())
            return false;
        try
        {
            file.createNewFile();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUser(User user)
    {
        File file = new File(path+user.getUsername()+".txt");
        if(!file.exists())
            return false;
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(user);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> getUsers()
    {
        ArrayList<User> users = new ArrayList<>();
        File dire = new File(path);
        File[] files = dire.listFiles();
        try
        {
            for(int i = 0; i < files.length; i++)
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(files[i]));
                users.add((User)ois.readObject());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return users;
    }


}
