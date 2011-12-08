package br.uff.midiacom.axt.datatype.auxiliar;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class VocabularyElementList<T extends XTPVocabularyElement, P extends XTPElement> extends ElementList<T, P> {


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
