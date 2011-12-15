package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPSwitchPortPrototype;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPSwitchPort<T extends XTPSwitchPort, P extends XTPElement, I extends XTPElementImpl, Em extends XTPMapping, Ei extends XTPInterface>
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
        NodeList nl;
        
        // set the xLabel (required)
        if(!(att_var = element.getAttribute("xLabel")).isEmpty())
            setXLabel(((XTPVocabulary)impl.getDoc().getVocabulary()).findComponent(att_var));
        else
            throw new XMLException("Could not find xLabel attribute.");
        
        
        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the area
                    if(el.getTagName().equals("mapping")){
                        Em inst = createMapping();
                        addMapping(inst);
                        inst.load(el);
                    }
                }
            }
        }catch(XMLException ex){
            throw new XMLException("SwitchPort > " + ex.getMessage());
        }
    }
    
    /**
     * Function to create the child element <i>mapping</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>mapping</i>.
     */
    protected Em createMapping() throws XMLException {
        return (Em) new XTPMapping();
    }
}
