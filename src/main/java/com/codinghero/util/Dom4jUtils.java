package com.codinghero.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

public final class Dom4jUtils {
	
	private static Map<String, String> nsURIs = new HashMap<String, String>();
	
	public static Element getRootElementWithNS(String xmlPath, String ns, String uri) 
			throws ParserConfigurationException, SAXException, DocumentException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		SAXParser parser = factory.newSAXParser();
		parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		SAXReader reader = new SAXReader(parser.getXMLReader());
		nsURIs.put(ns, uri);
		reader.getDocumentFactory().setXPathNamespaceURIs(nsURIs);
		return reader.read(xmlPath).getRootElement();
	}

	public static Element getRootElement(String xmlPath) 
			throws ParserConfigurationException, SAXException, DocumentException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SAXReader reader = new SAXReader(parser.getXMLReader());
		return reader.read(xmlPath).getRootElement();
	}
}
