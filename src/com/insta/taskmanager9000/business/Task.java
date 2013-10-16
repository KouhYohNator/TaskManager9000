package com.insta.taskmanager9000.business;


public class Task {

	private Contributor author;
	private String date;
	private Contributor target;
	private State state;
	private Priority priority;
	private String todo;
	
	
	public Task(Contributor author, String date, Contributor target,
			State state, Priority priority, String todo) {
		this(author, date, state, priority, todo);
		this.target = target;
	}
	
	public Task(Contributor author, String date, State state,
			Priority priority, String todo) {
		this.author = author;
		this.date = date;
		this.target = null;
		this.state = state;
		this.priority = priority;
		this.todo = todo;
	}
	
	public Task() {}
	


	public Contributor getAuthor() {
		return author;
	}
	public void setAuthor(Contributor author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String value) {
		this.date = value;
	}
	public Contributor getTarget() {
		return target;
	}
	public void setTarget(Contributor assignedTo) {
		this.target = assignedTo;
	}
	public State getState() {
		return state;
	}
	public void setState(String value) {
		this.state = State.valueOf(value);
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(String value) {
		this.priority = Priority.valueOf(value);
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	
}