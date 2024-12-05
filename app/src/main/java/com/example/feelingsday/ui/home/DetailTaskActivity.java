package com.example.feelingsday.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.example.feelingsday.R;
import com.example.feelingsday.databinding.ActivityDetailTaskBinding;

import java.util.ArrayList;

public class DetailTaskActivity extends AppCompatActivity {
    private ActivityDetailTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            ArrayList<String> goals = intent.getStringArrayListExtra("goals");
            String duration = intent.getStringExtra("duration");
            String steps = intent.getStringExtra("steps");
            int imageResource = intent.getIntExtra("image", R.drawable.programmer);

            binding.detailName.setText(name);
            binding.detailTime.setText(duration);
            binding.detailGoals.setText(TextUtils.join("\n", goals));
            binding.detailDesc.setText(steps);
            binding.detailImage.setImageResource(imageResource);
        }
    }
}
