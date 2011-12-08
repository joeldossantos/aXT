package br.uff.midiacom.axt.head;

import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.head.XTPHeadPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPHead<T extends XTPHead, P extends XTPElement, I extends XTPElementImpl, Ee extends XTPExtends, Ed extends NCLDescriptorBase, Ec extends NCLConnectorBase>
        extends XTPHeadPrototype<T, P, I, Ee, Ed, Ec> implements XTPElement<T, P> {

    
    public XTPHead() throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        Element el;

        try{
            // create the extends
            el = (Element) element.getElementsByTagName("extends").item(0);
            if(el != null){
                Ee inst = createExtends();
                addExtend(inst);
                inst.load(el);
            }

            // create the descriptorBase
            el = (Element) element.getElementsByTagName("descriptorBase").item(0);
            if(el != null){
                Ed inst = createDescriptorBase();
                setDescriptorBase(inst);
                inst.load(el);
            }

            // create the connectorBase
            el = (Element) element.getElementsByTagName("connectorBase").item(0);
            if(el != null){
                Ec inst = createConnectorBase();
                setConnectorBase(inst);
                inst.load(el);
            }
        }
        catch(XMLException ex){
            throw new XMLException("Head > " + ex.getMessage());
        }
    }
    
    
    /**
     * Function to create the extends element <i>extends</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>extends</i>.
     */
    protected Ee createExtends() throws XMLException {
        return (Ee) new XTPExtends();
    }
    
    
    /**
     * Function to create the child element <i>descriptorBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptorBase</i>.
     */
    protected Ed createDescriptorBase() throws XMLException {
        return (Ed) new NCLDescriptorBase();
    }


    /**
     * Function to create the child element <i>connectorBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>connectorBase</i>.
     */
    protected Ec createConnectorBase() throws XMLException {
        return (Ec) new NCLConnectorBase();
    }
}
