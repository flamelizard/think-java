package com.gridworld;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Tom on 12/16/2015.
 */

/*
Read properties file that consist of key-value mappings like key1=val1
 */
public class PropertyReader {

    public static void main(String[] args) {
        String p = "mysql.properties";
        try {
            readPropertyFile(p);
        } catch (Exception e) {
            System.out.println("Error reading file !!");
            e.printStackTrace();
        }
    }

    public static void readPropertyFile(String file) throws IOException {
        Properties prop = new Properties();

        InputStream stream = PropertyReader.class.getResourceAsStream(file);
        if (stream == null) {
            throw new FileNotFoundException(file);
        }

        prop.load(stream);

        String s = prop.getProperty("username");
        System.out.println(s);
    }
}
