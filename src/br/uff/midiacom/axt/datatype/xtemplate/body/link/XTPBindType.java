package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLRole;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.link.NCLBind;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.string.StringType;


public class XTPBindType<T extends XTPBindType, R extends NCLRole, N extends NCLNode, I extends NCLInterface, D extends NCLLayoutDescriptor, P extends XTPParamType> extends NCLBind<T, R, N, I, D, P> implements XTPElement<T> {

    protected StringType select;


    public XTPBindType() {
        super();
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


