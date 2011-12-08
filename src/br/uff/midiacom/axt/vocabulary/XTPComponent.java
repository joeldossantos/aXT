package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.axt.ReferenceManager;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPComponentPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class XTPComponent<T extends XTPComponent, P extends XTPElement, I extends XTPElementImpl, Ed extends NCLLayoutDescriptor, Ep extends XTPComponentPort>
        extends XTPComponentPrototype<T, P, I, Ed, Ep> implements XTPElement<T, P> {

    
    public XTPComponent(String xlabel) throws XMLException {
        super(xlabel);
    }
    
    
    public XTPComponent() throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        NodeList nl;
        
        try{
            // set the xLabel (required)
            if(!(att_var = element.getAttribute("xlabel")).isEmpty())
                setXLabel(att_var);
            else
                throw new XMLException("Could not find xlabel attribute.");
        }
        catch(XMLException ex){
            String aux = getXLabel();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("Component" + aux + ":\n" + ex.getMessage());
        }
        
        // set the type
        if(!(att_var = element.getAttribute("type")).isEmpty())
                setXType(att_var);
        
        // set the descriptor
        if(!(att_var = element.getAttribute("descriptor")).isEmpty()){
            Ed desc = (Ed) ReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), att_var);
            setDescriptor(desc);
        }
        
        // set the min
        if(!(att_var = element.getAttribute("minOccurs")).isEmpty())
                setMinOccurs(Integer.parseInt(att_var));
        
        // set the max
        if(!(att_var = element.getAttribute("maxOccurs")).isEmpty())
                setMaxOccurs(new MaxType(att_var));
        
        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the port
                    if(el.getTagName().equals("port")){
                        Ep inst = createComponentPort();
                        addComponentPort(inst);
                        inst.load(el);
                    }
                    // create the component
                    if(el.getTagName().equals("component")){
                        T inst = createComponent();
                        addComponent(inst);
                        inst.load(el);
                    }
                }
            }
        }catch(XMLException ex){
            throw new XMLException("Component > " + ex.getMessage());
        }
    }
    
    /**
     * Function to create the child element <i>port</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>port</i>.
     */
    protected Ep createComponentPort() throws XMLException {
        return (Ep) new XTPComponentPort();
    }
    
    /**
     * Function to create the child element <i>port</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>port</i>.
     */
    protected T createComponent() throws XMLException {
        return (T) new XTPComponent();
    }
    
    /**
     * Searches for a component inside the component.
     * 
     * @param xLabel
     *          xLabel of the component to be found.
     * @return 
     *          component or null if no component was found.
     */
    public T findComponent(String xLabel) throws XMLException {
        T result;
        
        // search in the component
        result = components.get(xLabel);
        if(result != null)
            return result;
        
        // search in inner components
        for(T c : components){
            result = (T) c.findComponent(xLabel);
            if(result != null)
                return result;
        }
        return null;
    }
    
    /**
     * Searches for a componentPort inside the component.
     * 
     * @param id
     *          id of the componentPort to be found.
     * @return 
     *          componentPort or null if no componentPort was found.
     */
    public Ep findComponentPort(String id) throws XMLException {
        Ep result;
        
        // search in the component
        result = ports.get(id);
        if(result != null)
            return result;
        
        // search in inner components
        for(T c : components){
            result = (Ep) c.findComponentPort(id);
            if(result != null)
                return result;
        }
        return null;
    }
}
