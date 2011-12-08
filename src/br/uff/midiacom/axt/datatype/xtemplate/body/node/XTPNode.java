package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.XMLIdentifiableElement;


public interface XTPNode<T extends XTPNode, P extends XTPElement>
        extends XMLIdentifiableElement<T, P>, XTPLabeledElement<T, P> {
    
}
