package ovh.zain.exo1;/*
 * Created on 26 nov. 2003
 *
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

public class Parser {

    public PrintWriter out = null;

    //Rem changer Package par ModelElement
    public void parse(String _fichier) throws SAXException, ParserConfigurationException, IOException {

        // Charger le document
        FileInputStream _xml_input_file = new FileInputStream(_fichier);

        try {
            parse(_xml_input_file);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public void parse(InputStream _xml_input_file) throws SAXException, ParserConfigurationException, IOException, TransformerException {

        //cr蠴ion du fichier output
        out = new PrintWriter(new FileOutputStream("./output.html"));
        out.println("<html xmlns:fo=\"http://www.w3.org/1999/XSL/Format\">");
        out.println("<head>");
        out.println("<title>TP3</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><h1 align=\"left\">Domaines </h1>");

        //instancier le contrcuteur de parseurs
        DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();

        //ignorer les commentaires dans les fichiers XML pars豍
        _factory.setIgnoringComments(true);

        // cr褲 un parseur
        DocumentBuilder _builder = _factory.newDocumentBuilder();

        try {

            // Charger le document
            Document doc = _builder.parse(_xml_input_file);


            // normalize the Document object
            doc.getDocumentElement().normalize();



            // iterate over the domain elements and write the HTML content
            NodeList domainList = doc.getElementsByTagName("domain");

            // iterate over the domain elements and write the HTML content
            for (int i = 0; i < domainList.getLength(); i++) {
                Node domainNode = domainList.item(i);
                Element domainElement = (Element) domainNode;
                String domainTitle = domainElement.getElementsByTagName("title").item(0).getTextContent();
                out.println("<h2><a href=\"#" + domainTitle + "\">" + domainTitle + "</a></h2>");
            }
            out.println("<hr><hr>");

            for (int i = 0; i < domainList.getLength(); i++) {
                Node domainNode = domainList.item(i);
                if (domainNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element domainElement = (Element) domainNode;
                    String domainTitle = domainElement.getElementsByTagName("title").item(0).getTextContent();
                    out.println("<table width=\"100%\" border=\"1\">");
                    out.println("<tbody><tr>");
                    out.println("<td width=\"100%\" bgcolor=\"#C0C0C0\">");
                    out.println("<h2><a name=\"" + domainTitle + "\">" + domainTitle + "</a></h2>");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</tbody></table>");
                    out.println("<hr>");

                    NodeList bibRefList = domainElement.getElementsByTagName("bib_ref");
                    for (int j = 0; j < bibRefList.getLength(); j++) {
                        Node bibRefNode = bibRefList.item(j);
                        if (bibRefNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element bibRefElement = (Element) bibRefNode;
                            String bibRefYear = bibRefElement.getElementsByTagName("year").item(0).getTextContent();
                            String bibRefTitle = bibRefElement.getElementsByTagName("title").item(0).getTextContent();
                            String bibRefAuthor = bibRefElement.getElementsByTagName("author").item(0).getTextContent();
                            String bibRefWeblink = bibRefElement.getElementsByTagName("weblink").item(0).getTextContent();

                            out.println("Annee  :" + bibRefYear + "<br>");
                            out.println("<h3>Titre :" + bibRefTitle + "</h3>");
                            out.println("Auteur(s)  :" + bibRefAuthor + "<br>");
                            out.println("Lien : <a href=\"" + bibRefWeblink + "\">" + bibRefWeblink + "</a><br>");
                            out.println("<hr>");
                        }
                    }
                }
            }

            // finish writing the HTML content
            out.println("</body>");
            out.println("</html>");

            // close the PrintWriter
            out.close();

            System.out.println("Conversion completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}