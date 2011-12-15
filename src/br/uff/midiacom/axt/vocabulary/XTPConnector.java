package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.axt.ReferenceManager;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPConnectorPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import org.w3c.dom.Element;


public class XTPConnector<T extends XTPConnector, P extends XTPElement, I extends XTPElementImpl>
        extends XTPConnectorPrototype<T, P, I> implements XTPElement<T, P> {

    
    public XTPConnector(String xlabel, NCLCausalConnector src) throws XMLException {
        super(xlabel, src);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;

        try{
            // set the src (required)
            if(!(att_var = element.getAttribute("src")).isEmpty())
                setSrc(ReferenceManager.getInstance().findConnectorReference(impl.getDoc(), att_var));
            else
                throw new XMLException("Could not find src attribute.");
        }
        catch(XMLException ex){
            String aux = getXLabel();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("Connector" + aux + ":\n" + ex.getMessage());
        }
        
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
            
            throw new XMLException("Connector" + aux + ":\n" + ex.getMessage());
        }
        
        // min
        if(!(att_var = element.getAttribute("minOccurs")).isEmpty())
                setMinOccurs(Integer.parseInt(att_var));
        
        // max
        if(!(att_var = element.getAttribute("maxOccurs")).isEmpty())
                setMaxOccurs(new MaxType(att_var));
    }
}
