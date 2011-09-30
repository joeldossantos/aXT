package br.uff.midiacom.axt.constraints;

import br.uff.midiacom.axt.XTPElement;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


public class XTPConstraints extends XTPElement {

    public XTPConstraints(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
