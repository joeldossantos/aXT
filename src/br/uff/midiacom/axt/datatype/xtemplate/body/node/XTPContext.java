package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortType;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyType;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkType;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPContext<T extends XTPContext, P extends XTPElement, I extends XMLElementImpl, Ept extends XTPPortType, Epp extends XTPPropertyType, En extends NCLNode, Ei extends NCLInterface, El extends XTPLinkType, Em extends NCLMeta, Emt extends NCLMetadata> extends NCLContext<T, P, I, Ept, Epp, En, Ei, El, Em, Emt> implements XTPLabeledElement<T, P> {

    protected StringType xlabel;
    
    
    public XTPContext(String id, String xlabel) throws XMLException {
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
