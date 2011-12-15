package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.node.XTPNode;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPMappingPrototype;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPMapping<T extends XTPMapping, P extends XTPElement, I extends XTPElementImpl, En extends XTPNode, Ei extends XTPInterface>
        extends XTPMappingPrototype<T, P, I, En, Ei> implements XTPElement<T, P> {

    
    
    public XTPMapping() throws XMLException {
        super();
    }
    
    
    public void load(Element element) throws XMLException {
        String att_var;
        
        // set the component
        if(!(att_var = element.getAttribute("component")).isEmpty()){
            XTPVocabulary voc = (XTPVocabulary) impl.getDoc().getVocabulary();
            setComponent((En)voc.findComponent(att_var));
            
            // set the interface (optional)
            if(!(att_var = element.getAttribute("interface")).isEmpty()){
                Ei comp = (Ei) getComponent().findInterface(att_var);
                if(comp == null)
                    throw new XMLException("Could not find element " + att_var);
                else
                    setInterface(comp);
            }
        }
    }
}
