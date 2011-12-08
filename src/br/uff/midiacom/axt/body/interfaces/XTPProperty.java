package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPProperty<T extends XTPProperty, P extends XTPElement, I extends XTPElementImpl, Ei extends XTPInterface>
        extends XTPPropertyPrototype<T, P, I, Ei> implements XTPInterface<Ei, P> {

    
    public XTPProperty(NCLSystemVariable name, String xlabel) throws XMLException {
        super(name, xlabel);
    }
    
    
    public XTPProperty() throws XMLException {
        super();
    }
    
    
    public XTPProperty(String name, String xlabel) throws XMLException {
        super(name, xlabel);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
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
            
            throw new XMLException("Property" + aux + ":\n" + ex.getMessage());
        }
        
        // set the name
        if(!(att_var = element.getAttribute("name")).isEmpty())
                setName(att_var);
        
        // set the type
        if(!(att_var = element.getAttribute("value")).isEmpty())
            setValue(att_var);
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
            setSelect(att_var);
    }
}
