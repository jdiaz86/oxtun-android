/**
 * 
 */
package com.oxtun.taskmanager.model;

/**
 * @author jdiaz
 *
 */
public class Task {

	private String name;

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
}