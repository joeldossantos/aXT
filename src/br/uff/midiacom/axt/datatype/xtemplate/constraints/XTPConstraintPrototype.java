package br.uff.midiacom.axt.datatype.xtemplate.constraints;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPConstraintPrototype<T extends XTPConstraintPrototype, P extends XTPElement, I extends XTPElementImpl>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected StringType select;
    protected StringType description;
    
    
    public XTPConstraintPrototype(String select) throws XMLException {
        super();
        setSelect(select);
    }
    
    public XTPConstraintPrototype() throws XMLException {
        super();
    }
    
    
    public void setSelect(String select) throws XMLException {
        if(select == null)
            throw new XMLException("Null select String");

        this.select = new StringType(select);
    }
    
    
    public String getSelect() {
        return this.select.getValue();
    }
    
    
    public void setDescription(String description) throws XMLException {
        this.description = new StringType(description);
    }
    
    
    public String getDescription() {
        return this.description.getValue();
    }


    public boolean compare(T other) {
        return getSelect().equals(other.getSelect());
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
