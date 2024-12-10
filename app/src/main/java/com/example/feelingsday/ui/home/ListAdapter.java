package com.example.feelingsday.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.feelingsday.R;
import com.example.feelingsday.Task;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Task> taskList;

    public ListAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // Reutilizar a view para melhor desempenho
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.taskName = convertView.findViewById(R.id.listName);
            holder.taskTime = convertView.findViewById(R.id.listTime);
            holder.taskImage = convertView.findViewById(R.id.listImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Obter a tarefa atual
        Task task = taskList.get(position);

        // Atualizar os valores da UI com os dados da tarefa
        holder.taskName.setText(task.getTaskName());
        holder.taskTime.setText(task.getTaskDuration());

        // Configurar a imagem da tarefa
        if (task.getImageTask() != null && !task.getImageTask().isEmpty()) {

            Glide.with(context)
                    .load(task.getImageTask())
                    .placeholder(R.drawable.programmer)
                    .error(R.drawable.programmer)
                    .into(holder.taskImage);
        } else {
            holder.taskImage.setImageResource(R.drawable.programmer);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView taskName;
        TextView taskTime;
        ImageView taskImage;
    }
}

