package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPAreaPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPMediaPrototype<T extends XTPMediaPrototype, P extends XTPElement, I extends XMLElementImpl, Ea extends XTPAreaPrototype, Ep extends XTPPropertyPrototype, Ed extends NCLLayoutDescriptor, En extends NCLNode, Ei extends NCLInterface> extends NCLMedia<T, P, I, Ea, Ep, Ed, En, Ei> implements XTPLabeledElement<T, P> {

    protected StringType xlabel;
    
    
    public XTPMediaPrototype(String id, NCLMimeType type, String xlabel) throws XMLException {
        super(id);
        setType(type);
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
