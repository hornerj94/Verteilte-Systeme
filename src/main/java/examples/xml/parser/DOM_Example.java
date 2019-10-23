package examples.xml.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Example for an DOM-parser.
 */
public class DOM_Example {
    // ---------------------------------------------------------------------------------------------

    /** The number of elements of the parsed document. */
    private static long number_elements;

    /** The number of attributes of the parsed document. */
    private static long number_attributes;

    // ---------------------------------------------------------------------------------------------

    /**
     * Parses the given file with an DOM-parser.
     * 
     * @param args The first argument should be the xml document
     * @throws SAXException                 Throws an SAXException if an general SAX
     *                                      error or warning occurred
     * @throws IOException                  Throws an IOException if an I/O
     *                                      operation failed or interrupted
     * @throws ParserConfigurationException Throws an ParserConfigurationException
     *                                      that indicates a serious configuration error
     */
    public static void main(String[] args) 
            throws ParserConfigurationException, SAXException, IOException {
        // DocumentBuilderFactory anlegen und konfigurieren
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        // Kontrolliert das XML-Dokument auf die definierten DTD-Regeln
        dbf.setValidating(true);
        dbf.setCoalescing(true);
        dbf.setIgnoringComments(true);

        // DOMParser erzeugen
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

        File xmlFile = new File(args[0]);
        Document document = documentBuilder.parse(xmlFile);

        printDocument(document);

        System.out.println("Zahl der Elemente: " + number_elements);
        System.out.println("Zahl der Attribute: " + number_attributes);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Starts to print the document to the console.
     * 
     * @param document The xml document to parse
     */
    private static void printDocument(Document document) {
        number_elements = 0;
        number_attributes = 0;
        traverse_and_print(document.getDocumentElement(), "");
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Runs recursively through the element and the element childs. It prints the name of each 
     * element, the attributes and the total amount of elements to the console.
     * 
     * @param element The element to print to the console
     * @param indent The indent to print to the console
     */
    private static void traverse_and_print(Element element, String indent) {
        String nextIndent = indent + "  ";
        number_elements++;

        System.out.print(indent);
        System.out.print("Element '");
        System.out.print(element.getNodeName());
        System.out.println("'");

        NamedNodeMap attributes = element.getAttributes();
        number_attributes += attributes.getLength();
        for (int i = 0; i < attributes.getLength(); ++i) {
            Node attribute = attributes.item(i);

            System.out.print(nextIndent);
            System.out.print("Attribut '");
            System.out.print(attribute.getNodeName());
            System.out.print("': '");
            System.out.print(attribute.getNodeValue());
            System.out.println("'");
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node currentChild = children.item(i);

            switch (currentChild.getNodeType()) {
            case Node.TEXT_NODE:
                String trimmedValue = currentChild.getNodeValue().trim();
                if (trimmedValue.length() == 0)
                    break;
                System.out.print(nextIndent);
                System.out.print("Text '");
                System.out.print(currentChild.getNodeValue());
                System.out.println("'");
                break;
            case Node.ELEMENT_NODE:
                traverse_and_print((Element) currentChild, nextIndent);
                break;
            default:
                break;
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
}
