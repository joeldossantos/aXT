package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;


public interface XTPInterface<T extends XTPInterface, P extends XTPElement> extends NCLInterface<T, P>, XTPLabeledElement<T, P> {
    
}
