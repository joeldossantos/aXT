package br.uff.midiacom.axt.datatype.auxiliar;

import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.elementList.ElementList;


public class LabeledElementList<T extends XTPLabeledElement> extends ElementList<T> {


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
