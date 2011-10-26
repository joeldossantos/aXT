package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.interfaces.NCLArea;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPAreaPrototype<T extends XTPAreaPrototype, P extends XTPElement, I extends XMLElementImpl, Ei extends XTPInterface> extends NCLArea<T, P, I, Ei> implements XTPInterface<Ei, P> {

    protected StringType xlabel;
    
    
    public XTPAreaPrototype(String id, String xlabel) throws XMLException {
        super(id);
        setXLabel(xlabel);
    }


    public void setXLabel(String xlabel) throws XMLException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

        this.xlabel = new StringType(xlabel);
    }


    public String getXLabel(){
        return xlabel.getValue();
    }
}
