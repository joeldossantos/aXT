package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.XTPBody;
import br.uff.midiacom.axt.body.node.XTPNode;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortPrototype;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPPort<T extends XTPPort, P extends XTPElement, I extends XTPElementImpl, En extends XTPNode, Ei extends XTPInterface>
        extends XTPPortPrototype<T, P, I, En, Ei> implements XTPInterface<Ei, P> {

    
    public XTPPort(String id) throws XMLException {
        super(id);
    }
    
    
    public XTPPort() throws XMLException {
        super();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<T, P>(this);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
        try{
            // set the id (required)
            if(!(att_var = element.getAttribute("id")).isEmpty())
                setId(att_var);
            else
                throw new XMLException("Could not find id attribute.");
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("Component" + aux + ":\n" + ex.getMessage());
        }
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
            setSelect(att_var);
        
        // set the xLabel
        if(!(att_var = element.getAttribute("xLabel")).isEmpty())
            setXLabel(((XTPVocabulary)impl.getDoc().getVocabulary()).findPort(att_var));
        
        // set the component
        if(!(att_var = element.getAttribute("component")).isEmpty()){
            XTPBody body = (XTPBody) impl.getDoc().getBody();
            setComponent((En)body.findNode(att_var));
            
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
