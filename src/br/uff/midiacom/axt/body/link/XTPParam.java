package br.uff.midiacom.axt.body.link;

import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPParamPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPParam<T extends XTPParam, P extends XTPElement, I extends XTPElementImpl, Ec extends NCLConnectorParam>
        extends XTPParamPrototype<T, P, I, Ec> implements XTPElement<T, P> {

    
    public XTPParam(NCLParamInstance paramType) throws XMLException{
        super(paramType);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
        // set the name (required)
        if(!(att_var = element.getAttribute("name")).isEmpty()){
            P aux;
            if((aux = (P) getParent()) == null)
                throw new XMLException("Could not find element " + att_var);
            if(paramType.equals(NCLParamInstance.BINDPARAM) && (aux = (P) aux.getParent()) == null)
                throw new XMLException("Could not find element " + att_var);

            Ec par = (Ec) ((XTPLink) aux).getXType().getConnectorParams().get(att_var);
            if(par == null)
                throw new XMLException("Could not find element " + att_var);

            setName(par);
        }
        else
            throw new XMLException("Could not find name attribute.");
        
        // set the value
        if(!(att_var = element.getAttribute("value")).isEmpty())
                setValue(att_var);
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
                setSelect(att_var);
    }
}
