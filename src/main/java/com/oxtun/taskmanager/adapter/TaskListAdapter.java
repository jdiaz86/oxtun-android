package com.oxtun.taskmanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.oxtun.taskmanager.R;
import com.oxtun.taskmanager.model.Task;
import com.oxtun.taskmanager.views.TaskListItem;

import java.util.ArrayList;

/**
 * Created by jdiaz on 20/06/13.
 */
public class TaskListAdapter extends BaseAdapter {

    private static final String TAG = "TaskListAdapter";

    private ArrayList<Task> tasks;
    private Context context;

    public TaskListAdapter(ArrayList<Task> tasks, Context context) {
        super();
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return ((null != tasks) ? tasks.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG,"Pasa por getView() " + position + " - " + convertView );
        TaskListItem listItem;
        if (null==convertView) {
            listItem = (TaskListItem) View.inflate(context, R.layout.task_list_item, null);
        } else {
            listItem = (TaskListItem) convertView;
        }
        listItem.setTask(tasks.get(position));
        return listItem;
    }

    public void forceReload() {
        notifyDataSetChanged();
    }

    public void toggleTaskCompleteAtPosition(int position) {
        Log.i(TAG,"Pasa por togglePosition()");
        Task task = tasks.get(position);
        task.toggleComplete();
        forceReload();
    }

    public ArrayList<Long> removeCompletedTasks() {
        ArrayList<Task> completedTask = new ArrayList<Task>();
        ArrayList<Long> completedIds = new ArrayList<Long>();
        int i = 0;
        for (Task task : tasks) {
            if (task.isComplete()) {
                completedTask.add(task);
                completedIds.add(task.getId());
            }
        }
        tasks.removeAll(completedTask);
        forceReload();

        return completedIds;

    }
}