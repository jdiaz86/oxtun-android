package com.oxtun.taskmanager.activity;

import java.util.ArrayList;

import com.oxtun.taskmanager.R;
import com.oxtun.taskmanager.model.Task;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTasksActivity extends TaskManagerActivity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpViews();
		
		listeners();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		showTask();
	}

	private void listeners() {
		addButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ViewTasksActivity.this, AddTaskActivity.class);
				startActivity(intent);
				
			}
		});
	}

	private void showTask() {
		ArrayList<Task> tasks = getTaskManagerApplication().getCurrentTask();
		StringBuffer sb = new StringBuffer();
		for (Task t: tasks) {
			sb.append(String.format("* %s\n", t.toString()));
		}
		tasksText.setText(sb.toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}