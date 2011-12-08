package br.uff.midiacom.axt.constraints;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.constraints.XTPConstraintsPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPConstraints<T extends XTPConstraints, P extends XTPElement, I extends XTPElementImpl, Ec extends XTPConstraint>
        extends XTPConstraintsPrototype<T, P, I, Ec> implements XTPElement<T, P> {

    
    public XTPConstraints() throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        NodeList nl;
                
        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the constraint
                    if(el.getTagName().equals("constraint")){
                        Ec inst = createConstraint();
                        addConstraint(inst);
                        inst.load(el);
                    }
                }
            }
        }catch(XMLException ex){
            throw new XMLException("Constraints > " + ex.getMessage());
        }
    }
    
    /**
     * Function to create the child element <i>area</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>area</i>.
     */
    protected Ec createConstraint() throws XMLException {
        return (Ec) new XTPConstraint();
    }
}
