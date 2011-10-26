package br.uff.midiacom.axt.datatype.xtemplate;

import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElement;


public interface XTPLabeledElement<T extends XMLIdentifiableElement, P extends XTPElement> extends XTPElement<T, P>, XMLIdentifiableElement<T, P> {


    public void setXLabel(String xlabel) throws XMLException;


    public String getXLabel();
}
