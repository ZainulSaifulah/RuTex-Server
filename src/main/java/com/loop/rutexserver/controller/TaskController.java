/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loop.rutexserver.controller;

import com.google.gson.Gson;
import com.loop.rutexserver.model.Task;
import java.util.ArrayList;

/**
 *
 * @author shiroe-13
 */
public class TaskController {

    private final ArrayList<Task> tasks;
    private static volatile TaskController sInstance;
    private static final Object LOCK = new Object();

    public static TaskController getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new TaskController();
                }
            }
        }
        return sInstance;
    }

    public TaskController() {
        this.tasks = new ArrayList<>();
    }

    public String getMessage(Task task) {
        checkSimilar(task.getUser());
        tasks.add(task);
        SqliteHelper.getInstance().insertTask(task);
        return new Gson().toJson(tasks);
    }

    private void checkSimilar(String user) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getUser().equals(user)) {
                tasks.remove(i);
                return;
            }
        }
    }

}
