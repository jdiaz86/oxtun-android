/**
 * 
 */
package com.oxtun.taskmanager.activity;

import com.oxtun.taskmanager.R;
import com.oxtun.taskmanager.model.Task;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;


/**
 * @author jdiaz
 *
 */
public class AddTaskActivity extends TaskManagerActivity {

	private boolean changesPending;
	private AlertDialog unsavedChangesDialog;
	
	private static final String TAG = "addTaskActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_tasks);
		setUpViews();
		
		listeners();
	}

	private void listeners() {
		addTaskButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				addTask();
				
			}
		});
		
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				cancel();
				
			}
		});
		
		taskNameText.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				changesPending = true;
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

	protected void cancel() {
		Log.i(TAG,"taskName: '" + (taskNameText.getText().toString().equals("")));
		if (changesPending && !(taskNameText.getText().toString().equals(""))) {
			unsavedChangesDialog = new AlertDialog.Builder(this)
				.setTitle("Unsaved Changes")
				.setMessage("Would you like to save this changes?")
				.setPositiveButton("Add Task", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						addTask();
						
					}
				})
				.setNeutralButton("Discard", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
						
					}
				})
				.setNegativeButton("Cancel", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						unsavedChangesDialog.cancel();
						
					}
				})
				.create();
			unsavedChangesDialog.show();
		} else {
			finish();
		}
	}

	protected void addTask() {
		String taskName = taskNameText.getText().toString();
		Task task = new Task(taskName);
		getTaskManagerApplication().addTask(task);
		finish();
	}

	
}