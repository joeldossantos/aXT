/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT.Port;

import AXT.Component.ComponentPort;
import AXT.Component.XTemplateComponent;
import AXT.Context.XTemplateContext;
import AXT.Media.XTemplateArea;
import AXT.Media.XTemplateMedia;
import AXT.Media.XTemplateProperty;
import AXT.Switch.XTemplateSwitch;
import AXT.XMLElement;
import AXT.XTemplateBody;
import AXT.XTemplateDoc;
import AXT.XTemplateElement;
import AXT.XTemplateVocabulary;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.node.NCLNode;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author flavia
 */
public class XTemplatePort<N extends NCLNode, I extends NCLInterface, Cp extends XTemplateComponent> extends NCLPort {

    private String xlabel;
    private String select;//tentar implementar como XPath
    private XMLElement selectedElement;
    
    public XTemplatePort(String id) throws NCLInvalidIdentifierException{
        super(id);
    }
    public XTemplatePort(String id, String xlabel, String select) throws NCLInvalidIdentifierException{
        super(id);
        this.select = select;
        this.xlabel = xlabel;
    }

    public XTemplatePort()throws NCLInvalidIdentifierException{
        super(null);
        this.select = null;
        this.xlabel = null;
    }

     public XTemplatePort(XMLReader reader, XMLElement parent){
        super();
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }

     //metodos de acesso

    public String getXLabel(){
        return this.xlabel;
    }

    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }

    public String getSelect(){
        return this.select;
    }

    public void setSelect(String select){
        this.select = select;
    }

    public XMLElement getSelectedElement(){
        return this.selectedElement;
    }

    public void setSelectedElement(XMLElement element){
        this.selectedElement = element;
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
    //metodos d parse

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("id"))
                    setId(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("xlabel"))
                    setXLabel(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("select"))
                    setSelect(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("component"))
                    setComponent((N) new XTemplateContext(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("interface"))
                    setInterface((I) new XTemplatePort(attributes.getValue(i)));
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public void endDocument() {
        if(getParent() == null)
            return;
        if(getSelect() != null)
            SelectedComponentReference();
        if(getComponent() != null)
            componentReference();

        if(getComponent() != null && getInterface() != null)
            interfaceReference();
    }

    private void SelectedComponentReference(){
        XMLElement root = getParent();
        while(!(root instanceof XTemplateDoc)){
            root = root.getParent();
        }

        if(this.getSelectedInterfaceXLabel(select)!=null){
            XMLElement component = ((XTemplateDoc)root).getVocabulary().findComponent(getSelectedInterfaceXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }
        if(this.getSelectedComponentXLabel(select)!=null){
            XMLElement component = ((XTemplateDoc)root).getVocabulary().findComponent(getSelectedComponentXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }

        addWarning("Could not find selected Element with xlabel"+getSelectedComponentXLabel(select)+"with interface"+getSelectedInterfaceXLabel(select));
    }
    private void componentReference() {
        //Search for a component node in its parent
        
        if(findComponent()){
            return;
        }
        else{
            
            Set<N> nodes;


        if(getParent() instanceof XTemplateBody)
            nodes = ((XTemplateBody) getParent()).getNodes();
        else
            nodes = ((XTemplateContext) getParent()).getNodes();

        for(N node : nodes){
            if(node.getId().equals(getComponent().getId())){
                setComponent(node);
                return;
            }
            else{
                
                if(node instanceof XTemplateMedia){
                    if(((XTemplateMedia) node).getXLabel().equals(getComponent().getId())){
                        setComponent(node);
                        return;
                    }
                    
                }
                else if(node instanceof XTemplateContext){
                    if(((XTemplateContext) node).getXLabel().equals(getComponent().getId())){
                        setComponent(node);
                        return;
                    }
                }
                else if(node instanceof XTemplateSwitch){
                    if(((XTemplateSwitch) node).getXLabel().equals(getComponent().getId())){
                        setComponent(node);
                        return;
                    }
                }
            }

        }
        /* Dúvida: sei que também posso ter uma referência a um xlabel no atributo
         * component da porta. Porém, fazer cast de N para XTemplateElement não garante
         * que eu tenha xlabel. Porém, implementar xlabel em XTemplateElement não
         * faz muito sentido pois nem todos os elementos xtemplate possuem xlabel.
          */
            


        addWarning("Could not find node with id: " + getComponent().getId());
    }
}

    public boolean findComponent(){
        XMLElement root = this.getParent();
         while(!(root instanceof XTemplateDoc)){
            root = root.getParent();
         }
         if(((XTemplateDoc) root).getVocabulary()==null){
            addWarning("Could not find a Vocabulary");
            return false;
         }
        Iterable<Cp> components = (((XTemplateVocabulary)((XTemplateDoc) root).getVocabulary())).getComponents();
        for(Cp comp : components){
            if(this.getComponent().getId().equals(comp.getXLabel())){
                setComponent(comp);
                return true;
            }
        }
        return false;
    }

    private void interfaceReference() {
        //Search for the interface inside the node
        Iterable<I> ifaces;

        if(getComponent() instanceof XTemplateMedia){
            ifaces = ((XTemplateMedia) getComponent()).getAreas();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
                else if(((XTemplateArea) iface).getXLabel().equals(getInterface().getId()))
                {
                    setInterface(iface);
                    return;
                }
            }
            ifaces = ((XTemplateMedia) getComponent()).getProperties();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
                else if(((XTemplateProperty) iface).getXLabel().equals(getInterface().getId()))
                {
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof XTemplateContext){
            ifaces = ((XTemplateContext) getComponent()).getPorts();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
                else if(((XTemplatePort) iface).getXLabel().equals(getInterface().getId()))
                {
                    setInterface(iface);
                    return;
                }
            }
            ifaces = ((XTemplateContext) getComponent()).getProperties();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
                else if(((XTemplateProperty) iface).getXLabel().equals(getInterface().getId()))
                {
                    setInterface(iface);
                    return;
                }
            }
        }
        else if(getComponent() instanceof XTemplateSwitch){
            ifaces = ((XTemplateSwitch) getComponent()).getPorts();
            for(I iface : ifaces){
                if(iface.getId().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }

            }
        }
        else if(getComponent() instanceof XTemplateComponent){
            ifaces = ((XTemplateComponent) getComponent()).getComponentPorts();
            for(I iface : ifaces){
                if(((ComponentPort)iface).getXLabel().equals(getInterface().getId())){
                    setInterface(iface);
                    return;
                }
            }
        }
        addWarning("Could not find interface with id: " + getInterface().getId());
        addWarning("Could not find interface with xlabel: " + ((XTemplateElement)getInterface()).getXLabel());
    }

    @Override
    public String parse(int ident) {return null;}


    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}




}
