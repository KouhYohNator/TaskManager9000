package com.insta.taskmanager9000.tools;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.insta.taskmanager9000.business.Contributor;

public class ParseContributorHandler extends DefaultHandler {
	
	private Contributor parsedContributor;
	private ArrayList<Contributor> contributors;

	public ArrayList<Contributor> getContributors() {
		return this.contributors;
	}

	@Override
	public void startDocument() throws SAXException {
		this.contributors = new ArrayList<Contributor>();
	}

	@Override
	public void endDocument() throws SAXException {}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (localName.equalsIgnoreCase("CONTRIBUTOR")) {

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
			Log.i("ParseContributor", "Parsed : " + 
					this.parsedContributor.toString());
			
			this.contributors.add(this.parsedContributor);
			this.parsedContributor = null;
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
	}
	
}
