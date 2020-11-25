package ru.skillbench.tasks.javax.xml;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XPathCallerImplTest {

    @Test
    void getEmployees() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("D:\\Work\\University\\Netcracker\\src\\ru\\skillbench\\tasks\\javax\\xml\\emp-hier.xml"));
        XPathCallerImpl caller = new XPathCallerImpl();
        caller.getEmployees(doc,"10","emp-hier");
    }

    @Test
    void getHighestPayed() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("D:\\Work\\University\\Netcracker\\src\\ru\\skillbench\\tasks\\javax\\xml\\emp-hier.xml"));
        XPathCallerImpl caller = new XPathCallerImpl();
        caller.getHighestPayed(doc,"emp-hier");
    }

    @Test
    void testGetHighestPayed() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("D:\\Work\\University\\Netcracker\\src\\ru\\skillbench\\tasks\\javax\\xml\\emp-hier.xml"));
        XPathCallerImpl caller = new XPathCallerImpl();
        caller.getHighestPayed(doc, "20","emp-hier");
    }

    @Test
    void getTopManagement() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("D:\\Work\\University\\Netcracker\\src\\ru\\skillbench\\tasks\\javax\\xml\\emp.xml"));
        XPathCallerImpl caller = new XPathCallerImpl();
        caller.getTopManagement(doc,"emp");
    }

    @Test
    void getOrdinaryEmployees() {
    }

    @Test
    void getCoworkers() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("D:\\Work\\University\\Netcracker\\src\\ru\\skillbench\\tasks\\javax\\xml\\emp.xml"));
        XPathCallerImpl caller = new XPathCallerImpl();
        caller.getCoworkers(doc, "","emp");
    }
}