package br.uff.midiacom.axt.body.link;

import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkPrototype;
import br.uff.midiacom.axt.vocabulary.XTPConnector;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPLink<T extends XTPLink, P extends XTPElement, I extends XTPElementImpl, Ep extends XTPParam, Eb extends XTPBind, Ec extends NCLCausalConnector, Exc extends XTPConnector>
        extends XTPLinkPrototype<T, P, I, Ep, Eb, Ec, Exc> implements XTPElement<T, P> {

    
    public XTPLink() throws XMLException {
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
        
        // set the id
        if(!(att_var = element.getAttribute("id")).isEmpty())
                setId(att_var);
        
        try{
            // set the xType (required)
            if(!(att_var = element.getAttribute("xtype")).isEmpty())
                setXType((Exc)((XTPVocabulary)impl.getDoc().getVocabulary()).findConnector(att_var));
            else
                throw new XMLException("Could not find xtype attribute.");
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("Link" + aux + ":\n" + ex.getMessage());
        }
        
        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the linkParam
                    if(el.getTagName().equals("area")){
                        Ep inst = createLinkParam();
                        addLinkParam(inst);
                        inst.load(el);
                    }
                    // create the bind
                    if(el.getTagName().equals("property")){
                        Eb inst = createBind();
                        addBind(inst);
                        inst.load(el);
                    }
                }
            }
        }catch(XMLException ex){
            throw new XMLException("Link > " + ex.getMessage());
        }
    }
    
    
    /**
     * Function to create the child element <i>linkParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>linkParam</i>.
     */
    protected Ep createLinkParam() throws XMLException {
        return (Ep) new XTPParam(NCLParamInstance.LINKPARAM);
    }


    /**
     * Function to create the child element <i>property</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>property</i>.
     */
    protected Eb createBind() throws XMLException {
        return (Eb) new XTPBind();
    }
}
