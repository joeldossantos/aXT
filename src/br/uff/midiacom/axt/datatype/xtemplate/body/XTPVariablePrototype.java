package br.uff.midiacom.axt.datatype.xtemplate.body;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPVariablePrototype<T extends XTPVariablePrototype, P extends XTPElement, I extends XTPElementImpl>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected StringType name;
    protected StringType select;
    
    
    public XTPVariablePrototype(String name, String select) throws XMLException {
        setName(name);
        setSelect(select);
    }
    
    public XTPVariablePrototype() throws XMLException {
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>();
    }
    
    
    public void setName(String name) throws XMLException {
        if(name == null)
            throw new NullPointerException("Null String.");
        
        this.name = new StringType(name);
    }
    
    
    public String getName() {
        if(name != null)
            return name.getValue();
        else
            return null;
    }


    public void setSelect(String select) throws XMLException {
        if(select == null)
            throw new NullPointerException("Null String.");

        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }


    public boolean compare(T other) {
        return getName().equals(other.getName());
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
