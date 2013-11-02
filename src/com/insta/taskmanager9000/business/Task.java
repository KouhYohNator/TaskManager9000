package com.insta.taskmanager9000.business;

import android.annotation.SuppressLint;
import java.io.Serializable;


@SuppressLint("DefaultLocale")
public class Task implements Serializable{

	private static final long serialVersionUID = 934044021934002581L;
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

	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(this.date + " - " + this.author.toString() + "\n");
		result.append(this.state.name() + " / " + this.priority.name() + "\n");
		
		if(this.target != null){
			result.append("->" + this.target.toString() + "\n");
		}
		
		result.append(this.todo);
		return result.toString();
	}

	public Contributor getAuthor() {
		return author;
	}
	public void setAuthor(Contributor author) {
		assert(author != null);
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String value) {
		assert(value != null && !value.isEmpty());
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
		assert(value != null && !value.isEmpty());
		this.state = State.valueOf(value.toUpperCase());
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(String value) {
		assert(value != null && !value.isEmpty());
		this.priority = Priority.valueOf(value.toUpperCase());
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		assert(todo != null && !todo.isEmpty());
		this.todo = todo;
	}
	
}