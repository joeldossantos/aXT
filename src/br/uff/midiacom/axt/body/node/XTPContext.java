package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.interfaces.XTPPort;
import br.uff.midiacom.axt.body.interfaces.XTPProperty;
import br.uff.midiacom.axt.body.link.XTPLink;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPContextPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPContext<T extends XTPContext, P extends XTPElement, I extends XTPElementImpl, Ept extends XTPPort, Epp extends XTPProperty, En extends XTPNode, Ei extends XTPInterface, El extends XTPLink, Em extends NCLMeta, Emt extends NCLMetadata>
        extends XTPContextPrototype<T, P, I, Ept, Epp, En, El, Em, Emt> implements XTPNode<En, P, Ei> {

    
    public XTPContext(String id, XTPVocabularyElement xlabel) throws XMLException {
        super(id, xlabel);
    }
    
    
    public XTPContext() throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        NodeList nl;
        
        // set the xLabel (required)
        if(!(att_var = element.getAttribute("xlabel")).isEmpty())
            setXLabel(((XTPVocabulary)impl.getDoc().getVocabulary()).findComponent(att_var));
        else
            throw new XMLException("Could not find xlabel attribute.");
        
        // set the id
        if(!(att_var = element.getAttribute("id")).isEmpty())
                setId(att_var);
        
                
        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the port
                    if(el.getTagName().equals("port")){
                        Ept inst = createPort();
                        addPort(inst);
                        inst.load(el);
                    }
                    // create the media
                    if(el.getTagName().equals("media")){
                        En inst = createMedia();
                        addNode(inst);
                        inst.load(el);
                    }
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("Switch" + aux + " > " + ex.getMessage());
        }
    }
    
    
    public Ei findInterface(String xlabel) throws XMLException {
        Ei result;
        
        // search as a property
        result = (Ei) properties.get(xlabel);
        if(result != null)
            return result;
        
        // search as a port
        result = (Ei) ports.get(xlabel);
        if(result != null)
            return result;
        
        // search in inner nodes
        for(En node : nodes){
            result = (Ei) node.findInterface(xlabel);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    public En findNode(String xlabel) throws XMLException {
        En result;
        
        if(getId().equals(xlabel))
            return (En) this;
        
        for(En node : nodes){
            result = (En) node.findNode(xlabel);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    /**
     * Function to create the child element <i>switchPort</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switchPort</i>.
     */
    protected Ept createPort() throws XMLException {
        return (Ept) new XTPPort();
    }


    /**
     * Function to create the child element <i>media</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>media</i>.
     */
    protected En createMedia() throws XMLException {
        return (En) new XTPMedia();
    }
}
