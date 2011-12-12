package br.uff.midiacom.axt.body;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.interfaces.XTPPort;
import br.uff.midiacom.axt.body.link.XTPLink;
import br.uff.midiacom.axt.body.node.XTPContext;
import br.uff.midiacom.axt.body.node.XTPMedia;
import br.uff.midiacom.axt.body.node.XTPNode;
import br.uff.midiacom.axt.body.node.XTPSwitch;
import br.uff.midiacom.axt.datatype.xtemplate.body.XTPBodyPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPBody<T extends XTPBody, P extends XTPElement, I extends XTPElementImpl, Ep extends XTPPort, En extends XTPNode, El extends XTPLink, Ev extends XTPVariable, Ef extends XTPForEach>
        extends XTPBodyPrototype<T, P, I, Ep, En, El, Ev, Ef> implements XTPElement<T, P> {

    
    public XTPBody () throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        NodeList nl;

        try{
            // create the child nodes (except ports and links)
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

            // create the child nodes (ports and links)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    //create the port
                    if(el.getTagName().equals("port")){
                        Ep inst = createPort();
                        addPort(inst);
                        inst.load(el);
                    }
                    // create the link
                    if(el.getTagName().equals("link")){
                        El inst = createLink();
                        addLink(inst);
                        inst.load(el);
                    }
                    // create the variable
                    if(el.getTagName().equals("variable")){
                        Ev inst = createVariable();
                        addVariable(inst);
                        inst.load(el);
                    }
                    // create the for each
                    if(el.getTagName().equals("forEach")){
                        Ef inst = createForEach();
                        addForEach(inst);
                        inst.load(el);
                    }
                }
            }
        }
        catch(XMLException ex){
            throw new XMLException("Body > " + ex.getMessage());
        }
    }
    
    
    /**
     * Searches for an node inside the body and its descendants. The node will be
     * searched inside contexts and switches.
     * 
     * @param id
     *          id of the node to be found.
     * @return 
     *          node or null if no node was found.
     */
    public Ep findPort(String id) throws XMLException {
        for(Ep p : ports){
            if(p.getComponent().getId().equals(id))
                return p;
        }
        return null;
    }
    
    
    public En findNode(String id) throws XMLException {
        En result;
        
        // search in the body
        for(En n : nodes){
            if(n.getId().equals(id))
                return n;
        }
        
        // search in inner nodes
        for(En node : nodes){
            result = (En) node.findNode(id);
            if(result != null)
                return result;
        }
        
        return null;
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
    
    
    /**
     * Function to create the child element <i>port</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>port</i>.
     */
    protected Ep createPort() throws XMLException {
        return (Ep) new XTPPort();
    }
    
    
    /**
     * Function to create the child element <i>link</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>link</i>.
     */
    protected El createLink() throws XMLException {
        return (El) new XTPLink();
    }
    
    
    /**
     * Function to create the child element <i>variable</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>variable</i>.
     */
    protected Ev createVariable() throws XMLException {
        return (Ev) new XTPVariable();
    }
    
    
    /**
     * Function to create the child element <i>forEach</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>forEach</i>.
     */
    protected Ef createForEach() throws XMLException {
        return (Ef) new XTPForEach();
    }
}
