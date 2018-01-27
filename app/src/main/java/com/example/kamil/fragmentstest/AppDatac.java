package com.example.kamil.fragmentstest;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Kamil Motyl
 */

public class AppDatac extends Application {
    private static final AppDatac ourInstance = new AppDatac();
    // Singleton consisting of account data, application state and current task selected.

    public int getCurrentFragment() {
        return CurrentFragment;
    }

    public void setCurrentFragment(int currentFragment) {
        CurrentFragment = currentFragment;
    }

    private int CurrentFragment=0;
    private String login="";
    private String password="";
    private boolean state=false;
    private int currentTaskId=0; // currently selected task by task ID/queue position
    private ArrayList<TaskModel> tasks;
    private String JSON;

    //
    // task queue/array here?
    //
    public String getLog() {
        return login;
    }
    public String getPasswd() {
        return password;
    }
    public boolean getState() {
        return state;
    }
    public int getCurrentTaskId() {
        return currentTaskId;
    }
    public ArrayList<TaskModel> getTasks(){return tasks;}


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public void setTasks(ArrayList<TaskModel> tasks){
        this.tasks = tasks;
    }
    public void setCurrentTaskId(int currentTaskId) {
        this.currentTaskId = currentTaskId;
    }

    public static AppDatac getInstance() {
        return(ourInstance);
    }

    private AppDatac() {
    }

    public void setJSON(String JSON) {
        this.JSON = JSON;


    }
    public String getJSON(){return JSON;}
}
