package br.uff.midiacom.axt;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 *
 * @author Flavia
 */
public class XTPParsingErrorHandler implements ErrorHandler {

    private XMLReader reader;


    public XTPParsingErrorHandler(XMLReader reader) {
        this.reader = reader;
    }
    public void warning(SAXParseException exception) throws SAXException {
        XMLElement element = (XMLElement) reader.getContentHandler();
        element.addWarning("PARSER WARNING: " + exception.getMessage());
    }

    public void error(SAXParseException exception) throws SAXException {
        XMLElement element = (XMLElement) reader.getContentHandler();
        element.addError("PARSER ERROR: " + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        XMLElement element = (XMLElement) reader.getContentHandler();
        element.addError("PARSER FATALERROR: " + exception.getMessage());
    }

}
