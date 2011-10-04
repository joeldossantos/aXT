package br.uff.midiacom.axt.constraints;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPConstraints<C extends XTPConstraint, T extends XTPConstraints> extends XTPElement<T> {

    private ElementList<C> constraints;


    public boolean addConstraint(C constraint) {
        //If the constraint was inserted, set its parent
        if(constraints.add(constraint)){
            constraint.setParent(this);
            return true;
        }
        return false;
    }


    public boolean removeConstraint(C constraint) {
        //If the constraint was removed, remove its parent
        if(constraints.remove(constraint)){
            constraint.setParent(null);
            return true;
        }
        return false;
    }


    public boolean hasConstraint(C constraint) {
        return constraints.contains(constraint);
    }


    public boolean hasConstraints() {
        return !constraints.isEmpty();
    }


    public ElementList<C> getConstraints() {
        return constraints;
    }


    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
