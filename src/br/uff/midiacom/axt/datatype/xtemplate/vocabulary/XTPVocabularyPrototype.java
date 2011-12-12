package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.axt.datatype.auxiliar.VocabularyElementList;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;


public class XTPVocabularyPrototype<T extends XTPVocabularyPrototype, P extends XTPElement, I extends XTPElementImpl, Ecp extends XTPComponentPrototype, Ecc extends XTPConnectorPrototype>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected VocabularyElementList<Ecp, T> components;
    protected VocabularyElementList<Ecc, T> connectors;
    
    
    public XTPVocabularyPrototype() throws XMLException {
        super();
        components = new VocabularyElementList<Ecp, T>();
        connectors = new VocabularyElementList<Ecc, T>();
    }


    public boolean addComponent(Ecp component) throws XMLException {
        return components.add(component, (T) this);
    }


    public boolean removeComponent(Ecp component) throws XMLException {
        return components.remove(component);
    }


    public boolean removeComponent(String xlabel) {
        return components.remove(xlabel);
    }


    public boolean hasComponent(Ecp component) throws XMLException {
        return components.contains(component);
    }


    public boolean hasComponent(String xlabel) {
        return components.get(xlabel) != null;
    }


    public boolean hasComponent() {
        return !components.isEmpty();
    }


    public VocabularyElementList<Ecp, T> getComponents() {
        return components;
    }


    public boolean addConnector(Ecc connector) throws XMLException {
        return connectors.add(connector, (T) this);
    }


    public boolean removeConnector(Ecc connector) throws XMLException {
        return connectors.remove(connector);
    }


    public boolean removeConnector(String xlabel) {
        return connectors.remove(xlabel);
    }


    public boolean hasConnector(Ecc connector) throws XMLException {
        return connectors.contains(connector);
    }


    public boolean hasConnector(String xlabel) {
        return connectors.get(xlabel) != null;
    }


    public boolean hasConnector() {
        return !connectors.isEmpty();
    }


    public VocabularyElementList<Ecc, T> getConnectors() {
        return connectors;
    }
    
    
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
