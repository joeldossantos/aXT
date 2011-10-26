package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.interfaces.XTPSwitchPort;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPSwitchPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPSwitch<T extends XTPSwitch, P extends XTPElement, I extends XMLElementImpl, En extends XTPNode, Ei extends XTPInterface, Ep extends XTPSwitchPort, Eb extends XTPBindRule> extends XTPSwitchPrototype<T, P, I, En, Ei, Ep, Eb> implements XTPNode<En, P> {

    
    public XTPSwitch(String id, String xlabel) throws XMLException {
        super(id, xlabel);
    }

//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("switch") && !insideSwitchFlag){
//                cleanWarnings();
//                cleanErrors();
//                insideSwitchFlag = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("xlabel"))
//                        setXLabel(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("refer"))
//                        setRefer((S) new XTPSwitch(attributes.getValue(i)));
//                }
//            }
//            else if(localName.equals("bindRule")){
//                B child = createBindRule();
//                child.startElement(uri, localName, qName, attributes);
//                addBind(child);
//            }
//            else if(localName.equals("defaultComponent")){
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("component"))
//                        setDefaultComponent((N) new NCLContext(attributes.getValue(i)));
//                }
//            }
//            else if(localName.equals("switchPort")){
//                P child = createSwitchPort();
//                child.startElement(uri, localName, qName, attributes);
//                addPort(child);
//            }
//            else if(localName.equals("media")){
//                N child = createMedia();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("context")){
//                N child = createContext();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("switch") && insideSwitchFlag){
//                N child = createSwitch();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    
//@Override
//    public void endDocument() {
//        if(getDefaultComponent() != null)
//            defaultComponentReference();
//        if(getParent() != null && getRefer() != null)
//                switchReference();
//
//        if(hasBind()){
//            Iterable<B> binds = this.getBinds();
//            for(B bind : binds){
//                bind.endDocument();
//                addWarning(bind.getWarnings());
//                addError(bind.getErrors());
//            }
//        }
//        if(hasPort()){
//            Iterable<P> ports = this.getPorts();
//            for(P port : ports){
//                port.endDocument();
//                addWarning(port.getWarnings());
//                addError(port.getErrors());
//            }
//        }
//        if(hasNode()){
//            Iterable<N> nodes = this.getNodes();
//            for(N node : nodes){
//                node.endDocument();
//                addWarning(node.getWarnings());
//                addError(node.getErrors());
//            }
//        }
//    }
//    
//
//
//    private void switchReference() {
//        //Search for the interface inside the node
//        XMLElement body = (XMLElement) getParent();
//
//        while(!(body instanceof XTPBody)){
//            body = (XMLElement) body.getParent();
//            if(body == null){
//                addWarning("Could not find a body");
//                return;
//            }
//        }
//
//        setRefer(findSwitch(((XTPBody) body).getNodes()));
//    }
//
//
//    private S findSwitch(Set<N> nodes) {
//        for(N n : nodes){
//            if(n instanceof XTPContext){
//                if( ((XTPContext) n).hasNode()){
//                    Set<N> cnodes = ((XTPContext) n).getNodes();
//                        S s = findSwitch(cnodes);
//                        if(s != null)
//                            return (S) s;
//                }
//            }
//            else if(n instanceof XTPSwitch){
//                if(n.getId().equals(getRefer().getId()))
//                    return (S) n;
//                else if( ((XTPSwitch) n).hasNode()){
//                    Set<N> snodes = ((XTPSwitch) n).getNodes();
//                    S s = findSwitch(snodes);
//                    if(s != null)
//                        return (S) s;
//                }
//            }
//        }
//
//        addWarning("Could not find switch with id: " + getRefer().getId());
//        return null;
//    }
//
//    
//
//    private void defaultComponentReference() {
//        
//        Iterable<N> nodes = this.getNodes();
//        for(N node : nodes){
//            if(node.getId().equals(getDefaultComponent().getId())){
//                setDefaultComponent(node);
//                return;
//            }
//        }
//        
//        XMLElement root = this.getParent();
//         while(!(root instanceof XTPDoc)){
//            root = root.getParent();
//         }
//         
//        if(((XTPDoc) root).getVocabulary()!=null){
//            
//         
//            Iterable<Cp> components = (((XTPVocabulary)((XTPDoc) root).getVocabulary())).getComponents();
//            if(findDefaultComponent(components)!=null){
//                setDefaultComponent(findDefaultComponent(components));
//                return;
//             }
//
//        }
//
//
//        addWarning("Could not find node in switch with id: " + getDefaultComponent().getId());
//    }
//
//
//    private Cp findDefaultComponent(Iterable<Cp> components){
//
//        for(Cp component : components){
//            if(this.getDefaultComponent().getId().equals(component.getXLabel())){
//                
//                return component;
//            }
//            else if(component.hasComponent()){
//                Iterable<Cp> ccomp = component.getComponents();
//                Cp cp = findDefaultComponent(ccomp);
//                 if(cp != null)
//                        return (Cp) cp;
//            }
//        }
//
//        return null;
//    }
//    public void searchNodes(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules, N nodeS ){
//        Iterable<N> nodes = ((XTPContext)nodeS).getNodes();
//        for(N node : nodes){
//            if(node instanceof XTPMedia)
//                ((XTPMedia)node).searchMedia(descriptors);
//            else if(node instanceof XTPContext)
//                ((XTPContext) node).searchContext(descriptors, connectors, rules);
//             else if (node instanceof XTPSwitch)
//                ((XTPSwitch)node).searchSwitch(descriptors, connectors, rules);
//
//
//    }
//    }
//    public void searchSwitch(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules){
//        if(this.hasNode()){
//            searchNodes(descriptors, connectors,rules, (N) this);
//            }
//
//
//        if(this.hasBind()){
//            Iterable<B> binds = this.getBinds();
//            for(B bind: binds){
//                ((XTPBindRule)bind).searchBindRule(rules);
//            }
//        }
//
//    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
