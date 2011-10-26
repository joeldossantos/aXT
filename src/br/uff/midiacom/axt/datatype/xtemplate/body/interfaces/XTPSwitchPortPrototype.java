package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.interfaces.NCLMapping;
import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPSwitchPortPrototype<T extends XTPSwitchPortPrototype, P extends XTPElement, I extends XMLElementImpl, Em extends NCLMapping, Ei extends NCLInterface> extends NCLSwitchPort<T, P, I, Em, Ei> implements XTPElement<T, P> {


    public XTPSwitchPortPrototype(String id) throws XMLException {
        super(id);
    }
}
