package br.uff.midiacom.axt.datatype.xtemplate.head;

import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPHeadType<T extends XTPHeadType, P extends XTPElement, I extends XMLElementImpl, Ee extends XTPExtendsType, Ed extends NCLDescriptorBase, Ec extends NCLConnectorBase> extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected ElementList<Ee, T> xtExtends;
    protected Ed descriptorBase;
    protected Ec connectorBase;
    
    
    public XTPHeadType() throws XMLException {
        super();
        xtExtends = new ElementList<Ee, T>();
    }


    public void setDescriptorBase(Ed descriptorBase) {
        //Retira o parentesco do descriptorBase atual
        if(this.descriptorBase != null)
            this.descriptorBase.setParent(null);

        this.descriptorBase = descriptorBase;
        //Se descriptorBase existe, atribui este como seu parente
        if(this.descriptorBase != null)
            this.descriptorBase.setParent(this);
    }


    public Ed getDescriptorBase() {
        return descriptorBase;
    }


    public void setConnectorBase(Ec connectorBase) {
        //Retira o parentesco do connectorBase atual
        if(this.connectorBase != null)
            this.connectorBase.setParent(null);

        this.connectorBase = connectorBase;
        //Se connectorBase existe, atribui este como seu parente
        if(this.connectorBase != null)
            this.connectorBase.setParent(this);
    }


    public Ec getConnectorBase() {
        return connectorBase;
    }
    
    
    public boolean addExtend(Ee xtExtend) throws XMLException {
        return xtExtends.add(xtExtend, (T) this);
    }
    
    
    public boolean removeExtend(Ee extend) throws XMLException {
        return xtExtends.remove(extend);
    }


    public boolean hasExtend(Ee extend) throws XMLException {
        return xtExtends.contains(extend);
    }


    public boolean hasExtend() {
        return !xtExtends.isEmpty();
    }
    
    
    public ElementList<Ee, T> getExtends() {
        return xtExtends;
    }


    @Override
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

    