package com.insta.taskmanager9000.tools;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.insta.taskmanager9000.business.Contributor;
import com.insta.taskmanager9000.business.Task;

public class Parser {
	
	private ArrayList<Task> tasks;
	private ArrayList<Contributor> contributors;
	
	public Pair<ArrayList<Contributor>, ArrayList<Task>> parseTasks(InputSource is){
		
		try{
			SAXParserFactory parsfactory = SAXParserFactory.newInstance();
			SAXParser parser = parsfactory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			
			ParseTaskHandler tskHandler = new ParseTaskHandler();
			reader.setContentHandler(tskHandler);
			
			Log.d("Parser", "Parsing - début, source = " + is);
			reader.parse(is);
			Log.d("Parser", "Parsing - fin");
			
			this.tasks = tskHandler.getTasks();
			this.contributors = tskHandler.getContributors();
			
			return new Pair<ArrayList<Contributor>, ArrayList<Task>>(
												this.contributors, this.tasks);
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println("Can't parse tasks");
			return null;
		}
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public ArrayList<Contributor> getContributors() {
		return contributors;
	}

}
