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
import com.example.feelingsday.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private final ArrayList<ListData> dataArrayList = new ArrayList<ListData>();
    private ListAdapter listAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String [] nameList = {"Program", "Study react"};
        String [] timeList = {"5 month", "1 year"};
        int[] imageList = {R.drawable.programmer, R.drawable.study};
        int [] goalList = {R.string.taskProgrammmerGoals, R.string.StudyReactGoals};
        int [] descList = {R.string.StudyReactDesc, R.string.StudyReactDesc};

        if(dataArrayList.isEmpty()) {
            for (int i = 0; i < imageList.length; i++) {
                ListData listData = new ListData(nameList[i], timeList[i], goalList[i], descList[i], imageList[i]);
                dataArrayList.add(listData);
            }
        }
         listAdapter = new ListAdapter(getActivity(), dataArrayList);
         binding.listView.setAdapter(listAdapter);
         binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

             public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                 Intent intent = new Intent(getActivity(), DetailTaskActivity.class);
                 intent.putExtra("name", nameList[i]);
                 intent.putExtra("time", timeList[i]);
                 intent.putExtra("goal", goalList[i]);
                 intent.putExtra("desc", descList[i]);
                 intent.putExtra("image", imageList[i]);
               startActivity(intent);
             }

         });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}