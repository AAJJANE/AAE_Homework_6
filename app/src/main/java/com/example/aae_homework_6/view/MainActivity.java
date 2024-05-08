package com.example.aae_homework_6.view;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aae_homework_6.R;
import com.example.aae_homework_6.model.Task;
import com.example.aae_homework_6.viewmodel.DataBaseHelper;
import com.example.aae_homework_6.viewmodel.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private DataBaseHelper dataBaseHelper;

    List<Task> tasks = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        button = findViewById(R.id.button);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        fetchAllTasks(tasks);

        taskAdapter = new TaskAdapter(this, tasks);

        recyclerView.setAdapter(taskAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));
            }
        });
    }

    public void fetchAllTasks(List<Task> taskList){
        Cursor cursor = dataBaseHelper.readTasks();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Задачи отсутствуют", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                taskList.add(new Task(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }
}