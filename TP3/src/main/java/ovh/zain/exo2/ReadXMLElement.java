package ovh.zain.exo2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

public class ReadXMLElement {
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
                System.out.println("Entrez le nom de l'élément : ");
                String elementName = scanner.nextLine();

                // Récupération de la liste des noeuds avec le nom de l'élément donné
                NodeList nodeList = doc.getElementsByTagName(elementName);
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    System.out.println("Contenu de l'élément " + elementName + " : " + element.getTextContent());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
