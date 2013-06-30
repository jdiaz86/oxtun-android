package com.oxtun.taskmanager.activity;

import java.util.ArrayList;

import com.oxtun.taskmanager.R;
import com.oxtun.taskmanager.adapter.TaskListAdapter;
import com.oxtun.taskmanager.application.TaskManagerApplication;
import com.oxtun.taskmanager.model.Task;


import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTasksActivity extends ListActivity {

    public static final String TAG = "ViewTasksActivity";

    protected Button addButton;
    private TaskManagerApplication app;
    private TaskListAdapter adapter;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.add_button);
        removeButton = (Button) findViewById(R.id.remove_button);

        listeners();

        app = (TaskManagerApplication) getApplication();
        adapter = new TaskListAdapter(app.getCurrentTask(), this);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        adapter.toggleTaskCompleteAtPosition(position);
        Task task = (Task) adapter.getItem(position);
        app.updateTask(task);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.forceReload();
    }

    private void listeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ViewTasksActivity.this, AddTaskActivity.class);
                startActivity(intent);

            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removeCompletedTasks();
            }
        });
    }

    private void removeCompletedTasks() {
        ArrayList<Long> ids = adapter.removeCompletedTasks();
        Log.i(TAG,"ids: " + ids.size());
        if (ids.size() == 0) {
            Toast.makeText(this,"There are no tasks selected to delete.",Toast.LENGTH_LONG).show();
        } else {
            app.deleteTasks(ids);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}