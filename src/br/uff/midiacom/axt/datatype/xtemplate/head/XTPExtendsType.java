package br.uff.midiacom.axt.datatype.xtemplate.head;

import br.uff.midiacom.axt.datatype.xtemplate.XTPDocType;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.Element;
import br.uff.midiacom.xml.datatype.reference.DocumentRefType;


public class XTPExtendsType<T extends XTPExtendsType, D extends XTPDocType> extends Element<T> implements XTPElement<T> {
    
    protected DocumentRefType<D> xtemplate;
    protected Boolean overwriteConstraints;
    
    
    public XTPExtendsType(D xtemplate) {
        setXTemplate(xtemplate);
    }
    
    
    public void setXTemplate(D xtemplate) throws NullPointerException {
        this.xtemplate = new DocumentRefType<D>(xtemplate);
    }
    
    
    public D getXTemplate() {
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
}
