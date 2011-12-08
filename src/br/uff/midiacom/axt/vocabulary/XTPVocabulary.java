package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPVocabulary<T extends XTPVocabulary, P extends XTPElement, I extends XTPElementImpl, Ecp extends XTPComponent, Ecc extends XTPConnector>
        extends XTPVocabularyPrototype<T, P, I, Ecp, Ecc> implements XTPElement<T, P> {

    
    public XTPVocabulary() throws XMLException {
        super();
    }
    
    
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Searches for a component inside the vocabulary.
     * 
     * @param xLabel
     *          xLabel of the component to be found.
     * @return 
     *          component or null if no component was found.
     */
    public Ecp findComponent(String xLabel) throws XMLException {
        Ecp result;
        
        // search in the vocabulary
        result = components.get(xLabel);
        if(result != null)
            return result;
        
        return null;
    }
}
