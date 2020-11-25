package ru.skillbench.tasks.javax.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;

public class XPathCallerImpl implements XPathCaller {
    private class XMLWorker {
        public Element[] convertToElement(NodeList nodes){
            Element[] elements = new Element[nodes.getLength()];
            for (int i =0; i<nodes.getLength(); i++)elements[i] = (Element) nodes.item(i);
            return elements;
        }
        public XPathExpression getXPathExpression(String expression) throws XPathExpressionException {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            return xpath.compile(expression);
        }
    }
    
    @Override
    public Element[] getEmployees(Document src, String dept, String docType) {
        if (src!=null && dept!=null && docType !=null)
        {
            try
            {
                XMLWorker xmlw = new XMLWorker();
                XPathExpression xPathExpression = null;
                if (docType.equals("emp")) xPathExpression = xmlw.getXPathExpression("/content/emp/employee[@deptno='"+dept+"']");
                else if(docType.equals("emp-hier")) xPathExpression = xmlw.getXPathExpression(".//employee[@deptno='"+dept+"']");
                else return new Element[0];
                return xmlw.convertToElement((NodeList) xPathExpression.evaluate(src, XPathConstants.NODESET));
            } catch (Exception s) {}
        }
        return new Element[0];
    }

    @Override
    public String getHighestPayed(Document src, String docType) {
        if (src!=null && docType !=null)
        {
            try
            {
                XMLWorker xmlw = new XMLWorker();
                XPathExpression xPathExpression = null;
                if (docType.equals("emp")) xPathExpression = xmlw.getXPathExpression("/content/emp/employee");
                else if(docType.equals("emp-hier")) xPathExpression = xmlw.getXPathExpression(".//employee");
                else return null;
                NodeList nodeList = (NodeList) xPathExpression.evaluate(src, XPathConstants.NODESET);
                double max = 0;
                String name = "";
                for (int i = 0; i<nodeList.getLength(); i++)
                {
                    Element element = (Element) nodeList.item(i);
                    double salary = Double.parseDouble(element.getElementsByTagName("sal").item(0).getTextContent());
                    if (salary>max)
                    {
                        max = salary;
                        name = element.getElementsByTagName("ename").item(0).getTextContent();
                    }
                }
                return name;
            } catch (Exception s) {}
        }
        return null;
    }

    @Override
    public String getHighestPayed(Document src, String dept, String docType) {
        if (src!=null && docType !=null && dept!=null)
        {
            try
            {
                XMLWorker xmlw = new XMLWorker();
                XPathExpression xPathExpression = null;
                if (docType.equals("emp")) xPathExpression = xmlw.getXPathExpression("/content/emp/employee[@deptno='"+dept+"']");
                else if(docType.equals("emp-hier")) xPathExpression = xmlw.getXPathExpression(".//employee[@deptno='"+dept+"']");
                else return null;
                NodeList nodeList = (NodeList) xPathExpression.evaluate(src, XPathConstants.NODESET);
                double max = 0;
                String name = "";
                for (int i = 0; i<nodeList.getLength(); i++)
                {
                    Element element = (Element) nodeList.item(i);
                    double salary = Double.parseDouble(element.getElementsByTagName("sal").item(0).getTextContent());
                    if (salary>max)
                    {
                        max = salary;
                        name = element.getElementsByTagName("ename").item(0).getTextContent();
                    }
                }
                return name;
            } catch (Exception s) {}
        }
        return null;
    }

    @Override
    public Element[] getTopManagement(Document src, String docType) {
        if (src!=null && docType !=null)
        {
            try
            {
                XMLWorker xmlw = new XMLWorker();
                XPathExpression xPathExpression = null;
                if (docType.equals("emp")) xPathExpression = xmlw.getXPathExpression("/content/emp/employee[not(@mgr)]");
                else if(docType.equals("emp-hier")) xPathExpression = xmlw.getXPathExpression("/employee");
                else return null;
                NodeList nodeList = (NodeList) xPathExpression.evaluate(src, XPathConstants.NODESET);
                return xmlw.convertToElement(nodeList);
            } catch (Exception s) {}
        }
        return new Element[0];
    }

    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        if (src!=null && docType !=null)
        {
            try
            {
                XMLWorker xmlw = new XMLWorker();
                XPathExpression xPathExpression = null;
                if (docType.equals("emp")) xPathExpression = xmlw.getXPathExpression("/content/emp/employee[not(@empno = (/content/emp/employee/@mgr))]");
                else if(docType.equals("emp-hier")) xPathExpression = xmlw.getXPathExpression("//employee[not(./employee)]");
                else return null;
                NodeList nodeList = (NodeList) xPathExpression.evaluate(src, XPathConstants.NODESET);
                return xmlw.convertToElement(nodeList);
            } catch (Exception s) {}
        }
        return new Element[0];
    }

    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        if (src!=null && docType !=null && empno !=null)
        {
            try
            {
                XMLWorker xmlw = new XMLWorker();
                XPathExpression xPathExpression = null;
                if (docType.equals("emp")) xPathExpression = xmlw.getXPathExpression( "/content/emp/employee[" + "not(@empno='" + empno + "') and @mgr = (/content/emp/employee[@empno='" + empno + "']/@mgr)]");
                else if(docType.equals("emp-hier")) xPathExpression = xmlw.getXPathExpression("//employee[@empno='" + empno + "']/../employee[not(@empno='" + empno + "')]");
                else return null;
                NodeList nodeList = (NodeList) xPathExpression.evaluate(src, XPathConstants.NODESET);
                return xmlw.convertToElement(nodeList);
            } catch (Exception s) {}
        }
        return new Element[0];
    }
}
