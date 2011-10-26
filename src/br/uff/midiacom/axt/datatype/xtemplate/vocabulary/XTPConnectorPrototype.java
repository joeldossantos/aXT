package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPConnectorPrototype<T extends XTPConnectorPrototype, P extends XTPElement, I extends XMLElementImpl> extends XTPVocabularyElement<T, P, I> {

    protected StringType src;
    
    
    public XTPConnectorPrototype(String xlabel, String src) throws XMLException {
        super(xlabel);
        setSrc(src);
    }
    
    
    public void setSrc(String src) throws XMLException {
        if(src == null)
            throw new NullPointerException("Null String");

        this.src = new StringType(src);
    }


    public String getSrc(){
        return src.getValue();
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    @Deprecated
    public void setId(String id) throws XMLException { }
    
    
    @Deprecated
    public String getId() { return null; }
}
