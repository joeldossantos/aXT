package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.XMLIdentifiableElement;


public interface XTPInterface<T extends XTPInterface, P extends XTPElement>
        extends XMLIdentifiableElement<T, P>, XTPLabeledElement<T, P> {
    
}
