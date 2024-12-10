package com.example.feelingsday.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.feelingsday.R;
import com.example.feelingsday.Task;
import com.example.feelingsday.TaskStorage;
import com.example.feelingsday.databinding.ActivityDetailTaskBinding;

import java.util.ArrayList;

import static androidx.core.content.ContentProviderCompat.requireContext;

public class DetailTaskActivity extends AppCompatActivity {
    private ActivityDetailTaskBinding binding;

    private TaskStorage taskStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        taskStorage = new TaskStorage(this);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            ArrayList<String> goals = intent.getStringArrayListExtra("goals");
            String duration = intent.getStringExtra("duration");
            String steps = intent.getStringExtra("steps");
            String image = intent.getStringExtra("image");

            binding.detailName.setText(name);
            binding.detailTime.setText(duration);
            binding.detailGoals.setText(TextUtils.join("\n", goals));
            binding.detailDesc.setText(steps);
            if (image != null && !image.isEmpty()) {
                Glide.with(this)
                        .load(image)  // Pode ser URL ou caminho local
                        .placeholder(R.drawable.programmer)  // Imagem temporária enquanto carrega
                        .error(R.drawable.programmer)
                        .apply(RequestOptions.fitCenterTransform())
                        .into(binding.detailImage);
            } else {
                // Se a imagem estiver vazia ou nula, você pode definir uma imagem padrão
                binding.detailImage.setImageResource(R.drawable.programmer);
            }
        }

        //  progressBar = findViewById(R.id.progressbar);
        // progessButton = findViewById(R.id.progress_button);

        //progessButton.setOnClickListener(new View.OnClickListener(){
        //    @Override
        //    public void onClick(View v) {
        //        CurrentProgress = CurrentProgress + 10;
        //        progressBar.setProgress(CurrentProgress);
        //        progressBar.setMax(100);
        //    }
        //});

        binding.deleteTask.setOnClickListener(v -> {
            // Criar uma instância de Task com os dados atuais
            Task taskToDelete = new Task(
                    binding.detailName.getText().toString(),
                    binding.detailTime.getText().toString(),
                    new ArrayList<>(), // Supondo que não usamos metas aqui
                    binding.detailDesc.getText().toString(),
                    binding.detailImage.getTag() != null ? binding.detailImage.getTag().toString() : null // Obtém o caminho da imagem
            );

            // Chamar o método de exclusão do armazenamento
            boolean isDeleted = taskStorage.deleteTask(taskToDelete);

            // Mostrar mensagem de confirmação
            if (isDeleted) {
                Toast.makeText(this, "Tarefa excluída com sucesso!", Toast.LENGTH_SHORT).show();
                finish();// Voltar para a tela anterior
            } else {
                Toast.makeText(this, "Erro ao excluir a tarefa!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
