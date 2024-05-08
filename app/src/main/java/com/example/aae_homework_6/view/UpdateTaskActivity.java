package com.example.aae_homework_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aae_homework_6.R;
import com.example.aae_homework_6.viewmodel.DataBaseHelper;

public class UpdateTaskActivity extends AppCompatActivity {

    // поля
    private EditText taskName, taskDescription;
    private Button buttonUpdate, buttonDelete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        taskName = findViewById(R.id.task_name);
        taskDescription = findViewById(R.id.task_description);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        taskName.setText(intent.getStringExtra("name"));
        taskDescription.setText(intent.getStringExtra("description"));
        id = intent.getStringExtra("id");

        buttonUpdate.setOnClickListener(listener);
        buttonDelete.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!TextUtils.isEmpty(taskName.getText().toString()) && !TextUtils.isEmpty(taskDescription.getText().toString())) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());

                int viewId = view.getId();
                if (viewId == R.id.button_update) {
                    dataBaseHelper.updateNotes(taskName.getText().toString(), taskDescription.getText().toString(), id);
                } else if (viewId == R.id.button_delete) {
                    dataBaseHelper.deleteSingleItem(id);
                }

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Данные не обновились", Toast.LENGTH_SHORT).show();
            }
        }
    };
}