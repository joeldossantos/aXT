package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.axt.ReferenceManager;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.interfaces.XTPArea;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.interfaces.XTPProperty;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPMediaPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPMedia<T extends XTPMedia, P extends XTPElement, I extends XTPElementImpl, Ea extends XTPArea, Ep extends XTPProperty, Ed extends NCLLayoutDescriptor, En extends XTPNode, Ei extends XTPInterface>
        extends XTPMediaPrototype<T, P, I, Ea, Ep, Ed, En> implements XTPNode<En, P, Ei> {

    
    public XTPMedia(String id, NCLMimeType type, XTPVocabularyElement xlabel) throws XMLException {
        super(id, type, xlabel);
    }
    
    
    public XTPMedia() throws XMLException {
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
        
        // set the type
        if(!(att_var = element.getAttribute("type")).isEmpty())
            setType(NCLMimeType.getEnumType(att_var));
        
        // set the descriptor
        if(!(att_var = element.getAttribute("descriptor")).isEmpty()){
            Ed desc = (Ed) ReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), att_var);
            setDescriptor(desc);
        }
        
        // set the src
        if(!(att_var = element.getAttribute("src")).isEmpty())
            setSrc(new SrcType(att_var));
        
                
        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the area
                    if(el.getTagName().equals("area")){
                        Ea inst = createArea();
                        addArea(inst);
                        inst.load(el);
                    }
                    // create the property
                    if(el.getTagName().equals("property")){
                        Ep inst = createProperty();
                        addProperty(inst);
                        inst.load(el);
                    }
                }
            }
        }catch(XMLException ex){
            throw new XMLException("Media > " + ex.getMessage());
        }
    }
    
    
    public Ei findInterface(String xlabel) throws XMLException {
        Ei result;
        
        // search as a property
        result = (Ei) properties.get(xlabel);
        if(result != null)
            return result;
        
        // search as an area
        result = (Ei) areas.get(xlabel);
        if(result != null)
            return result;
        
        return null;
    }
    
    
    public En findNode(String xlabel) {
        if(getId().equals(xlabel))
            return (En) this;
        else
            return null;
    }
    
    
    /**
     * Function to create the child element <i>area</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>area</i>.
     */
    protected Ea createArea() throws XMLException {
        return (Ea) new XTPArea();
    }


    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected Ep createProperty() throws XMLException {
        return (Ep) new XTPProperty();
    }
}
