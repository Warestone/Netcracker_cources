package ru.skillbench.tasks.javax.xml;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamWriter;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleXMLImplTest {

    @Test
    void createXML() {
        SimpleXMLImpl simpleXML = new SimpleXMLImpl();
        String s = simpleXML.createXML("root","<R&D>");
    }

    @Test
    void parseRootElement() throws SAXException, FileNotFoundException {
        SimpleXMLImpl simpleXML = new SimpleXMLImpl();
        //InputStream reader = new FileInputStream("D:\\Work\\University\\Netcracker\\src\\ru\\skillbench\\tasks\\javax\\xml\\data.txt");
        InputStream reader = null;
        String name = simpleXML.parseRootElement(reader);
    }
}