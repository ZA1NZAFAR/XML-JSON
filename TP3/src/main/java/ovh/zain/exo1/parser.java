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
import java.util.ArrayList;
import java.util.List;

public class parser {

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
        out.println("<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><h1 align=\"center\">Domaines </h1>");

        //instancier le contrcuteur de parseurs
        DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();

        //ignorer les commentaires dans les fichiers XML pars豍
        _factory.setIgnoringComments(true);

        // cr褲 un parseur
        DocumentBuilder _builder = _factory.newDocumentBuilder();

        // Charger le document
        Document doc = _builder.parse(_xml_input_file);


        //get all domain elements and loop through them
        NodeList domains = doc.getElementsByTagName("domain");
        for (int i = 0; i < domains.getLength(); i++) {
            Node domain = domains.item(i);
            if (domain.getNodeType() == Node.ELEMENT_NODE) {
                Element domainElement = (Element) domain;
                String domainTitle = domainElement.getElementsByTagName("title").item(0).getTextContent();

                out.println("<h2>" + domainTitle + "</h2>");

                // 1 table per domain
                out.println("<table border=\"1\" style=\"width:100%\">");

                // get all bib_ref elements and loop through them
                NodeList bibRefs = domainElement.getElementsByTagName("bib_ref");

                // domain table headers
                List<String> tableHeaders = new ArrayList<>();

                for (int j = 0; j < bibRefs.getLength(); j++) {
                    Node bibRef = bibRefs.item(j);
                    if (bibRef.getNodeType() == Node.ELEMENT_NODE) {
                        Element bibRefElement = (Element) bibRef;

                        if (j == 0) {
                            //get tags in bibRefElement and add them to tableHeaders
                            out.println("<tr>");
                            for (int k = 0; k < bibRefElement.getChildNodes().getLength(); k++) {
                                if (bibRefElement.getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE) {
                                    tableHeaders.add(bibRefElement.getChildNodes().item(k).getNodeName());
                                    out.println("<th>" + bibRefElement.getChildNodes().item(k).getNodeName() + "</th>");
                                }
                            }
                            out.println("</tr>");
                        }

                        // get all tags in bibRefElement and add them to table
                        out.println("<tr>");
                        for (String tableHeader : tableHeaders) {
                            out.println("  <td>" + bibRefElement.getElementsByTagName(tableHeader).item(0).getTextContent() + "</td>");
                        }
                        out.println("</tr>");
                    }
                }
                out.println("</table>");
            }
        }
        out.println("</body></html>");
        out.close();
        out.flush();
    }
}