package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPComponentPortPrototype<T extends XTPComponentPortPrototype, P extends XTPElement, I extends XMLElementImpl> extends XTPVocabularyElement<T, P, I> {

    
    public XTPComponentPortPrototype(String xlabel) throws XMLException {
        super(xlabel);
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
