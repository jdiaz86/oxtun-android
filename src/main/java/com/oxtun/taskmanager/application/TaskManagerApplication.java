/**
 * 
 */
package com.oxtun.taskmanager.application;

import java.util.ArrayList;

import com.oxtun.taskmanager.model.Task;
import com.oxtun.taskmanager.model.TasksSQLiteOpenHelper;
import static com.oxtun.taskmanager.model.TasksSQLiteOpenHelper.*;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author jdiaz
 * 
 */
public class TaskManagerApplication extends Application {

	private ArrayList<Task> currentTask;
    private SQLiteDatabase database;

    @Override
	public void onCreate() {
		super.onCreate();
        TasksSQLiteOpenHelper helper = new TasksSQLiteOpenHelper(this);
        database = helper.getWritableDatabase();
		if (null == getCurrentTask()) {
            setCurrentTask(loadTasks());
			//setCurrentTask(new ArrayList<Task>());
		}
	}

    private ArrayList<Task> loadTasks() {
        currentTask = new ArrayList<Task>();

        Cursor tasksCursor = database.query(
            TASKS_TABLE, new String[] { TASK_ID, TASK_NAME, TASK_COMPLETE},
                null,null,null,null,String.format("%s,%s", TASK_COMPLETE, TASK_NAME)
        );
        tasksCursor.moveToFirst();
        Task t;
        if (! tasksCursor.isAfterLast()) {
            do {
                int id = tasksCursor.getInt(0);
                String name = tasksCursor.getString(1);
                String boolValue = tasksCursor.getString(2);
                boolean complete = Boolean.parseBoolean(boolValue);
                t = new Task(name);
                t.setId(id);
                t.setComplete(complete);
                currentTask.add(t);
            } while (tasksCursor.moveToNext());
        }

        return currentTask;
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
        ContentValues values = new ContentValues();
        values.put(TASK_NAME, task.getName());
        values.put(TASK_COMPLETE, Boolean.toString(task.isComplete()));
        task.setId(database.insert(TASKS_TABLE,null,values));
        currentTask.add(task);
	}

    public void updateTask(Task task) {
        assert (null != task);
        ContentValues values = new ContentValues();
        values.put(TASK_COMPLETE, Boolean.toString(task.isComplete()));

        database.update(TASKS_TABLE,values,"id=?",new String[]{task.getId()+""});
    }

    public void deleteTasks(ArrayList<Long> ids) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<ids.size(); i++) {
            sb.append(ids.get(i));
            if (i < (ids.size()-1)) {
                sb.append(",");
            }
        }

        database.delete(TASKS_TABLE,"id in (" + sb.toString() + ")",null);
    }

}