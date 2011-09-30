package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.link.NCLParam;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.string.StringType;


public class XTPParamType<T extends XTPParamType, C extends NCLConnectorParam> extends NCLParam<T, C> implements XTPElement<T> {

    protected StringType select;
    
    
    public XTPParamType(NCLParamInstance paramType) {
        super(paramType);
    }


    public void setSelect(String select) throws NullPointerException, IllegalArgumentException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }


    @Override
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
