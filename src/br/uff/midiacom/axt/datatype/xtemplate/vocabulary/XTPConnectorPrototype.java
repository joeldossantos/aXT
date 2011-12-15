package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPConnectorPrototype<T extends XTPConnectorPrototype, P extends XTPElement, I extends XTPElementImpl>
        extends XTPVocabularyElement<T, P, I> {

    protected NCLCausalConnector src;
    
    
    public XTPConnectorPrototype(String xlabel, NCLCausalConnector src) throws XMLException {
        super(xlabel);
        setSrc(src);
    }
    
    
    public void setSrc(NCLCausalConnector src) throws XMLException {
        if(src == null)
            throw new NullPointerException("Null connector");

        this.src = src;
    }


    public NCLCausalConnector getSrc(){
        return src;
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    @Deprecated
    public void setId(String id) throws XMLException { }
    
    
    @Deprecated
    public String getId() { return null; }
}
