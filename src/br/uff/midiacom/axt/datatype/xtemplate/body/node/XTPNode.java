package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.datatype.ncl.node.NCLNode;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;


public interface XTPNode<T extends XTPNode, P extends XTPElement> extends NCLNode<T, P>, XTPLabeledElement<T, P> {
    
}
