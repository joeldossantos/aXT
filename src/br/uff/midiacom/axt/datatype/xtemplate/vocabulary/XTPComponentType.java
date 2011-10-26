package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.axt.datatype.auxiliar.LabeledElementList;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.reference.IdRefType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPComponentType<T extends XTPComponentType, P extends XTPElement, I extends XMLElementImpl, Ed extends NCLLayoutDescriptor, Ep extends XTPComponentPortPrototype> extends XTPVocabularyElement<T, P, I> {

    protected StringType xType;
    protected IdRefType<Ed> descriptor;
    protected LabeledElementList<Ep, T> ports;
    protected LabeledElementList<T, T> components;


    public XTPComponentType(String xlabel) throws XMLException {
        super(xlabel);
        ports = new LabeledElementList<Ep, T>();
        components = new LabeledElementList<T, T>();
    }
    
    
    public void setXType(String xType) throws XMLException{
        this.xType = new StringType(xType);
    }
    
    
    public String getXType() {
        return xType.getValue();
    }
    
    
    public void setDescriptor(IdRefType<Ed> descriptor){
        this.descriptor = descriptor;
    }
    
    
    public IdRefType<Ed> getDescriptor(){
        return descriptor;
    }
    
    
    public boolean addComponentPort(Ep port) throws XMLException {
        return ports.add(port, (T) this);
    }


    public boolean removeComponentPort(Ep port) throws XMLException {
        return ports.remove(port);
    }


    public boolean removeComponentPort(String xlabel) {
        return ports.remove(xlabel);
    }
    
    
    public boolean hasComponentPort(Ep port) throws XMLException {
        return ports.contains(port);
    }
    
    
    public boolean hasComponentPort(String xlabel) {
        return ports.get(xlabel) != null;
    }


    public boolean hasComponentPort() {
        return !ports.isEmpty();
    }
    
    
    public LabeledElementList<Ep, T> getComponentPorts() {
        return ports;
    }


    public boolean addComponent(T component) throws XMLException {
        return components.add(component, (T) this);
    }


    public boolean removeComponent(T component) throws XMLException {
        return components.remove(component);
    }


    public boolean removeComponent(String xlabel) {
        return components.remove(xlabel);
    }


    public boolean hasComponent(T component) throws XMLException {
        return components.contains(component);
    }


    public boolean hasComponent(String xlabel) {
        return components.get(xlabel) != null;
    }


    public boolean hasComponent() {
        return !components.isEmpty();
    }


    public LabeledElementList<T, T> getComponents() {
        return components;
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
