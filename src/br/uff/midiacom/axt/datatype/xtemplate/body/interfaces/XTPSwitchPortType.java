package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.interfaces.NCLMapping;
import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;


public class XTPSwitchPortType<T extends XTPSwitchPortType, I extends NCLInterface, M extends NCLMapping> extends NCLSwitchPort<I, M> implements XTPElement<T> {


    public XTPSwitchPortType(String id) throws NCLInvalidIdentifierException {
        super(id);
    }


    @Override
    public boolean compare(T other) {
        return getId().equals(other.getId());
    }

}
