package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPAreaType;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPMediaType<T extends XTPMediaType, A extends XTPAreaType, P extends XTPPropertyType, N extends NCLNode, D extends NCLLayoutDescriptor> extends NCLMedia<A, P, N, D, T> implements XTPLabeledElement<T> {

    protected StringType xlabel;
    
    
    public XTPMediaType(String id, NCLMimeType type, String xlabel) throws NCLInvalidIdentifierException {
        super(id);
        setType(type);
        setXLabel(xlabel);
    }


    public void setXLabel(String xlabel) throws NullPointerException, IllegalArgumentException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

        this.xlabel = new StringType(xlabel);
    }


    public String getXLabel(){
        return xlabel.getValue();
    }


    @Override
    public boolean compare(T other) {
        return getId().equals(other.getId());
    }
}
