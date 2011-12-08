package br.uff.midiacom.axt.body;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.link.XTPLink;
import br.uff.midiacom.axt.body.node.XTPSwitch;
import br.uff.midiacom.axt.datatype.xtemplate.body.XTPForEachPrototype;
import br.uff.midiacom.xml.XMLException;
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
                        addInterface(inst);
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
                    // create the property
                    if(el.getTagName().equals("property")){
                        Ei inst = createProperty();
                        addProperty(inst);
                        inst.load(el);
                    }
                }
            }
        }catch(XMLException ex){
            throw new XMLException("ForEach > " + ex.getMessage());
        }
    }
}
