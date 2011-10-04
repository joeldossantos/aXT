package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPSwitchPortType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPSwitchType<T extends XTPSwitchType, N extends NCLNode, P extends XTPSwitchPortType, B extends XTPBindRuleType> extends NCLSwitch<N, T, P, B> implements XTPLabeledElement<T> {

    protected StringType xlabel;
    
    
    public XTPSwitchType(String id, String xlabel) throws NCLInvalidIdentifierException, NullPointerException, IllegalArgumentException {
        super(id);
        setXLabel(xlabel);
    }


    public void setXLabel(String xlabel) throws NullPointerException, IllegalArgumentException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

        this.xlabel = new StringType(xlabel);
    }


    public String getXLabel(){
        return xlabel.getValue();
    }


    @Override
    public boolean compare(T other) {
        return getId().equals(other.getId());
    }
}
