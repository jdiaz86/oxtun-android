/**
 * 
 */
package com.oxtun.taskmanager.application;

import java.util.ArrayList;

import com.oxtun.taskmanager.model.Task;

import android.app.Application;

/**
 * @author jdiaz
 * 
 */
public class TaskManagerApplication extends Application {

	private ArrayList<Task> currentTask;

	@Override
	public void onCreate() {
		super.onCreate();
		if (null == getCurrentTask()) {
			setCurrentTask(new ArrayList<Task>());
		}
	}

	/**
	 * @return the currentTask
	 */
	public ArrayList<Task> getCurrentTask() {
		return currentTask;
	}

	/**
	 * @param currentTask
	 *            the currentTask to set
	 */
	public void setCurrentTask(ArrayList<Task> currentTask) {
		this.currentTask = currentTask;
	}

	public void addTask(Task task) {
		assert (null != task);
		currentTask.add(task);
	}

}