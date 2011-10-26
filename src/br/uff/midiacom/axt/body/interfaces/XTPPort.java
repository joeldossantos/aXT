package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.body.node.XTPNode;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPPort<T extends XTPPort, P extends XTPElement, I extends XMLElementImpl, En extends XTPNode, Ei extends XTPInterface> extends XTPPortPrototype<T, P, I, En, Ei> implements XTPInterface<Ei, P> {

    
    public XTPPort(String id) throws XMLException {
        super(id);
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
//    //metodos d parse
//
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("id"))
//                    setId(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("xlabel"))
//                    setXLabel(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("select"))
//                    setSelect(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("component"))
//                    setComponent((N) new XTPContext(attributes.getValue(i)));
//                else if(attributes.getLocalName(i).equals("interface"))
//                    setInterface((I) new XTPPort(attributes.getValue(i)));
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument() {
//        if(getParent() == null)
//            return;
//        if(getSelect() != null)
//            SelectedComponentReference();
//        if(getComponent() != null)
//            componentReference();
//
//        if(getComponent() != null && getInterface() != null)
//            interfaceReference();
//    }
//
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
//    private void componentReference() {
//        //Search for a component node in its parent
//        
//        if(findComponent()){
//            return;
//        }
//        else{
//            
//            Set<N> nodes;
//
//
//        if(getParent() instanceof XTPBody)
//            nodes = ((XTPBody) getParent()).getNodes();
//        else
//            nodes = ((XTPContext) getParent()).getNodes();
//
//        for(N node : nodes){
//            if(node.getId().equals(getComponent().getId())){
//                setComponent(node);
//                return;
//            }
//            else{
//                
//                if(node instanceof XTPMedia){
//                    if(((XTPMedia) node).getXLabel().equals(getComponent().getId())){
//                        setComponent(node);
//                        return;
//                    }
//                    
//                }
//                else if(node instanceof XTPContext){
//                    if(((XTPContext) node).getXLabel().equals(getComponent().getId())){
//                        setComponent(node);
//                        return;
//                    }
//                }
//                else if(node instanceof XTPSwitch){
//                    if(((XTPSwitch) node).getXLabel().equals(getComponent().getId())){
//                        setComponent(node);
//                        return;
//                    }
//                }
//            }
//
//        }
//        /* Dúvida: sei que também posso ter uma referência a um xlabel no atributo
//         * component da porta. Porém, fazer cast de N para XTPElement não garante
//         * que eu tenha xlabel. Porém, implementar xlabel em XTPElement não
//         * faz muito sentido pois nem todos os elementos xtemplate possuem xlabel.
//          */
//            
//
//
//        addWarning("Could not find node with id: " + getComponent().getId());
//    }
//}
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
//                setComponent(comp);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void interfaceReference() {
//        //Search for the interface inside the node
//        Iterable<I> ifaces;
//
//        if(getComponent() instanceof XTPMedia){
//            ifaces = ((XTPMedia) getComponent()).getAreas();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//                else if(((XTPArea) iface).getXLabel().equals(getInterface().getId()))
//                {
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
//                else if(((XTPProperty) iface).getXLabel().equals(getInterface().getId()))
//                {
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
//                else if(((XTPPort) iface).getXLabel().equals(getInterface().getId()))
//                {
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
//                else if(((XTPProperty) iface).getXLabel().equals(getInterface().getId()))
//                {
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
//
//            }
//        }
//        else if(getComponent() instanceof XTPComponent){
//            ifaces = ((XTPComponent) getComponent()).getComponentPorts();
//            for(I iface : ifaces){
//                if(((XTPComponentPort)iface).getXLabel().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//        addWarning("Could not find interface with id: " + getInterface().getId());
//        addWarning("Could not find interface with xlabel: " + ((XTPElement)getInterface()).getXLabel());
//    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
