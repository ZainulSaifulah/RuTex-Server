/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loop.rutexserver.controller;

import com.loop.rutexserver.model.Task;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zainul Saifulah Aziz
 */
public class SqliteHelper {

    private Connection c = null;
    private static final Object LOCK = new Object();
    private static volatile SqliteHelper sInstance;

    
    public static SqliteHelper getInstance(){
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new SqliteHelper();
                }
            }
        }
        return sInstance;
    }
    
    public SqliteHelper(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Task.db");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException  ex) {
            Logger.getLogger(SqliteHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTable(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS task(create_date DATE, user varchar(16), task TEXT)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SqliteHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertTask(Task task){
        try {
            String sql = "INSERT INTO task values (?,?,?)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, task.getCreateDate().toString());
            statement.setString(2, task.getUser());
            statement.setString(3, task.getTask());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SqliteHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
