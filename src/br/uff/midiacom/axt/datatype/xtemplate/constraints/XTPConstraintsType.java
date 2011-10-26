package br.uff.midiacom.axt.datatype.xtemplate.constraints;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPConstraintsType<T extends XTPConstraintsType, P extends XTPElement, I extends XMLElementImpl, Ec extends XTPConstraintType> extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected ElementList<Ec, T> constraints;
    
    
    public XTPConstraintsType() throws XMLException {
        super();
    }


    public boolean addConstraint(Ec constraint) throws XMLException {
        return constraints.add(constraint, (T) this);
    }


    public boolean removeConstraint(Ec constraint) throws XMLException {
        return constraints.remove(constraint);
    }


    public boolean hasConstraint(Ec constraint) throws XMLException {
        return constraints.contains(constraint);
    }


    public boolean hasConstraints() {
        return !constraints.isEmpty();
    }


    public ElementList<Ec, T> getConstraints() {
        return constraints;
    }


    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
