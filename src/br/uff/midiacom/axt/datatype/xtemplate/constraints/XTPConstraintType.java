package br.uff.midiacom.axt.datatype.xtemplate.constraints;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPConstraintType<T extends XTPConstraintType, P extends XTPElement, I extends XMLElementImpl> extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected StringType select;
    protected StringType description;
    
    
    public XTPConstraintType(String select) throws XMLException {
        super();
        setSelect(select);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
