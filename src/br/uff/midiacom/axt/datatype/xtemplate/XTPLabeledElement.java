package br.uff.midiacom.axt.datatype.xtemplate;

import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.XMLException;


public interface XTPLabeledElement<T extends XTPLabeledElement, P extends XTPElement> extends XTPElement<T, P> {


    public void setXLabel(XTPVocabularyElement xlabel) throws XMLException;


    public XTPVocabularyElement getXLabel();
}
