package com.insta.taskmanager9000.tools;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.insta.taskmanager9000.business.Contributor;
import com.insta.taskmanager9000.business.Task;

public class Parser {
	
	private ArrayList<Task> tasks;
	private ArrayList<Contributor> contributors;
	
	public void parseTasks(InputSource is){
		
		try{
			SAXParserFactory parsfactory = SAXParserFactory.newInstance();
			SAXParser parser = parsfactory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			
			ParseTaskHandler tskHandler = new ParseTaskHandler(contributors);
			reader.setContentHandler(tskHandler);
			reader.parse(is);
			
			this.tasks = tskHandler.getTasks();
			this.contributors = tskHandler.getContributors();
			
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println("Can't parse task");
			
		}
	}
	
}
