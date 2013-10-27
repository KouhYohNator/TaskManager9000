package com.insta.taskmanager9000.tools;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.insta.taskmanager9000.business.Contributor;
import com.insta.taskmanager9000.business.Task;

public class ParseTaskHandler extends DefaultHandler {

	private Task parsedTask;
	private Contributor parsedContributor;
	private ArrayList<Task> tasks;
	private ArrayList<Contributor> contributors;
	@SuppressWarnings("unused")
	private boolean inTask, inContributor, inTodo = false;
	private StringBuffer buffer;
	
	
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	
	public ArrayList<Contributor> getContributors() {
		return this.contributors;
	}

	@Override
	public void startDocument() throws SAXException {}

	@Override
	public void endDocument() throws SAXException {}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		// On entre dans la liste des tâches
		if (localName.equalsIgnoreCase("TASKS")) {
			this.tasks = new ArrayList<Task>();
			Log.d("ParseTaskHandler", "Tasks - entrée");
		}
		// Parsing d'une tâche
		else if (localName.equalsIgnoreCase("TASK")) {

			this.parsedTask = new Task();
			
			for (int i = 0; i < atts.getLength(); i++) {
				String name = atts.getLocalName(i);
				String value = atts.getValue(i);
				
				if (name.equalsIgnoreCase("date")) {
					this.parsedTask.setDate(value);
				} else if (name.equalsIgnoreCase("priority")) {
					this.parsedTask.setPriority(value);
				}
				else if (name.equalsIgnoreCase("state")) {
					this.parsedTask.setState(value);
				}
			}
			
			this.inTask = true;
		}
		// Parsing de l'auteur d'une tâche
		else if(localName.equalsIgnoreCase("AUTHOR")){
			
			for (int i = 0; i < atts.getLength(); i++) {
				String name = atts.getLocalName(i);
				String value = atts.getValue(i);
				
				if (name.equalsIgnoreCase("name")) {
					for(Contributor c : this.contributors){
						if(value.equals(c.getName())){
							this.parsedTask.setAuthor(c);
						}
					}
				}
			}
		}
		// Parsing de la description de la tâche
		else if(localName.equalsIgnoreCase("TODO")){
			// pour la lecture, Cf. characters()
			this.buffer = new StringBuffer();
			this.inTodo = true;
		}
		// Parsing du contributeur à la tâche
		else if (localName.equalsIgnoreCase("TARGET")){
			
			for (int i = 0; i < atts.getLength(); i++) {
				String name = atts.getLocalName(i);
				String value = atts.getValue(i);
				
				if (name.equalsIgnoreCase("name")) {
					for(Contributor c : this.contributors){
						if(value.equals(c.getName())){
							this.parsedTask.setTarget(c);
						}
					}
				}
			}
		}
		// On entre dans la liste des contributeurs
		else if (localName.equalsIgnoreCase("CONTRIBUTORS")) {
			this.contributors = new ArrayList<Contributor>();
			Log.d("ParseTaskHandler", "Contributors - entrée");
		}
		// Parsing d'un contributeur
		else if (localName.equalsIgnoreCase("CONTRIBUTOR")) {
			
			this.parsedContributor = new Contributor();
			
			for (int i = 0; i < atts.getLength(); i++) {
				String name = atts.getLocalName(i);
				String value = atts.getValue(i);
				
				if (name.equalsIgnoreCase("name")) {
					this.parsedContributor.setName(value);
				} else if (name.equalsIgnoreCase("mail")) {
					this.parsedContributor.setMail(value);
				}
				
			}
			
			this.inContributor = true;
		}
		
	}
	
	@Override
	public void characters(char[] ch,int start, int length) throws SAXException{
		String lecture = new String(ch,start,length);
		if(buffer != null) buffer.append(lecture); 
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (localName.equalsIgnoreCase("TASK")) {
			Log.d("ParseTaskHandler", "Parsed task : " + 
					this.parsedTask.toString());
			
			this.tasks.add(this.parsedTask);
			this.parsedTask = null;
			this.inTask = false;
		}
		else if(localName.equalsIgnoreCase("TODO")){
			this.parsedTask.setTodo(this.buffer.toString());
			Log.d("PaseTaskHandler", "buffer : " + this.buffer.toString());
			this.buffer = null;
			this.inTodo = false;
		}
		else if (localName.equalsIgnoreCase("TASKS")) {
			Log.d("ParseTaskHandler", "Tasks - sortie");
		}
		else if (localName.equalsIgnoreCase("CONTRIBUTORS")) {
			Log.d("ParseTaskHandler", "Contributors - sortie");
		}
		else if(localName.equalsIgnoreCase("CONTRIBUTOR")){
			Log.d("ParseTaskHandler", "Parsed contributor : " + 
					this.parsedContributor.toString());
			
			this.contributors.add(this.parsedContributor);
			this.parsedContributor = null;
			this.inContributor = false;
		}
	}

}
