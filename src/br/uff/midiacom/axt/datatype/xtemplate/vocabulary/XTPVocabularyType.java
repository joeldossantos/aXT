package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.axt.datatype.auxiliar.LabeledElementList;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.Element;


public class XTPVocabularyType<T extends XTPVocabularyType, Cp extends XTPComponentType, Cc extends XTPConnectorType> extends Element<T> implements XTPElement<T> {

    protected LabeledElementList<Cp> components;
    protected LabeledElementList<Cc> connectors;
    
    
    public XTPVocabularyType() {
        components = new LabeledElementList<Cp>();
        connectors = new LabeledElementList<Cc>();
    }


    public boolean addComponent(Cp component) {
        //If the component was inserted, set its parent
        if(components.add(component)){
            component.setParent(this);
            return true;
        }
        return false;
    }


    public boolean removeComponent(Cp component) {
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


    public boolean hasComponent(Cp component) {
        return components.contains(component);
    }


    public boolean hasComponent(String xlabel) {
        return components.get(xlabel) != null;
    }


    public boolean hasComponent() {
        return !components.isEmpty();
    }


    public LabeledElementList<Cp> getComponents() {
        return components;
    }


    public boolean addConnector(Cc connector) {
        //If the connector was inserted, set its parent
        if(connectors.add(connector)){
            connector.setParent(this);
            return true;
        }
        return false;
    }


    public boolean removeConnector(Cc connector) {
        //If the connector was removed, remove its parent
        if(connectors.remove(connector)){
            connector.setParent(null);
            return true;
        }
        return false;
    }


    public boolean removeConnector(String xlabel) {
        return removeConnector(connectors.get(xlabel));
    }


    public boolean hasConnector(Cc connector) {
        return connectors.contains(connector);
    }


    public boolean hasConnector(String xlabel) {
        return connectors.get(xlabel) != null;
    }


    public boolean hasConnector() {
        return !connectors.isEmpty();
    }


    public LabeledElementList<Cc> getConnectors() {
        return connectors;
    }


    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
