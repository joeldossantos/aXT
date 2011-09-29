/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT.Switch;


import AXT.Component.XTemplateComponent;
import AXT.Context.XTemplateContext;
import AXT.Media.XTemplateMedia;
import AXT.XMLElement;
import AXT.XTemplateBody;
import AXT.XTemplateDoc;
import AXT.XTemplateVocabulary;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import br.uff.midiacom.ana.node.NCLSwitchBindRule;
import br.uff.midiacom.ana.rule.NCLRule;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author flavia
 */
public class XTemplateSwitch<S extends NCLSwitch, B extends NCLSwitchBindRule, N extends NCLNode, P extends NCLSwitchPort,
        Cp extends XTemplateComponent, D extends NCLLayoutDescriptor, Cn extends NCLCausalConnector, R extends NCLRule> extends NCLSwitch{

    private String xlabel;
    private boolean insideSwitchFlag;
    

    //construtores
    public XTemplateSwitch(){}

    public XTemplateSwitch(String id) throws NCLInvalidIdentifierException{
        super(id);
    }

    public XTemplateSwitch(XMLReader reader, XMLElement parent) {
        super();
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideSwitchFlag = false;
    }

    //metodos de acesso

    public String getXLabel(){
        return this.xlabel;
    }

    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }



    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("switch") && !insideSwitchFlag){
                cleanWarnings();
                cleanErrors();
                insideSwitchFlag = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("xlabel"))
                        setXLabel(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("refer"))
                        setRefer((S) new XTemplateSwitch(attributes.getValue(i)));
                }
            }
            else if(localName.equals("bindRule")){
                B child = createBindRule();
                child.startElement(uri, localName, qName, attributes);
                addBind(child);
            }
            else if(localName.equals("defaultComponent")){
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("component"))
                        setDefaultComponent((N) new NCLContext(attributes.getValue(i)));
                }
            }
            else if(localName.equals("switchPort")){
                P child = createSwitchPort();
                child.startElement(uri, localName, qName, attributes);
                addPort(child);
            }
            else if(localName.equals("media")){
                N child = createMedia();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("context")){
                N child = createContext();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("switch") && insideSwitchFlag){
                N child = createSwitch();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }

    
@Override
    public void endDocument() {
        if(getDefaultComponent() != null)
            defaultComponentReference();
        if(getParent() != null && getRefer() != null)
                switchReference();

        if(hasBind()){
            Iterable<B> binds = this.getBinds();
            for(B bind : binds){
                bind.endDocument();
                addWarning(bind.getWarnings());
                addError(bind.getErrors());
            }
        }
        if(hasPort()){
            Iterable<P> ports = this.getPorts();
            for(P port : ports){
                port.endDocument();
                addWarning(port.getWarnings());
                addError(port.getErrors());
            }
        }
        if(hasNode()){
            Iterable<N> nodes = this.getNodes();
            for(N node : nodes){
                node.endDocument();
                addWarning(node.getWarnings());
                addError(node.getErrors());
            }
        }
    }
    


    private void switchReference() {
        //Search for the interface inside the node
        XMLElement body = (XMLElement) getParent();

        while(!(body instanceof XTemplateBody)){
            body = (XMLElement) body.getParent();
            if(body == null){
                addWarning("Could not find a body");
                return;
            }
        }

        setRefer(findSwitch(((XTemplateBody) body).getNodes()));
    }


    private S findSwitch(Set<N> nodes) {
        for(N n : nodes){
            if(n instanceof XTemplateContext){
                if( ((XTemplateContext) n).hasNode()){
                    Set<N> cnodes = ((XTemplateContext) n).getNodes();
                        S s = findSwitch(cnodes);
                        if(s != null)
                            return (S) s;
                }
            }
            else if(n instanceof XTemplateSwitch){
                if(n.getId().equals(getRefer().getId()))
                    return (S) n;
                else if( ((XTemplateSwitch) n).hasNode()){
                    Set<N> snodes = ((XTemplateSwitch) n).getNodes();
                    S s = findSwitch(snodes);
                    if(s != null)
                        return (S) s;
                }
            }
        }

        addWarning("Could not find switch with id: " + getRefer().getId());
        return null;
    }

    

    private void defaultComponentReference() {
        
        Iterable<N> nodes = this.getNodes();
        for(N node : nodes){
            if(node.getId().equals(getDefaultComponent().getId())){
                setDefaultComponent(node);
                return;
            }
        }
        
        XMLElement root = this.getParent();
         while(!(root instanceof XTemplateDoc)){
            root = root.getParent();
         }
         
        if(((XTemplateDoc) root).getVocabulary()!=null){
            
         
            Iterable<Cp> components = (((XTemplateVocabulary)((XTemplateDoc) root).getVocabulary())).getComponents();
            if(findDefaultComponent(components)!=null){
                setDefaultComponent(findDefaultComponent(components));
                return;
             }

        }


        addWarning("Could not find node in switch with id: " + getDefaultComponent().getId());
    }


    private Cp findDefaultComponent(Iterable<Cp> components){

        for(Cp component : components){
            if(this.getDefaultComponent().getId().equals(component.getXLabel())){
                
                return component;
            }
            else if(component.hasComponent()){
                Iterable<Cp> ccomp = component.getComponents();
                Cp cp = findDefaultComponent(ccomp);
                 if(cp != null)
                        return (Cp) cp;
            }
        }

        return null;
    }
    public void searchNodes(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules, N nodeS ){
        Iterable<N> nodes = ((XTemplateContext)nodeS).getNodes();
        for(N node : nodes){
            if(node instanceof XTemplateMedia)
                ((XTemplateMedia)node).searchMedia(descriptors);
            else if(node instanceof XTemplateContext)
                ((XTemplateContext) node).searchContext(descriptors, connectors, rules);
             else if (node instanceof XTemplateSwitch)
                ((XTemplateSwitch)node).searchSwitch(descriptors, connectors, rules);


    }
    }
    public void searchSwitch(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules){
        if(this.hasNode()){
            searchNodes(descriptors, connectors,rules, (N) this);
            }


        if(this.hasBind()){
            Iterable<B> binds = this.getBinds();
            for(B bind: binds){
                ((XTemplateBindRule)bind).searchBindRule(rules);
            }
        }

    }


    @Override
    protected B createBindRule() {
        return (B) new XTemplateBindRule(getReader(), this);
    }

    @Override
    protected P createSwitchPort() {
        return (P) new NCLSwitchPort(getReader(), this);
    }

    @Override
    protected N createMedia() {
        return (N) new XTemplateMedia(getReader(), this);
    }

    @Override
    protected N createContext() {
        return (N) new XTemplateContext(getReader(), this);
    }

    @Override
    protected N createSwitch() {
        return (N) new NCLSwitch(getReader(), this);
    }

    @Override
    public String parse(int ident) {return null;}

   
    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}

}
