package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLRole;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.link.NCLBind;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPNode;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPBindPrototype<T extends XTPBindPrototype, P extends XTPElement, I extends XMLElementImpl, Er extends NCLRole, En extends XTPNode, Ei extends XTPInterface, Ed extends NCLLayoutDescriptor, Ep extends XTPParamPrototype> extends NCLBind<T, P, I, Er, En, Ei, Ed, Ep> implements XTPElement<T, P> {

    protected StringType select;


    public XTPBindPrototype() throws XMLException {
        super();
    }


    public void setSelect(String select) throws XMLException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }
}


