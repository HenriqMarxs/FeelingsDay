package com.example.feelingsday.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.feelingsday.R;
import com.example.feelingsday.databinding.ActivityDetailTaskBinding;

public class DetailTaskActivity extends AppCompatActivity {

    private ActivityDetailTaskBinding binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       Intent intent = this.getIntent();
            if (intent != null) {
                String name = intent.getStringExtra("name");
                String time = intent.getStringExtra("time");
                int desc = intent.getIntExtra("desc", R.string.taskProgrammmerDesc);
                int goals = intent.getIntExtra("goals", R.string.taskProgrammmerGoals);
                int image = intent.getIntExtra("image", R.drawable.programmer);

                binding.detailName.setText(name);
                binding.detailTime.setText(time);
                binding.detailDesc.setText(getString(desc));
                binding.detailGoals.setText(getString(goals));
                binding.detailImage.setImageResource(image);

        }

    }
}
