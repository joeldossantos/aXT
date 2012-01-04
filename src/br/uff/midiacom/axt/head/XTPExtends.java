package br.uff.midiacom.axt.head;

import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.axt.XTPDoc;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.head.XTPExtendsPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import org.w3c.dom.Element;


public class XTPExtends<T extends XTPExtends, P extends XTPElement, I extends XTPElementImpl, Ed extends XTPDoc>
        extends XTPExtendsPrototype<T, P, I, Ed> implements XTPElement<T, P> {

    
    public XTPExtends(SrcType xtemplate) throws XMLException {
        super(xtemplate);
    }
    
    
    public XTPExtends() throws XMLException {
        super();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>(this);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;

        try{
            // set the documentURI (required)
            if(!(att_var = element.getAttribute("documentURI")).isEmpty())
                setXTemplate(new SrcType(att_var));
            else
                throw new XMLException("Could not find documentURI attribute.");

            // set the overwriteConstraints (optional)
            setOverwriteConstraints(!element.getAttribute("overwriteConstraints").isEmpty());
        }
        catch(XMLException ex){
            throw new XMLException("Extends:\n" + ex.getMessage());
        }
    }
}
