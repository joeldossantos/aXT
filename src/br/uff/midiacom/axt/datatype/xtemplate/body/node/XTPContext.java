package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortType;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyType;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPContext<T extends XTPContext, Pt extends XTPPortType, Pp extends XTPPropertyType, N extends NCLNode, L extends XTPLinkType, M extends NCLMeta, MT extends NCLMetadata> extends NCLContext<T, Pt, Pp, N, L, M, MT> implements XTPLabeledElement<T> {

    protected StringType xlabel;
    
    
    public XTPContext(String id, String xlabel) throws NCLInvalidIdentifierException {
        super(id);
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
