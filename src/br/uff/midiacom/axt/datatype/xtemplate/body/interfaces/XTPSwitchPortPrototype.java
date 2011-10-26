package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.interfaces.NCLMapping;
import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPSwitchPortPrototype<T extends XTPSwitchPortPrototype, P extends XTPElement, I extends XMLElementImpl, Em extends NCLMapping, Ei extends XTPInterface> extends NCLSwitchPort<T, P, I, Em, Ei> implements XTPInterface<Ei, P> {

    protected StringType xlabel;
    

    public XTPSwitchPortPrototype(String id) throws XMLException {
        super(id);
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
