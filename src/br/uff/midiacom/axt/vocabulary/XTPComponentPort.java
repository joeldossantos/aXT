package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPComponentPortPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import org.w3c.dom.Element;


public class XTPComponentPort<T extends XTPComponentPort, P extends XTPElement, I extends XTPElementImpl>
        extends XTPComponentPortPrototype<T, P, I> implements XTPElement<T, P> {

    
    public XTPComponentPort(String xlabel) throws XMLException{
        super(xlabel);
    }

    
    public XTPComponentPort() throws XMLException{
        super();
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
            
            throw new XMLException("ComponentPort" + aux + ":\n" + ex.getMessage());
        }
        
        // set the min
        if(!(att_var = element.getAttribute("minOccurs")).isEmpty())
                setMinOccurs(Integer.parseInt(att_var));
        
        // set the max
        if(!(att_var = element.getAttribute("maxOccurs")).isEmpty())
                setMaxOccurs(new MaxType(att_var));
    }
}
