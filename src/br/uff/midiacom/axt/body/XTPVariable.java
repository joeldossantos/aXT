package br.uff.midiacom.axt.body;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.XTPVariablePrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import org.w3c.dom.Element;


public class XTPVariable<T extends XTPVariable, P extends XTPElement, I extends XTPElementImpl>
        extends XTPVariablePrototype<T, P, I> implements XTPElement<T, P> {

    
    public XTPVariable(String name, String select) throws XMLException {
        super(name, select);
    }
    
    
    public XTPVariable() throws XMLException {
        super();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>(this);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
        // set the name
        if(!(att_var = element.getAttribute("name")).isEmpty())
                setName(att_var);
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
            setSelect(att_var);
    }
}
