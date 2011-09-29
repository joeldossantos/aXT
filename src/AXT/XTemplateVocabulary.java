/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT;

import AXT.Component.XTemplateComponent;
import AXT.Connector.XTemplateConnector;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 *
 * @author Flavia
 */
public class XTemplateVocabulary<Cp extends XTemplateComponent, Conn extends XTemplateConnector,
        D extends NCLLayoutDescriptor, C extends NCLCausalConnector> extends XTemplateElement {

    private List<Cp> components = new ArrayList<Cp>();
    private List<Conn> xtconnectors = new ArrayList<Conn>();

    public XTemplateVocabulary(){}

     public XTemplateVocabulary(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }

    public boolean addComponent(Cp component) {
        if(components.add(component)){

            if(component != null)
                component.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeComponent(Cp component) {
        if(components.remove(component)){

            if(component != null)
                component.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasComponent(Cp component) {
        return components.contains(component);
    }

    public Cp findComponent(String xlabel){
        for(Cp comp : components){
            if(comp.getXLabel().equals(xlabel)){
                return comp;
            }
        }
        return null;
    }
   

    public boolean hasComponent() {
        return !components.isEmpty();
    }


    public List<Cp> getComponents() {
        return components;
    }

    public boolean addConnector(Conn connector) {
        if(xtconnectors.add(connector)){

            if(connector != null)
                connector.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeConnector(Conn connector) {
        if(xtconnectors.remove(connector)){

            if(connector != null)
                connector.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasConnector(Conn connector) {
        return xtconnectors.contains(connector);
    }


    public boolean hasConnector() {
        return !xtconnectors.isEmpty();
    }


    public Iterable<Conn> getConnectors() {
        return xtconnectors;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("vocabulary")){
            System.out.println("entrou no start element d vocabulary");
            cleanWarnings();
            cleanErrors();
        }
        else if(localName.equals("component")){
                System.out.println("achou o component");
                Cp child = createComponent();
                child.startElement(uri, localName, qName, attributes);
                addComponent(child);
        }
        else if(localName.equals("connector")){
                Conn child = createConnector();
                child.startElement(uri, localName, qName, attributes);
                addConnector(child);
        }
        }

        catch(/*NCLInvalidIdentifierException*/Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public void endDocument() {
            System.out.println("entrou no endDocument do vocabulary");
            if(hasComponent()){
            for(Cp component : components){
                component.endDocument();
                addWarning(component.getWarnings());
                addError(component.getErrors());
            }
        }
        if(hasConnector()){
            for(Conn xtconnector : xtconnectors){
                xtconnector.endDocument();
                addWarning(xtconnector.getWarnings());
                addError(xtconnector.getErrors());
            }


    }
    }

    public void searchForExternalReferences(Iterable<D> descriptors, Iterable<C> connectors){
        for(Cp component : components){
            component.searchDescriptor(descriptors);
        }
        for(Conn connector : xtconnectors ){
            connector.searchConnector(connectors);
        }
    }

    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     protected Cp createComponent() {
        return (Cp) new XTemplateComponent(getReader(), this);
    }

    protected Conn createConnector() {
        return (Conn) new XTemplateConnector(getReader(), this);
    }
    
}
