package examples.xml.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Example for an SAX-parser.
 */
public class SAX_Example {
    // ---------------------------------------------------------------------------------------------

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
        public void characters(final char[] ch, final int start, final int length) 
                throws SAXException {
            // Erzeugt String aus geparsten Characters und schneidet Leerzeichen ab
            String characters = new String(ch, start, length).trim();
            if (characters.length() > 0) { System.out.println(characters); }
        }
    
        // -----------------------------------------------------------------------------------------
    }
    
    // ---------------------------------------------------------------------------------------------
}
