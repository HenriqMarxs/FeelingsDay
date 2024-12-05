package com.example.feelingsday.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.feelingsday.R;
import com.example.feelingsday.Task;
import com.example.feelingsday.TaskStorage;
import com.example.feelingsday.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private ListAdapter listAdapter;
    private List<Task> taskList;
    private TaskStorage taskStorage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        taskStorage = new TaskStorage(requireContext());

        taskList = new ArrayList<>();
        listAdapter = new ListAdapter(requireContext(), taskList);

        binding.listView.setAdapter(listAdapter);

        binding.listView.setOnItemClickListener((parent, view, position, id) -> {
            Task selectedTask = taskList.get(position);
            Intent intent = new Intent(getContext(), DetailTaskActivity.class);

            intent.putExtra("name", selectedTask.getTaskName());
            intent.putExtra("duration", selectedTask.getTaskDuration());
            intent.putStringArrayListExtra("goals", new ArrayList<>(selectedTask.getTaskGoals()));
            intent.putExtra("steps", selectedTask.getTaskSteps());
            intent.putExtra("image", selectedTask.getImageTask());

            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Atualizar lista de tarefas ao retornar para o fragmento
        taskList.clear();
        taskList.addAll(taskStorage.getTasks());
        listAdapter.notifyDataSetChanged();
    }
}