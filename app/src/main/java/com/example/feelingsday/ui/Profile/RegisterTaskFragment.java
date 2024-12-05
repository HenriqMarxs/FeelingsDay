package com.example.feelingsday.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.feelingsday.R;
import com.example.feelingsday.Task;
import com.example.feelingsday.TaskStorage;
import com.example.feelingsday.databinding.FragmentProfileBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterTaskFragment extends Fragment {
    private  FragmentProfileBinding binding;
    private TaskStorage taskStorage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        taskStorage = new TaskStorage(requireContext());

        binding.saveTask.setOnClickListener(v -> {
            String name = binding.nameInput.getText().toString();
            String duration = binding.durationInput.getText().toString();
            String steps = binding.stepsInput.getText().toString();
            String goalsInput = binding.goalsInput.getText().toString(); // Metas separadas por vírgula
            List<String> goals = Arrays.asList(goalsInput.split(","));
            int imageResource = R.drawable.programmer; // Imagem padrão ou escolhida pelo usuário

            if (!name.isEmpty() && !steps.isEmpty()) {
                // Criar a nova tarefa
                Task newTask = new Task(name, duration, goals, steps, imageResource);

                // Salvar a tarefa no armazenamento
                List<Task> tasks = taskStorage.getTasks();
                tasks.add(newTask);
                taskStorage.saveTasks(tasks);

                // Notificar sucesso
                Toast.makeText(requireContext(), "Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();

                // Navegar de volta para o HomeFragment
                requireActivity().getSupportFragmentManager().popBackStack();
            } else {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}