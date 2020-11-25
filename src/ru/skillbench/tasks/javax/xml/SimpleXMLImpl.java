package ru.skillbench.tasks.javax.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleXMLImpl implements SimpleXML {

    @Override
    public String createXML(String tagName, String textNode) {
        if (tagName == null || tagName.equals(""))return "";
        StringWriter outWriter = new StringWriter();
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().newDocument();;
            factory.setNamespaceAware(true);

            Element root = doc.createElement(tagName);
            root.setTextContent(textNode);
            doc.appendChild(root);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
            transformer.transform(new DOMSource(doc), new StreamResult(outWriter));

        } catch (ParserConfigurationException | TransformerException e) { e.printStackTrace();}
        return outWriter.toString();
    }

    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        List<String>founded = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (founded.size()<1)founded.add(qName);
                }
            };

            if (xmlStream==null)return "";
            Reader reader = new InputStreamReader(xmlStream);
            InputSource is = new InputSource(reader);
            parser.parse(is, handler);
        }
         catch (ParserConfigurationException | IOException e) {e.printStackTrace(); throw new SAXException();
        }
        return founded.get(0);
    }
}
