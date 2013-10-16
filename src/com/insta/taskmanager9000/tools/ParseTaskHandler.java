package com.insta.taskmanager9000.tools;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.insta.taskmanager9000.business.Task;

public class ParseTaskHandler extends DefaultHandler {

	private Task parsedTask;
	private ArrayList<Task> tasks;
	
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	

	@Override
	public void startDocument() throws SAXException {
		this.tasks = new ArrayList<Task>();
	}

	@Override
	public void endDocument() throws SAXException {}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (localName.equalsIgnoreCase("TASK")) {

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
			
			//TODO : correspondance entre contributor et task
			Log.i("ParseContributor", "Parsed : " + 
					this.parsedTask.toString());
			
			this.tasks.add(this.parsedTask);
			this.parsedTask = null;
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		// nothing to do
	}

}
