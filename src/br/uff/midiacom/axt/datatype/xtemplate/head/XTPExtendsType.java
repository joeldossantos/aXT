package br.uff.midiacom.axt.datatype.xtemplate.head;

import br.uff.midiacom.axt.datatype.xtemplate.XTPDocType;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.reference.DocumentRefType;


public class XTPExtendsType<T extends XTPExtendsType, P extends XTPElement, I extends XMLElementImpl, Ed extends XTPDocType> extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {
    
    protected DocumentRefType<Ed> xtemplate;
    protected Boolean overwriteConstraints;
    
    
    public XTPExtendsType(Ed xtemplate) throws XMLException {
        super();
        setXTemplate(xtemplate);
    }
    
    
    public void setXTemplate(Ed xtemplate) throws XMLException {
        this.xtemplate = new DocumentRefType<Ed>(xtemplate);
    }
    
    
    public Ed getXTemplate() {
        return xtemplate.getElement();
    }
    
    
    public void setOverwriteConstraints(Boolean value) {
        overwriteConstraints = value;
    }
    
    
    public boolean getOverwriteConstraints() {
        return overwriteConstraints;
    }


    @Override
    public boolean compare(T other) {
        return xtemplate.getElement().compare(other.getXTemplate());
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
