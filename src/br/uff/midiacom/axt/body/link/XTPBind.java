package br.uff.midiacom.axt.body.link;

import br.uff.midiacom.ana.connector.NCLRole;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.node.XTPNode;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPBindPrototype;
import br.uff.midiacom.axt.vocabulary.XTPConnector;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPBind<T extends XTPBind, P extends XTPElement, I extends XTPElementImpl, Er extends NCLRole, En extends XTPNode, Ei extends XTPInterface, Ed extends NCLLayoutDescriptor, Ep extends XTPParam>
        extends XTPBindPrototype<T, P, I, Er, En, Ei, Ed, Ep> implements XTPElement<T, P> {

    
    public XTPBind() throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        NodeList nl;
        
        // set the role
        if(!(att_var = element.getAttribute("role")).isEmpty()){
                P aux;
                if((aux = (P) getParent()) == null)
                    throw new XMLException("Could not find element " + att_var);

                XTPConnector conn = (XTPConnector) ((XTPLink) aux).getXType();
                if(conn == null)
                    throw new XMLException("Could not find element " + att_var);

                Er rol = (Er) conn.getSrc().findRole(att_var);
                if(rol == null)
                    throw new XMLException("Could not find element " + att_var);
                setRole(rol);
            }
            else
                throw new XMLException("Could not find role attribute.");
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
                setSelect(att_var); // procurar pelo id
        
        
        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the bindParam
                    if(el.getTagName().equals("bindParam")){
                        Ep inst = createBindParam();
                        addBindParam(inst);
                        inst.load(el);
                    }
                }
            }
        }catch(XMLException ex){
            throw new XMLException("Bind > " + ex.getMessage());
        }
    }
    
    
    /**
     * Function to create the child element <i>bindParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindParam</i>.
     */
    protected Ep createBindParam() throws XMLException {
        return (Ep) new XTPParam(NCLParamInstance.BINDPARAM);
    }
}
