package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.axt.XTPElement;


public interface XTPInterface<T extends XTPInterface, P extends XTPElement>
        extends XTPElement<T, P>, br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPInterface<T, P> {
    
}
