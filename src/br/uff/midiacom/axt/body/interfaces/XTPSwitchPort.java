package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.interfaces.NCLMapping;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPSwitchPortPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPSwitchPort<T extends XTPSwitchPort, P extends XTPElement, I extends XMLElementImpl, Em extends NCLMapping, Ei extends NCLInterface> extends XTPSwitchPortPrototype<T, P, I, Em, Ei> implements XTPElement<T, P> {


    public XTPSwitchPort(String id) throws XMLException {
        super(id);
    }
}
