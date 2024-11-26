package com.example.feelingsday.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.feelingsday.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListData> {

    private Context context;
    private ArrayList<ListData> dataArrayList;

    public ListAdapter(Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item, dataArrayList);
        this.context = context;
        this.dataArrayList = dataArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        ListData listData = getItem(position);

        if (listData != null) {
            ImageView listImage = view.findViewById(R.id.listImage);
            TextView listName = view.findViewById(R.id.listName);
            TextView listTime = view.findViewById(R.id.listTime);

            listImage.setImageResource(listData.getImage());
            listName.setText(listData.getName());
            listTime.setText(listData.getTime());
        }

        return view;
    }
}
