package com.example.feelingsday.ui.RegisterTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.feelingsday.R;
import com.example.feelingsday.Task;
import com.example.feelingsday.TaskStorage;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ImportTask extends AppCompatActivity {
    private TaskStorage taskStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_task);

        EditText urlEditText = findViewById(R.id.edit_text_url);
        Button fetchButton = findViewById(R.id.button_fetch_task);

        fetchButton.setOnClickListener(view -> {
            String url = urlEditText.getText().toString().trim();

            if (!url.isEmpty()) {
                fetchTaskFromUrl(url);
            } else {
                Toast.makeText(this, "Por favor, insira um link válido!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchTaskFromUrl(String urlString) {
        new Thread(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String jsonResponse = response.toString();

                // Processar o JSON
                parseJson(jsonResponse);

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(this, "Erro ao importar a tarefa!", Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }

    private void parseJson(String jsonResponse) {
        taskStorage = new TaskStorage(this);
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            String name = jsonObject.getString("name");
            String duration = jsonObject.getString("duration");
            String goals = jsonObject.getString("goals");
            List<String> goalsList = Arrays.asList(goals.split(","));
            String steps = jsonObject.getString("steps");
            String image = jsonObject.getString("image");

            Task newTask = new Task(name, duration, goalsList, steps, image);
            // Salvar a tarefa no armazenamento
            List<Task> tasks = taskStorage.getTasks();
            tasks.add(newTask);
            taskStorage.saveTasks(tasks);
            runOnUiThread(() -> {
                // Exibir os dados ou salvar no banco de dados
                Toast.makeText(this, "Tarefa Importada: " + name, Toast.LENGTH_SHORT).show();
            });

        } catch (JSONException e) {
            e.printStackTrace();
            runOnUiThread(() ->
                    Toast.makeText(this, "Erro ao processar o JSON!", Toast.LENGTH_SHORT).show()
            );
        }
    }
}
