/**
 * 
 */
package com.oxtun.taskmanager.model;

/**
 * @author jdiaz
 *
 */
public class Task {

    private boolean complete;
	private String name;
    private long id;

	/**
	 * @param name
	 */
	public Task(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void toggleComplete() {
        this.complete = !complete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}