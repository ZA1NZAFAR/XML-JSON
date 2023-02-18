package ovh.zain.exo2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class RemoveXMLElement {
    public static void main(String[] args) {
        do {


            try {
                File xmlFile = new File("TP3/src/main/java/ovh/zain/exo2/test.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                // Lecture du nom d'un élément depuis la console
                Scanner scanner = new Scanner(System.in);
                System.out.println("Entrez le nom de l'élément à supprimer : ");
                String elementName = scanner.nextLine();

                // Récupération de la liste des noeuds avec le nom de l'élément donné
                NodeList nodeList = doc.getElementsByTagName(elementName);
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    Node parent = node.getParentNode();
                    parent.removeChild(node);
                }

                // Écriture des modifications dans le fichier XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new FileOutputStream(xmlFile));
                transformer.transform(source, result);

                System.out.println("L'élément " + elementName + " a été supprimé du fichier XML.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}