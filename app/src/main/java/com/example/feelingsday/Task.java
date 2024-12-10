package com.example.feelingsday;

import java.util.List;

public class Task {
    private String taskName;
    private String taskDuration;
    private List<String> taskGoals;
    private String taskSteps;
    private String imageTasck;

    public Task(String taskName, String taskDuration, List<String>taskGoals, String taskSteps, String imageTasck) {
        this.taskName = taskName;
        this.taskDuration = taskDuration;
        this.taskGoals = taskGoals;
        this.taskSteps = taskSteps;
        this.imageTasck = imageTasck;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskDuration() {
        return taskDuration;
    }
    public void setTaskDuration(String taskDuration) {
        this.taskDuration = taskDuration;
    }
    public List<String> getTaskGoals() {
        return taskGoals;
    }
    public void setTaskGoals(List<String> taskGoals) {
        this.taskGoals = taskGoals;
    }
    public String getTaskSteps() {
        return taskSteps;
    }
    public void setTaskSteps(String taskSteps) {
        this.taskSteps = taskSteps;
    }
    public String getImageTask() {
        return imageTasck;
    }
    public void setImageTask(String imageTasck) {
        this.imageTasck = imageTasck;
    }
}
