package com.example.feelingsday.ui.RegisterTask;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.feelingsday.R;
import com.example.feelingsday.Task;
import com.example.feelingsday.TaskStorage;
import com.example.feelingsday.databinding.FragmentRegisterTaskBinding;
import com.example.feelingsday.ui.home.DetailTaskActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterTaskFragment extends Fragment {
    private  FragmentRegisterTaskBinding binding;
    private TaskStorage taskStorage;
    private String selectedImagePath = null;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterTaskBinding.inflate(inflater, container, false);
        taskStorage = new TaskStorage(requireContext());

        binding.selectImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });


        binding.addGoal.setOnClickListener(v -> addGoalField());

        binding.saveTask.setOnClickListener(v -> {
            String name = binding.nameInput.getText().toString();
            String duration = binding.durationInput.getText().toString();
            String steps = binding.stepsInput.getText().toString();
            String imagePath = selectedImagePath;

            // Lista para armazenar as metas
            List<String> goals = new ArrayList<>();

            // Adiciona o texto do primeiro EditText (goalsInput) à lista de metas
            String goalInput = binding.goalsInput.getText().toString().trim();
            if (!goalInput.isEmpty()) {
                goals.add(goalInput);  // Adiciona a primeira goal à lista
            }

            // Recuperar todas as metas do LinearLayout
            LinearLayout goalsContainer = binding.goalsContainer;

            // Iterar sobre os filhos do goalsContainer (campos dinamicamente adicionados)
            for (int i = 0; i < goalsContainer.getChildCount(); i++) {
                View child = goalsContainer.getChildAt(i);
                if (child instanceof LinearLayout) {
                    LinearLayout goalLayout = (LinearLayout) child;
                    EditText goalInputField = (EditText) goalLayout.getChildAt(0);  // O primeiro filho será o EditText
                    String goalText = goalInputField.getText().toString().trim();
                    if (!goalText.isEmpty()) {
                        goals.add(goalText); // Adiciona à lista de metas
                    }
                }
            }

            String imageResource = selectedImagePath;  // Imagem padrão ou escolhida pelo usuário

            // Validar se os campos obrigatórios estão preenchidos
            if (!name.isEmpty() && !steps.isEmpty()) {
                // Criar a nova tarefa
                Task newTask = new Task(name, duration, goals, steps, imageResource);
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


        binding.importTask.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ImportTask.class);
            startActivity(intent);
        });
        return binding.getRoot();
    }
    private void addGoalField() {
        int dimencion = 25;
        dimencion = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dimencion, getResources().getDisplayMetrics());
        // Cria um novo layout horizontal
        LinearLayout newGoalLayout = new LinearLayout(getContext());
        newGoalLayout.setOrientation(LinearLayout.VERTICAL);
        newGoalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Cria um novo EditText para a goal
        EditText newGoalInput = new EditText(getContext());
        newGoalInput.setHint("Goal");
        newGoalInput.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f // Peso para ocupar o espaço disponível
        ));

        // Cria um botão para excluir o campo
        ImageButton removeGoalButton = new ImageButton(getContext());
        removeGoalButton.setImageResource(R.drawable.delete);
        removeGoalButton.setBackgroundResource(android.R.color.transparent);
        removeGoalButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        removeGoalButton.setLayoutParams(new LinearLayout.LayoutParams(dimencion, dimencion));

        // Listener para remover o campo de goal
        removeGoalButton.setOnClickListener(v -> binding.goalsContainer.removeView(newGoalLayout));

        // Adiciona os componentes ao layout horizontal
        newGoalLayout.addView(newGoalInput);
        newGoalLayout.addView(removeGoalButton);

        // Adiciona o layout horizontal ao containerz
        binding.goalsContainer.addView(newGoalLayout);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            // Exibir a imagem no ImageView
            binding.taskImageView.setImageURI(selectedImageUri);

            // Você pode salvar o URI como string para usá-lo posteriormente
            selectedImagePath = selectedImageUri.toString();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}