package com.insta.taskmanager9000.tools;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.insta.taskmanager9000.business.Contributor;
import com.insta.taskmanager9000.business.Task;

public class Parser {

	public ArrayList<Contributor> parseContributors(InputSource is){
		
		try{
			SAXParserFactory parsfactory = SAXParserFactory.newInstance();
			SAXParser parser = parsfactory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			
			ParseContributorHandler contHandler = new ParseContributorHandler();
			reader.setContentHandler(contHandler);
			reader.parse(is);
			
			return contHandler.getContributors();
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println("Can't parse contributor");
			
			return null;
		}
	}
	
	public ArrayList<Task> parseTasks(InputSource is){
		
		try{
			SAXParserFactory parsfactory = SAXParserFactory.newInstance();
			SAXParser parser = parsfactory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			
			ParseTaskHandler tskHandler = new ParseTaskHandler();
			reader.setContentHandler(tskHandler);
			reader.parse(is);
			
			return tskHandler.getTasks();
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println("Can't parse task");
			
			return null;
		}
	}
	
}
