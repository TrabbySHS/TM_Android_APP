package com.example.kamil.fragmentstest;


public class TaskModel <T,K>{
    public void setId(T id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPriority(K priority) {
        this.priority = priority;
    }

    T id;
    String title="";
    String description="";

    String data;
    K priority;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public T getId() {
        return id;
    }

    public String getData() {
        return data;
    }
    public K getPriority() {
        return priority;
    }

}
