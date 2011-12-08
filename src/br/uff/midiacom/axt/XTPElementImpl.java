package br.uff.midiacom.axt;

import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElement;


public class XTPElementImpl<T extends XMLIdentifiableElement, P extends XTPElement>
        extends br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl<T, P> {

    private XTPElement owner;


    public XTPElementImpl(XTPElement owner) throws XMLException {
        if(owner == null)
            throw new XMLException("Null owner element.");

        this.owner = owner;
    }
    
    
    /**
     * Walk through the element parents until it finds the document root element.
     * 
     * @return 
     *          element representing the document root.
     */
    public XTPDoc getDoc() throws XMLException {
        XTPElement doc = getParent();

        while(!(doc instanceof XTPDoc)){
            doc = (XTPElement) doc.getParent();
            if(doc == null){
                throw new XMLException("Could not find document root element.");
            }
        }
        
        return (XTPDoc) doc;
    }
}
