package br.uff.midiacom.axt.link;

import br.uff.midiacom.axt.component.XTPComponentPort;
import br.uff.midiacom.axt.component.XTPComponent;
import br.uff.midiacom.axt.context.XTPContext;
import br.uff.midiacom.axt.iteration.XTPForEach;
import br.uff.midiacom.axt.Media.XTPArea;
import br.uff.midiacom.axt.Media.XTPMedia;
import br.uff.midiacom.axt.Media.XTPProperty;
import br.uff.midiacom.axt.port.XTPPort;
import br.uff.midiacom.axt.Switch.XTPSwitch;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.connector.NCLAction;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.connector.NCLCondition;
import br.uff.midiacom.ana.connector.NCLRole;
import br.uff.midiacom.ana.datatype.NCLParamInstance;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.link.NCLBind;
import br.uff.midiacom.ana.link.NCLParam;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


public class XTPBind<P extends NCLParam, R extends NCLRole, N extends NCLNode, I extends NCLInterface,
        D extends NCLLayoutDescriptor, Im extends NCLImport, C extends XTPComponent, Cp extends XTPComponentPort,
        Con extends NCLCausalConnector> extends NCLBind {

    private String select;
    private String descriptorPath;
    private XMLElement selectedElement;
    

    //construtores

    public XTPBind(){};

    public XTPBind(XMLReader reader, XMLElement parent) {

        super();

        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }
     //metodos de acesso

     public String getSelect(){
        return this.select;
    }

        public void setSelect(String select){
        this.select = select;
    }

     public String getDescriptorPath(){
        return this.descriptorPath;
    }

        public void setDescriptorPath(String descriptorPath){
        this.descriptorPath = descriptorPath;
    }

    public String getSelectedComponentXLabel(String select){
                    String pxlabel;
                    if(select.contains("@xlabel")){
                        pxlabel = select.substring(select.indexOf('@')+1);


                        String[] split =  pxlabel.split("'", 3);
                        pxlabel = split[1];

                        return pxlabel;
                    }
                    else{
                    return pxlabel = "null";
                    }


                }
     public String getSelectedInterfaceXLabel(String select){
            String pxlabel;
                    if(select.contains("@xlabel")){
                        pxlabel = select.substring(select.indexOf('@')+1);
                        if(pxlabel.contains("@xlabel")){
                            pxlabel = pxlabel.substring(pxlabel.indexOf('@'));
                            String[] split =  pxlabel.split("'", 3);
                            pxlabel = split[1];
                            return pxlabel;
                        }
                    }
                    return null;
    }
    public XMLElement getSelectedElement(){
        return this.selectedElement;
    }

    public void setSelectedElement(XMLElement element){
        this.selectedElement = element;
    }
    private void SelectedComponentReference(){
        XMLElement root = getParent();
        while(!(root instanceof XTPDoc)){
            root = root.getParent();
        }

        if(this.getSelectedInterfaceXLabel(select)!=null){
            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedInterfaceXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }
        if(this.getSelectedComponentXLabel(select)!=null){
            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedComponentXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }

        addWarning("Could not find selected Element with xlabel"+getSelectedComponentXLabel(select)+"with interface"+getSelectedInterfaceXLabel(select));
    }
    @Override

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("bind")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("role"))
                        setRole((R) new NCLRole(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("component"))
                        setComponent((N) new XTPContext(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("interface"))
                        setInterface((I) new XTPPort(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("descriptor"))
                        setDescriptorPath(attributes.getValue(i));
                }
            }
            else if(localName.equals("bindParam")){
                P child = createBindParam();
                child.startElement(uri, localName, qName, attributes);
                addBindParam(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public String parse(int ident) {return null;}

    
    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}

    
    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getSelect()!= null)
                SelectedComponentReference();
            if(getRole() != null);
                //roleReference();
            if(getComponent() != null)
                componentReference();
            if(getComponent() != null && getInterface() != null)
                interfaceReference();
            if(getDescriptor() != null);
                //descriptorReference();
        }

        if(hasBindParam()){
            Iterable<P> bindParams = this.getBindParams();
            for(P param : bindParams){
                param.endDocument();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }
    }

    public void bindSearch(Iterable<D> descriptors){
        if(this.getDescriptorPath()!=null)
            descriptorReference(descriptors);
        if(this.getRole()!=null)
            searchRole();
        else{
            addWarning("Could not find a role");
        }
    }

    private void descriptorReference(Iterable<D> descriptors) {
        String[] uri = descriptorPath.split("#",2);

        for(D desc : descriptors){
            if(desc.getId().equals(uri[1])){
                setDescriptor(desc);
                return;
            }
        }
        //@todo: descritores internos a switch de descritores podem ser utilizados?

        addWarning("Could not find descriptor in descriptorBase with id: " + getDescriptor().getId());
    }
    private void searchRole() {

        if(this.getParent() == null){
            addWarning("Could not find a link parent");
            return;
        }

        if(this.getParent() instanceof XTPLink){
            Con connector = (Con)((XTPLink)(this.getParent())).getXType().getConnector();
            if(connector == null){
                addWarning("Could not find a connector");
                return;
            }
            else{

                NCLCondition cond = connector.getCondition();
                if(cond != null){
                    NCLRole r = findRole(cond);
                    if(r != null){
                        setRole((R) r);
                        return;
                    }
                }

                NCLAction act = connector.getAction();
                if(act != null){
                    NCLRole r = findRole(act);
                    if(r != null){
                        setRole((R) r);
                        return;
                    }
                }


            }
        }
        else{
            addWarning("Parent is not a link");
            return;
        }
       addWarning("Could not find role in connector with name: " + getRole().getName());

    }



    private void componentReference() {
        //Search for a component node in its parent
        if(findComponent()){
        return;
        }
        else{
        if(getParent().getParent() == null){
            addWarning("Could not find a link parent");
            return;
        }

        Iterable<N> nodes = null;

        if(getParent().getParent() instanceof XTPBody)
            nodes = ((XTPBody) getParent().getParent()).getNodes();
        else if(getParent().getParent() instanceof XTPContext)
            nodes = ((XTPContext) getParent().getParent()).getNodes();
       

        for(N node : nodes){
            if(node.getId().equals(getComponent().getId())){
                setComponent(node);
                return;
            }
        }

        addWarning("Could not find role in node with id: " + getComponent().getId());
        }
    }

    public boolean findComponent(){
        XMLElement root = this.getParent();
         while(!(root instanceof XTPDoc)){
            root = root.getParent();
         }
         if(((XTPDoc) root).getVocabulary()==null){
            addWarning("Could not find a Vocabulary");
            return false;
         }
        Iterable<Cp> components = (((XTPVocabulary)((XTPDoc) root).getVocabulary())).getComponents();
        for(Cp comp : components){
            if(this.getComponent().getId().equals(comp.getXLabel())){
                setComponent((N) comp);
                return true;
            }
        }
        return false;
    }

    private void interfaceReference() {
        //Search for the interface inside the node
        Iterable<I> ifaces;
        if(getComponent() instanceof XTPMedia){
            ifaces = ((XTPMedia) getComponent()).getAreas();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
                else if((((XTPArea) iface).getXLabel()).equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
            ifaces = ((XTPMedia) getComponent()).getProperties();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
                else if((((XTPProperty) iface).getXLabel()).equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof XTPContext){
            ifaces = ((XTPContext) getComponent()).getPorts();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
                else if((((XTPPort) iface).getXLabel()).equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
            ifaces = ((XTPContext) getComponent()).getProperties();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
                else if((((XTPProperty) iface).getXLabel()).equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof XTPSwitch){
            ifaces = ((XTPSwitch) getComponent()).getPorts();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof XTPComponent){
            ifaces = ((XTPComponent) getComponent()).getComponentPorts();
            for(I iface : ifaces){
                if(((XTPComponent)iface).getXLabel().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }

        addWarning("Could not find interface with id: " + getInterface().getId());
    }
    /*@Override
    public void endDocument() {
        if(getParent() != null){
            if(getRole() != null)
                roleReference();
            if(getComponent() != null)
                componentReference();
            if(getComponent() != null && getInterface() != null)
                interfaceReference();
            if(getDescriptor() != null)
                descriptorReference();
        }

        if(hasBindParam()){
            for(P param : bindParams){
                param.endDocument();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }
    }*/

    

    /*private Iterable<NCLCausalConnector>findImportedConnectors(){

        XMLElement root = getParent();

        while(!(root.getParent() instanceof XTPDoc)){
            root = root.getParent();
            if(root == null){
                addWarning("Could not find a root element");
                return null;
            }
        }

        if(((XTPDoc) root).getHead() == null){
            addWarning("Could not find a head");
            return null;
        }

        Iterable<I> importedConBases = ((XTPDoc) root).getHead().getConnectorBase().getImportBases();
        
        for (I base : importedConBases){

        }
    }*/

    @Override
protected P createBindParam() {
        return (P) new XTPParam(NCLParamInstance.BINDPARAM, getReader(), this);
    }
}



