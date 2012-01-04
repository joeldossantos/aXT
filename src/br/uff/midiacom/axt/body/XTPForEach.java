package br.uff.midiacom.axt.body;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.interfaces.XTPArea;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.interfaces.XTPPort;
import br.uff.midiacom.axt.body.interfaces.XTPProperty;
import br.uff.midiacom.axt.body.link.XTPLink;
import br.uff.midiacom.axt.body.node.XTPSwitch;
import br.uff.midiacom.axt.datatype.xtemplate.body.XTPForEachPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPForEach<T extends XTPForEach, P extends XTPElement, I extends XTPElementImpl, Ei extends XTPInterface, Es extends XTPSwitch, El extends XTPLink, Ev extends XTPVariable>
        extends XTPForEachPrototype<T, P, I, Ei, Es, El, Ev> implements XTPElement<T, P> {

    
    public XTPForEach(String select) throws XMLException {
        super(select);
    }
    
    
    public XTPForEach() throws XMLException {
        super();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>(this);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        NodeList nl;
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
                setSelect(att_var);
        
                
        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the port
                    if(el.getTagName().equals("port")){
                        Ei inst = createPort();
                        addPort(inst);
                        inst.load(el);
                    }
                    // create the variable
                    if(el.getTagName().equals("variable")){
                        Ev inst = createVariable();
                        addVariable(inst);
                        inst.load(el);
                    }
                    // create the property
                    if(el.getTagName().equals("property")){
                        Ei inst = createProperty();
                        addProperty(inst);
                        inst.load(el);
                    }
                    // create the area
                    if(el.getTagName().equals("area")){
                        Ei inst = createArea();
                        addArea(inst);
                        inst.load(el);
                    }
                    // create the link
                    if(el.getTagName().equals("link")){
                        El inst = createLink();
                        addLink(inst);
                        inst.load(el);
                    }
                    // create the switch
                    if(el.getTagName().equals("switch")){
                        Es inst = createSwitch();
                        addSwitch(inst);
                        inst.load(el);
                    }
                }
            }
        }catch(XMLException ex){
            throw new XMLException("ForEach > " + ex.getMessage());
        }
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
     * Function to create the child element <i>port</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>port</i>.
     */
    protected Ei createPort() throws XMLException {
        return (Ei) new XTPPort();
    }
    
    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected Ei createProperty() throws XMLException {
        return (Ei) new XTPProperty();
    }
    
    /**
     * Function to create the child element <i>area</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>area</i>.
     */
    protected Ei createArea() throws XMLException {
        return (Ei) new XTPArea();
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
     * Function to create the child element <i>switch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switch</i>.
     */
    protected Es createSwitch() throws XMLException {
        return (Es) new XTPSwitch();
    }
}
