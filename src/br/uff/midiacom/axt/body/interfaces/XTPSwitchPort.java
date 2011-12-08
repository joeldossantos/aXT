package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.ana.interfaces.NCLMapping;
import br.uff.midiacom.axt.XTPDoc;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPSwitchPortPrototype;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPSwitchPort<T extends XTPSwitchPort, P extends XTPElement, I extends XTPElementImpl, Em extends NCLMapping, Ei extends XTPInterface>
        extends XTPSwitchPortPrototype<T, P, I, Em, Ei> implements XTPInterface<Ei, P> {


    public XTPSwitchPort(String id) throws XMLException {
        super(id);
    }
    
    
    public XTPSwitchPort() throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
        try{
            // set the id (required)
            if(!(att_var = element.getAttribute("id")).isEmpty())
                setXLabel(att_var);
            else
                throw new XMLException("Could not find id attribute.");
        }
        catch(XMLException ex){
            String aux = getXLabel();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("SwitchPort" + aux + ":\n" + ex.getMessage());
        }
        
        
        // set the component
        if(!(att_var = element.getAttribute("component")).isEmpty()){
            P aux = (P) getParent();
            while(!(aux instanceof XTPDoc)){
                aux = (P) aux.getParent();
//                if((aux = (P) getParent()) == null)
//                    throw new XMLException("Could not find element " + att_var);
            }

            XTPVocabulary voc = (XTPVocabulary) ((XTPDoc) aux).getVocabulary();
            setComponent((En)voc.findComponent(att_var));
            
            // set the interface (optional)
            if(!(att_var = element.getAttribute("interface")).isEmpty()){
                En comp = (En) getComponent().find(att_var);
                if(comp instanceof )
                
                if(refEl == null)
                    throw new XMLException("Could not find element " + att_var);
                setInterface(refEl);
            }
        }
    }
}
