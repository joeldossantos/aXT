package br.uff.midiacom.axt.constraints;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.constraints.XTPConstraintsPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPConstraints<T extends XTPConstraints, P extends XTPElement, I extends XMLElementImpl, Ec extends XTPConstraint> extends XTPConstraintsPrototype<T, P, I, Ec> implements XTPElement<T, P> {

    
    public XTPConstraints() throws XMLException {
        super();
    }
    
    
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
