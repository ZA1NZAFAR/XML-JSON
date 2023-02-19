package ovh.zain.exo3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringReader;

public class XMLTransformer {
    public static void main(String[] args) throws Exception {
        // Load the XML document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("/Users/zainzafar/Desktop/Desktop - Zain’s MacBook Pro/Data/EFREI/Semester 4/XML_JSON/Repo/TP3/src/main/java/ovh/zain/exo3/input.xml"));

        // Create the new document
        Document newDoc = builder.newDocument();
        newDoc.setXmlStandalone(true);
        newDoc.setStrictErrorChecking(true);
        Element list = newDoc.createElement("list");
        newDoc.appendChild(list);

        // Define the DTD
        String dtd = "<!DOCTYPE list [\n" +
                "  <!ELEMENT list (man | woman)*>\n" +
                "  <!ELEMENT man (sons*, daughters*)>\n" +
                "  <!ATTLIST man name CDATA #REQUIRED>\n" +
                "  <!ELEMENT woman (sons*, daughters*)>\n" +
                "  <!ATTLIST woman name CDATA #REQUIRED>\n" +
                "  <!ELEMENT sons (man)*>\n" +
                "  <!ELEMENT daughters (woman)*>\n" +
                "]>\n";
        StringReader dtdReader = new StringReader(dtd);
        InputSource dtdSource = new InputSource(dtdReader);
        builder.setEntityResolver((publicId, systemId) -> dtdSource);
        newDoc.insertBefore(newDoc.createComment(" " + dtd.trim() + " "), newDoc.getDocumentElement());



        // Transform each person element into a man or woman element
        NodeList persons = doc.getElementsByTagName("person");
        for (int i = 0; i < persons.getLength(); i++) {
            Element person = (Element) persons.item(i);
            String gender = person.getAttribute("gender");
            Element newPerson;
            if (gender.equals("M")) {
                newPerson = newDoc.createElement("man");
                insertPerson(newDoc, person, newPerson);
            } else {
                newPerson = newDoc.createElement("woman");
                insertPerson(newDoc, person, newPerson);
            }
            list.appendChild(newPerson);
        }

        // Save the new document
        // Write the new document to a file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(newDoc);
        StreamResult result = new StreamResult("output.xml");
        transformer.transform(source, result);

    }

    private static void insertPerson(Document newDoc, Element person, Element newPerson) {
        String name = person.getElementsByTagName("name").item(0).getTextContent();
        newPerson.setAttribute("name", name);
        Element sons = newDoc.createElement("sons");
        newPerson.appendChild(sons);
        Element daughters = newDoc.createElement("daughters");
        newPerson.appendChild(daughters);
        NodeList children = person.getElementsByTagName("person");
        for (int j = 0; j < children.getLength(); j++) {
            Element child = (Element) children.item(j);
            String childGender = child.getAttribute("gender");
            if (childGender.equals("M")) {
                Element son = newDoc.createElement("man");
                String childName = child.getElementsByTagName("name").item(0).getTextContent();
                son.setAttribute("name", childName);
                sons.appendChild(son);
            } else {
                Element daughter = newDoc.createElement("woman");
                String childName = child.getElementsByTagName("name").item(0).getTextContent();
                daughter.setAttribute("name", childName);
                daughters.appendChild(daughter);
            }
        }
    }
}


