package com.oxtun.taskmanager.activity;

import com.oxtun.taskmanager.R;
import com.oxtun.taskmanager.application.TaskManagerApplication;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TaskManagerActivity extends Activity {
	

	protected TextView tasksText;
	
	protected EditText taskNameText;
	protected Button addTaskButton;
	protected Button cancelButton;
	
	protected TaskManagerApplication getTaskManagerApplication() {
		return (TaskManagerApplication) getApplication();
	}

	protected void setUpViews() {
		

		tasksText = (TextView)findViewById(R.id.tasks_title);
		
		taskNameText = (EditText)findViewById(R.id.etv_taskName);
		addTaskButton = (Button)findViewById(R.id.btn_addTasks);
		cancelButton = (Button)findViewById(R.id.btn_cancelTask);
		


	}
	
	
}