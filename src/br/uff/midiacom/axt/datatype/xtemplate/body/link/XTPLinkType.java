package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPConnectorType;
import br.uff.midiacom.xml.reference.IdRefType;


public class XTPLinkType<T extends XTPLinkType, P extends XTPParamType, B extends XTPBindType, C extends NCLCausalConnector> extends NCLLink<T, P, B, C> implements XTPElement<T> {

    protected IdRefType<XTPConnectorType> xtype;
    
    
    public XTPLinkType() {
        super();
    }
    
    
    public void setXType(IdRefType<XTPConnectorType> xtype){
        this.xtype = xtype;
    }
    
    
    public IdRefType<XTPConnectorType> getXType(){
        return xtype;
    }


    @Override
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Deprecated
    @Override
    public void setXconnector(C xconnector) {
        super.setXconnector(xconnector);
    }


    @Deprecated
    @Override
    public C getXconnector() {
        return super.getXconnector();
    }
}
