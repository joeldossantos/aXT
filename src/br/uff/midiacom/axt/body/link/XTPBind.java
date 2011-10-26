package br.uff.midiacom.axt.body.link;

import br.uff.midiacom.ana.connector.NCLRole;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.node.XTPNode;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPBindPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPBind<T extends XTPBind, P extends XTPElement, I extends XMLElementImpl, Er extends NCLRole, En extends XTPNode, Ei extends XTPInterface, Ed extends NCLLayoutDescriptor, Ep extends XTPParam> extends XTPBindPrototype<T, P, I, Er, En, Ei, Ed, Ep> implements XTPElement<T, P> {

    
    public XTPBind() throws XMLException {
        super();
    }

//    public String getSelectedComponentXLabel(String select){
//                    String pxlabel;
//                    if(select.contains("@xlabel")){
//                        pxlabel = select.substring(select.indexOf('@')+1);
//
//
//                        String[] split =  pxlabel.split("'", 3);
//                        pxlabel = split[1];
//
//                        return pxlabel;
//                    }
//                    else{
//                    return pxlabel = "null";
//                    }
//
//
//                }
//     public String getSelectedInterfaceXLabel(String select){
//            String pxlabel;
//                    if(select.contains("@xlabel")){
//                        pxlabel = select.substring(select.indexOf('@')+1);
//                        if(pxlabel.contains("@xlabel")){
//                            pxlabel = pxlabel.substring(pxlabel.indexOf('@'));
//                            String[] split =  pxlabel.split("'", 3);
//                            pxlabel = split[1];
//                            return pxlabel;
//                        }
//                    }
//                    return null;
//    }
//    private void SelectedComponentReference(){
//        XMLElement root = getParent();
//        while(!(root instanceof XTPDoc)){
//            root = root.getParent();
//        }
//
//        if(this.getSelectedInterfaceXLabel(select)!=null){
//            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedInterfaceXLabel(select));
//            if(component!=null){
//                setSelectedElement(component);
//                return;
//            }
//        }
//        if(this.getSelectedComponentXLabel(select)!=null){
//            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedComponentXLabel(select));
//            if(component!=null){
//                setSelectedElement(component);
//                return;
//            }
//        }
//
//        addWarning("Could not find selected Element with xlabel"+getSelectedComponentXLabel(select)+"with interface"+getSelectedInterfaceXLabel(select));
//    }
//    @Override
//
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("bind")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("role"))
//                        setRole((R) new NCLRole(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("component"))
//                        setComponent((N) new XTPContext(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("interface"))
//                        setInterface((I) new XTPPort(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("descriptor"))
//                        setDescriptorPath(attributes.getValue(i));
//                }
//            }
//            else if(localName.equals("bindParam")){
//                P child = createBindParam();
//                child.startElement(uri, localName, qName, attributes);
//                addBindParam(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//    
//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getSelect()!= null)
//                SelectedComponentReference();
//            if(getRole() != null);
//                //roleReference();
//            if(getComponent() != null)
//                componentReference();
//            if(getComponent() != null && getInterface() != null)
//                interfaceReference();
//            if(getDescriptor() != null);
//                //descriptorReference();
//        }
//
//        if(hasBindParam()){
//            Iterable<P> bindParams = this.getBindParams();
//            for(P param : bindParams){
//                param.endDocument();
//                addWarning(param.getWarnings());
//                addError(param.getErrors());
//            }
//        }
//    }
//
//    public void bindSearch(Iterable<D> descriptors){
//        if(this.getDescriptorPath()!=null)
//            descriptorReference(descriptors);
//        if(this.getRole()!=null)
//            searchRole();
//        else{
//            addWarning("Could not find a role");
//        }
//    }
//
//    private void descriptorReference(Iterable<D> descriptors) {
//        String[] uri = descriptorPath.split("#",2);
//
//        for(D desc : descriptors){
//            if(desc.getId().equals(uri[1])){
//                setDescriptor(desc);
//                return;
//            }
//        }
//        //@todo: descritores internos a switch de descritores podem ser utilizados?
//
//        addWarning("Could not find descriptor in descriptorBase with id: " + getDescriptor().getId());
//    }
//    private void searchRole() {
//
//        if(this.getParent() == null){
//            addWarning("Could not find a link parent");
//            return;
//        }
//
//        if(this.getParent() instanceof XTPLink){
//            Con connector = (Con)((XTPLink)(this.getParent())).getXType().getConnector();
//            if(connector == null){
//                addWarning("Could not find a connector");
//                return;
//            }
//            else{
//
//                NCLCondition cond = connector.getCondition();
//                if(cond != null){
//                    NCLRole r = findRole(cond);
//                    if(r != null){
//                        setRole((R) r);
//                        return;
//                    }
//                }
//
//                NCLAction act = connector.getAction();
//                if(act != null){
//                    NCLRole r = findRole(act);
//                    if(r != null){
//                        setRole((R) r);
//                        return;
//                    }
//                }
//
//
//            }
//        }
//        else{
//            addWarning("Parent is not a link");
//            return;
//        }
//       addWarning("Could not find role in connector with name: " + getRole().getName());
//
//    }
//
//
//
//    private void componentReference() {
//        //Search for a component node in its parent
//        if(findComponent()){
//        return;
//        }
//        else{
//        if(getParent().getParent() == null){
//            addWarning("Could not find a link parent");
//            return;
//        }
//
//        Iterable<N> nodes = null;
//
//        if(getParent().getParent() instanceof XTPBody)
//            nodes = ((XTPBody) getParent().getParent()).getNodes();
//        else if(getParent().getParent() instanceof XTPContext)
//            nodes = ((XTPContext) getParent().getParent()).getNodes();
//       
//
//        for(N node : nodes){
//            if(node.getId().equals(getComponent().getId())){
//                setComponent(node);
//                return;
//            }
//        }
//
//        addWarning("Could not find role in node with id: " + getComponent().getId());
//        }
//    }
//
//    public boolean findComponent(){
//        XMLElement root = this.getParent();
//         while(!(root instanceof XTPDoc)){
//            root = root.getParent();
//         }
//         if(((XTPDoc) root).getVocabulary()==null){
//            addWarning("Could not find a Vocabulary");
//            return false;
//         }
//        Iterable<Cp> components = (((XTPVocabulary)((XTPDoc) root).getVocabulary())).getComponents();
//        for(Cp comp : components){
//            if(this.getComponent().getId().equals(comp.getXLabel())){
//                setComponent((N) comp);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void interfaceReference() {
//        //Search for the interface inside the node
//        Iterable<I> ifaces;
//        if(getComponent() instanceof XTPMedia){
//            ifaces = ((XTPMedia) getComponent()).getAreas();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//                else if((((XTPArea) iface).getXLabel()).equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//            ifaces = ((XTPMedia) getComponent()).getProperties();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//                else if((((XTPProperty) iface).getXLabel()).equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//        else if(getComponent() instanceof XTPContext){
//            ifaces = ((XTPContext) getComponent()).getPorts();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//                else if((((XTPPort) iface).getXLabel()).equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//            ifaces = ((XTPContext) getComponent()).getProperties();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//                else if((((XTPProperty) iface).getXLabel()).equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//        else if(getComponent() instanceof XTPSwitch){
//            ifaces = ((XTPSwitch) getComponent()).getPorts();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//        else if(getComponent() instanceof XTPComponent){
//            ifaces = ((XTPComponent) getComponent()).getComponentPorts();
//            for(I iface : ifaces){
//                if(((XTPComponent)iface).getXLabel().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//
//        addWarning("Could not find interface with id: " + getInterface().getId());
//    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
