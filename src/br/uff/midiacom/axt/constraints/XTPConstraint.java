package br.uff.midiacom.axt.constraints;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.constraints.XTPConstraintPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPConstraint<T extends XTPConstraint, P extends XTPElement, I extends XTPElementImpl>
        extends XTPConstraintPrototype<T, P, I> implements XTPElement<T, P> {

    
    public XTPConstraint(String select) throws XMLException {
        super(select);
    }
    
    
    public XTPConstraint() throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
                setSelect(att_var);
        
        // set the description
        if(!(att_var = element.getAttribute("description")).isEmpty())
            setDescription(att_var);
    }
}
