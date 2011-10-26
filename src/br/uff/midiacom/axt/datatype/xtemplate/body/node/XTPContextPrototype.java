package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPContextPrototype<T extends XTPContextPrototype, P extends XTPElement, I extends XMLElementImpl, Ept extends XTPPortPrototype, Epp extends XTPPropertyPrototype, En extends NCLNode, Ei extends NCLInterface, El extends XTPLinkPrototype, Em extends NCLMeta, Emt extends NCLMetadata> extends NCLContext<T, P, I, Ept, Epp, En, Ei, El, Em, Emt> implements XTPLabeledElement<T, P> {

    protected StringType xlabel;
    
    
    public XTPContextPrototype(String id, String xlabel) throws XMLException {
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
