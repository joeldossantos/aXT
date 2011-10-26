package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPConnectorType;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.reference.IdRefType;


public class XTPLinkType<T extends XTPLinkType, P extends XTPElement, I extends XMLElementImpl, Ep extends XTPParamType, Eb extends XTPBindType, Ec extends NCLCausalConnector, Exc extends XTPConnectorType> extends NCLLink<T, P, I, Ep, Eb, Ec> implements XTPElement<T, P> {

    protected IdRefType<Exc> xtype;
    
    
    public XTPLinkType() throws XMLException {
        super();
    }
    
    
    public void setXType(IdRefType<Exc> xtype){
        this.xtype = xtype;
    }
    
    
    public IdRefType<Exc> getXType(){
        return xtype;
    }


    @Deprecated
    @Override
    public void setXconnector(Ec xconnector) {
        super.setXconnector(xconnector);
    }


    @Deprecated
    @Override
    public Ec getXconnector() {
        return super.getXconnector();
    }
}
