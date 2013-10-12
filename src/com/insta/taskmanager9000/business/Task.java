package com.insta.taskmanager9000.business;

import android.text.format.DateUtils;

public class Task {

	private Contributor author;
	private DateUtils date;
	private Contributor assignedTo;
	private State state;
	private Priority priority;
	private String todo;
	
	
	public Task(Contributor author, DateUtils date, Contributor assignedTo,
			State state, Priority priority, String todo) {
		this.author = author;
		this.date = date;
		this.assignedTo = assignedTo;
		this.state = state;
		this.priority = priority;
		this.todo = todo;
	}
	
	public Task(Contributor author, DateUtils date, State state,
			Priority priority, String todo) {
		this.author = author;
		this.date = date;
		this.assignedTo = null;
		this.state = state;
		this.priority = priority;
		this.todo = todo;
	}


	public Contributor getAuthor() {
		return author;
	}
	public void setAuthor(Contributor author) {
		this.author = author;
	}
	public DateUtils getDate() {
		return date;
	}
	public void setDate(DateUtils date) {
		this.date = date;
	}
	public Contributor getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Contributor assignedTo) {
		this.assignedTo = assignedTo;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	
}