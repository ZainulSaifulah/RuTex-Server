/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loop.rutexserver.model;

import java.util.Date;

/**
 *
 * @author Zainul Saifulah Aziz
 */
public class Task {
    private Date createDate;
    private String user;
    private String task;

    public Task(Date createDate, String user, String task) {
        this.createDate = createDate;
        this.user = user;
        this.task = task;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
    
        
    
    
}
