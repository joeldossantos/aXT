package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.axt.datatype.auxiliar.LabeledElementList;
import br.uff.midiacom.xml.datatype.reference.IdRefType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPComponentType<T extends XTPComponentType, D extends NCLDescriptor, P extends XTPComponentPortType> extends XTPVocabularyElement<T> {

    protected StringType xType;
    protected IdRefType<D> descriptor;
    protected LabeledElementList<P> ports;
    protected LabeledElementList<T> components;


    public XTPComponentType(String xlabel) throws NullPointerException, IllegalArgumentException {
        super(xlabel);
        ports = new LabeledElementList<P>();
        components = new LabeledElementList<T>();
    }
    
    
    public void setXType(String xType){
        this.xType = new StringType(xType);
    }
    
    
    public String getXType() {
        return xType.getValue();
    }
    
    
    public void setDescriptor(IdRefType<D> descriptor){
        this.descriptor = descriptor;
    }
    
    
    public IdRefType<D> getDescriptor(){
        return descriptor;
    }
    
    
    public boolean addComponentPort(P port) {
        //If the port was inserted, set its parent
        if(ports.add(port)){
            port.setParent(this);
            return true;
        }
        return false;
    }


    public boolean removeComponentPort(P port) {
        //If the port was removed, remove its parent
        if(ports.remove(port)){
            port.setParent(null);
            return true;
        }
        return false;
    }


    public boolean removeComponentPort(String xlabel) {
        return removeComponentPort(ports.get(xlabel));
    }
    
    
    public boolean hasComponentPort(P port) {
        return ports.contains(port);
    }
    
    
    public boolean hasComponentPort(String xlabel) {
        return ports.get(xlabel) != null;
    }


    public boolean hasComponentPort() {
        return !ports.isEmpty();
    }
    
    
    public LabeledElementList<P> getComponentPorts() {
        return ports;
    }


    public boolean addComponent(T component) {
        //If the component was inserted, set its parent
        if(components.add(component)){
            component.setParent(this);
            return true;
        }
        return false;
    }


    public boolean removeComponent(T component) {
        //If the component was removed, remove its parent
        if(components.remove(component)){
            component.setParent(null);
            return true;
        }
        return false;
    }


    public boolean removeComponent(String xlabel) {
        return removeComponent(components.get(xlabel));
    }


    public boolean hasComponent(T component) {
        return components.contains(component);
    }


    public boolean hasComponent(String xlabel) {
        return components.get(xlabel) != null;
    }


    public boolean hasComponent() {
        return !components.isEmpty();
    }


    public LabeledElementList<T> getComponents() {
        return components;
    }
}
