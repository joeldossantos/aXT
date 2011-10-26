package br.uff.midiacom.axt;

import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public interface XTPElement<T extends XTPElement, P extends XTPElement> extends br.uff.midiacom.axt.datatype.xtemplate.XTPElement<T, P> {
    
    
    public void load(Element element) throws XMLException;
}

