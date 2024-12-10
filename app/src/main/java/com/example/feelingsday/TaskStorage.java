package com.example.feelingsday;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.feelingsday.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final String PREFS_NAME = "task_storage";
    private static final String TASK_KEY = "tasks";

    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public TaskStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    // Salvar lista de tarefas
    public void saveTasks(List<Task> tasks) {
        String json = gson.toJson(tasks);
        sharedPreferences.edit().putString(TASK_KEY, json).apply();
    }

    // Recuperar lista de tarefas
    public List<Task> getTasks() {
        String json = sharedPreferences.getString(TASK_KEY, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Task>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public boolean deleteTask(Task taskToDelete) {
        List<Task> tasks = getTasks();
        tasks.removeIf(task -> task.getTaskName().equals(taskToDelete.getTaskName()) &&
                task.getTaskDuration().equals(taskToDelete.getTaskDuration()));
        saveTasks(tasks);
        return true;
    }
}
