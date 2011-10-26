package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.link.NCLParam;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPParamPrototype<T extends XTPParamPrototype, P extends XTPElement, I extends XMLElementImpl, Ec extends NCLConnectorParam> extends NCLParam<T, P, I, Ec> implements XTPElement<T, P> {

    protected StringType select;
    
    
    public XTPParamPrototype(NCLParamInstance paramType) throws XMLException {
        super(paramType);
    }


    public void setSelect(String select) throws XMLException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }
}
