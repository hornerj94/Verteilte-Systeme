package examples.xml.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Example for another SAX-parser.
 */
public class SAX_Example2 {
    // ---------------------------------------------------------------------------------------------

    /** The number of elements of the parsed document. */
    private static long number_elements;

    /** The number of attributes of the parsed document. */
    private static long number_attributes;

    /** The number of characters of the parsed document. */
    private static long number_characters;

    /**
     * Parses the given file with an SAX-parser.
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
        // SAXParserFactory anlegen und konfigurieren
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        // Kontrolliert das XML-Dokument auf die definierten DTD-Regeln
        spf.setValidating(true);

        // SAXParser erzeugen
        SAXParser parser = spf.newSAXParser();

        File xmlFile = new File(args[0]);
        System.out.println("XML File: " + xmlFile);
        DefaultHandler myHandler = new MyHandler();

        parser.parse(xmlFile, myHandler);
    }

    // ---------------------------------------------------------------------------------------------

    private static class MyHandler extends DefaultHandler {
        // -----------------------------------------------------------------------------------------

        /**
         * {@inheritDoc}
         */
        @Override
        public void startDocument() {
            number_elements = 0;
            number_attributes = 0;
            number_characters = 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void startElement(final String uri,final String name,final String qName,
                final Attributes attrs) {
            number_elements++;
            if (attrs != null) { number_attributes += attrs.getLength(); }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void characters(final char ch[],final int start,final int length) {
            number_characters += length;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void endDocument() {
            System.out.println("\nResults:");
            System.out.println("  Zahl der Elemente: " + number_elements);
            System.out.println("  Zahl der Attribute: " + number_attributes);
            System.out.println("  Zahl der Characters: " + number_characters);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void warning(final SAXParseException ex) {
            System.err.println("[Warning]");
            ex.printStackTrace();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void error(final SAXParseException ex) {
            System.err.println("[Error]");
            ex.printStackTrace();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void fatalError(final SAXParseException ex) {
            System.err.println("[Fatal Error]");
            ex.printStackTrace();
        }

        // -----------------------------------------------------------------------------------------
    }

    // ---------------------------------------------------------------------------------------------
}


