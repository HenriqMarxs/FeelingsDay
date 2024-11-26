package com.example.feelingsday.ui.home;

public class ListData {
    String name;
    String time;
    int goals;
    int desc;
    int image;
    public ListData(String name, String time, int goals, int desc, int image) {
        this.name = name;
        this.time = time;
        this.goals = goals;
        this.desc = desc;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getGoals() {
        return goals;
    }

    public int getDesc() {
        return desc;
    }

    public int getImage() {
        return image;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public void setImage(int image) {
        this.image = image;
    }
}