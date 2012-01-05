package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.interfaces.XTPSwitchPort;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPSwitchPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPSwitch<T extends XTPSwitch, P extends XTPElement, I extends XTPElementImpl, En extends XTPNode, Ei extends XTPInterface, Ep extends XTPSwitchPort, Eb extends XTPBindRule>
        extends XTPSwitchPrototype<T, P, I, En, Ep, Eb> implements XTPNode<En, P, Ei> {

    
    public XTPSwitch(String id, XTPVocabularyElement xlabel) throws XMLException {
        super(id, xlabel);
    }
    
    public XTPSwitch() throws XMLException {
        super();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<T, P>(this);
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
            // create the child nodes (except ports and binds)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    // create the media
                    if(el.getTagName().equals("media")){
                        En inst = createMedia();
                        addNode(inst);
                        inst.load(el);
                    }
                    // create the context
                    if(el.getTagName().equals("context")){
                        En inst = createContext();
                        addNode(inst);
                        inst.load(el);
                    }
                    // create the switch
                    if(el.getTagName().equals("switch")){
                        En inst = createSwitch();
                        addNode(inst);
                        inst.load(el);
                    }
                }
            }

            // create the child nodes (ports, binds and defaultComponent)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    //create the switchPort
                    if(el.getTagName().equals("switchPort")){
                        Ep inst = createSwitchPort();
                        addPort(inst);
                        inst.load(el);
                    }
                    // create the bindRule
                    if(el.getTagName().equals("bindRule")){
                        Eb inst = createBindRule();
                        addBind(inst);
                        inst.load(el);
                    }
                    // create the defaultComponent
                    if(el.getTagName().equals("defaultComponent")){
                        if(!(att_var = el.getAttribute("component")).isEmpty())
                            setDefaultComponent(nodes.get(att_var));
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
        
        // search as a switchPort
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
     * Function to create the child element <i>bindRule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindRule</i>.
     */
    protected Eb createBindRule() throws XMLException {
        return (Eb) new XTPBindRule();
    }


    /**
     * Function to create the child element <i>switchPort</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switchPort</i>.
     */
    protected Ep createSwitchPort() throws XMLException {
        return (Ep) new XTPSwitchPort();
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


    /**
     * Function to create the child element <i>context</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>context</i>.
     */
    protected En createContext() throws XMLException {
        return (En) new XTPContext();
    }


    /**
     * Function to create the child element <i>switch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switch</i>.
     */
    protected En createSwitch() throws XMLException {
        return (En) new XTPSwitch();
    }
}
