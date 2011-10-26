package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLRole;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.link.NCLBind;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPBindType<T extends XTPBindType, P extends XTPElement, I extends XMLElementImpl, Er extends NCLRole, En extends NCLNode, Ei extends NCLInterface, Ed extends NCLLayoutDescriptor, Ep extends XTPParamType> extends NCLBind<T, P, I, Er, En, Ei, Ed, Ep> implements XTPElement<T, P> {

    protected StringType select;


    public XTPBindType() throws XMLException {
        super();
    }


    public void setSelect(String select) throws XMLException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }
}


