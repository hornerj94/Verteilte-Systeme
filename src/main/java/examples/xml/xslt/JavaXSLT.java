package examples.xml.xslt;

import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

class JavaXSLT {
    //----------------------------------------------------------------------------------------------

    /** The start window of the application. */
    private StartWindow startWindow = new StartWindow(500, 200, 100, 600, 
            "Meldungen und Fehlermeldungen");

    //----------------------------------------------------------------------------------------------

    /**
     * Creates an object of the XSLT processor.
     * 
     * @param fileArray Contains the necessary files
     */
    public JavaXSLT(String fileArray[]) {
        transforming(fileArray);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Transforms the xml and the xsl stylesheet to an new html document.
     * 
     * @param args The first argument, xml-document 
     *             The second argument, xsl-document 
     *             The third argument, html-document
     */
    public static void main(String args[]) {

        if (args.length != 3) {
            System.out.println("Syntax: JavaXSLT <XML-Datei> <XSL-Datei> <Ziel-HTML-Datei>");
            System.exit(1);
        }
        new JavaXSLT(args);
    }

    //==============================================================================================

    /**
     * Transforms the given files.
     * 
     * @param fileArray Contains the necessary files
     */
    public void transforming(String fileArray[]) {
        File xmlFile = new File(fileArray[0]);
        File xslStylesheetFile = new File(fileArray[1]);
        File htmlFile = new File(fileArray[2]);
        try {
            startWindow.printText("\nDas Formen startet");

            StreamSource xslSSSource = new StreamSource(xslStylesheetFile);
            StreamSource xmlSource = new StreamSource(xmlFile);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xslSSSource);

            FileWriter fileWriter = new FileWriter(htmlFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StreamResult result = new StreamResult(bufferedWriter);
            
            transformer.transform(xmlSource, result);
            
            String out = result.toString();
            bufferedWriter.write(out, 0, out.indexOf("javax"));
            bufferedWriter.close();

            startWindow.printText("\nFormen beendet");
        } catch (IOException e) {
            startWindow.printText("IO-Fehler:\n" + e);
        } catch (TransformerException e) {
            startWindow.printText("Transorm-Fehler:\n" + e);
        }
    }

    //----------------------------------------------------------------------------------------------
}

class StartWindow extends JFrame implements WindowListener {
    //----------------------------------------------------------------------------------------------

    /**  The serial version UID of the class. */
    private static final long serialVersionUID = 219252518431336825L;

    //==============================================================================================

    /** The area for the text. */
    private JTextArea textArea;

    //----------------------------------------------------------------------------------------------

    /**
     * Creates an object of the starting window.
     * 
     * @param width The width of the window
     * @param height The height of the window.
     * @param posx The x-coordinate of the window.
     * @param posy The y-coordinate of the window.
     * @param title The title of the window.
     */
    public StartWindow(final int width, final int height, final int posx, final int posy, 
            final String title) {
        setSize(width, height);
        setLocation(posx, posy);
        setTitle(title);
        textArea = new JTextArea();
        Container content = getContentPane();
        content.add(textArea);
        addWindowListener(this);
        setVisible(true);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Prints the given text to the window.
     * 
     * @param text The text to print.
     */
    public void printText(final String text) {
        textArea.append(text);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowClosing(final WindowEvent evt) {
        dispose();
        System.exit(0);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowOpened(final WindowEvent evt) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowIconified(final WindowEvent evt) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowDeiconified(final WindowEvent evt) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowClosed(final WindowEvent evt) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowActivated(final WindowEvent evt) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowDeactivated(final WindowEvent evt) { }

    //----------------------------------------------------------------------------------------------
}
