package br.uff.midiacom.axt.datatype.auxiliar;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class LabeledElementList<T extends XTPLabeledElement, P extends XTPElement> extends ElementList<T, P> {


    public boolean remove(String xlabel) {
        return elements.remove(get(xlabel));
    }


    public T get(String xlabel) {
        for(T el : elements){
            if(el.getXLabel().equals(xlabel))
                return el;
        }
        return null;
    }
}
