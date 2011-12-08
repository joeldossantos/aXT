package br.uff.midiacom.axt.datatype.xtemplate.head;

import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.axt.datatype.xtemplate.XTPDocPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.reference.DocumentRefType;


public class XTPExtendsPrototype<T extends XTPExtendsPrototype, P extends XTPElement, I extends XTPElementImpl, Ed extends XTPDocPrototype>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {
    
//    protected DocumentRefType<Ed> xtemplate;
    protected SrcType xtemplate;
    protected Boolean overwriteConstraints;
    
    
    public XTPExtendsPrototype(SrcType xtemplate) throws XMLException {
        super();
        setXTemplate(xtemplate);
    }
    
    public XTPExtendsPrototype() throws XMLException {
        super();
    }
    
    
    public void setXTemplate(SrcType xtemplate) throws XMLException {
        this.xtemplate = xtemplate;
    }
    
    
    public SrcType getXTemplate() {
        return xtemplate;
    }
    
    
    public void setOverwriteConstraints(Boolean value) {
        overwriteConstraints = value;
    }
    
    
    public boolean getOverwriteConstraints() {
        return overwriteConstraints;
    }


    @Override
    public boolean compare(T other) {
        return getXTemplate().parse().equals(other.getXTemplate().parse());
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
