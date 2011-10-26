package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPSwitchPortPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPSwitchPrototype<T extends XTPSwitchPrototype, P extends XTPElement, I extends XMLElementImpl, En extends NCLNode, Ei extends NCLInterface, Ep extends XTPSwitchPortPrototype, Eb extends XTPBindRulePrototype> extends NCLSwitch<T, P, I, En, Ei, Ep, Eb> implements XTPLabeledElement<T, P> {

    protected StringType xlabel;
    
    
    public XTPSwitchPrototype(String id, String xlabel) throws XMLException {
        super(id);
        setXLabel(xlabel);
    }


    public void setXLabel(String xlabel) throws XMLException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

        this.xlabel = new StringType(xlabel);
    }


    public String getXLabel(){
        return xlabel.getValue();
    }
}
