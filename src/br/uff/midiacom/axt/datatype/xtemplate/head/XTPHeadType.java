package br.uff.midiacom.axt.datatype.xtemplate.head;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElement;
import br.uff.midiacom.xml.elementList.ElementList;


public class XTPHeadType<T extends XTPHeadType, E extends XTPExtendsType, D extends XTPDescriptorBaseType, C extends XTPConnectorBaseType> extends XMLElement<T> implements XTPElement<T> {

    protected ElementList<E> xtExtends;
    protected D descriptorBase;
    protected C connectorBase;
    
    
    public XTPHeadType() {
        xtExtends = new ElementList<E>();
    }


    public void setDescriptorBase(D descriptorBase) {
        //Retira o parentesco do descriptorBase atual
        if(this.descriptorBase != null)
            this.descriptorBase.setParent(null);

        this.descriptorBase = descriptorBase;
        //Se descriptorBase existe, atribui este como seu parente
        if(this.descriptorBase != null)
            this.descriptorBase.setParent(this);
    }


    public D getDescriptorBase() {
        return descriptorBase;
    }


    public void setConnectorBase(C connectorBase) {
        //Retira o parentesco do connectorBase atual
        if(this.connectorBase != null)
            this.connectorBase.setParent(null);

        this.connectorBase = connectorBase;
        //Se connectorBase existe, atribui este como seu parente
        if(this.connectorBase != null)
            this.connectorBase.setParent(this);
    }


    public C getConnectorBase() {
        return connectorBase;
    }
    
    
    public boolean addExtend(E xtExtend) {
        //If the extends was inserted, set its parent
        if(xtExtends.add(xtExtend)){
            xtExtend.setParent(this);
            return true;
        }
        return false;
    }
    
    
    public boolean removeExtend(E extend) {
        //If the extends was removed, remove its parent
        if(xtExtends.remove(extend)){
            extend.setParent(null);
            return true;
        }
        return false;
    }


    public boolean hasExtend(E extend) {
        return xtExtends.contains(extend);
    }


    public boolean hasExtend() {
        return !xtExtends.isEmpty();
    }
    
    
    public ElementList<E> getExtends() {
        return xtExtends;
    }


    @Override
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

    