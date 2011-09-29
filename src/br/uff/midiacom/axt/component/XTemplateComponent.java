

package br.uff.midiacom.axt.component;

import br.uff.midiacom.axt.importBase.XTemplateDescriptorBase;
import AXT.XMLElement;
import AXT.XTemplateDoc;
import AXT.XTemplateElement;
import AXT.XTemplateHead;
import AXT.XTemplateValues.XTemplateXType;
import AXT.XTemplateXLabeledElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;



public class XTemplateComponent<P extends ComponentPort, C extends XTemplateComponent, D extends NCLLayoutDescriptor, I extends NCLImport>
        extends XTemplateElement implements NCLNode{

    private String xlabel;
    private static final int unbounded = 1000000000;
    private int maxOccurs;
    private int minOccurs;
    private XTemplateXType xtype;//implementar como enum em XTemplateValues
    private String descriptorPath;//referencia ao descritor de uma base importada
    private D descriptor;
    private List<P> interfaces = new ArrayList<P>();
    private List<C> components = new ArrayList<C>();
    private boolean insideComponent;
    //construtores



    public XTemplateComponent(){
        this.maxOccurs = unbounded;
        this.minOccurs = 0;
        this.xtype= null;
        this.descriptor = null;
        
        this.setXLabel(null);
    }

    public XTemplateComponent(int maxOccurs, int minOccurs, XTemplateXType xtype, String descriptor, String xlabel){
        this.maxOccurs = maxOccurs;
        this.minOccurs = minOccurs;
        this.xtype= xtype;
        this.descriptorPath = descriptor;
        this.xlabel = xlabel;
    }

    public XTemplateComponent(XMLReader reader, XTemplateElement parent) {
        setReader(reader);
        setParent(parent);
        getReader().setContentHandler(this);
        insideComponent = false;
    }

    @Override
     public String getXLabel(){
        return this.xlabel;
    }

    @Override
    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }

    public void setMaxOccurs(int maxOccurs){
        this.maxOccurs = maxOccurs;
    }

    public int getMaxOccurs(){
        return this.maxOccurs;
    }

    public void setMinOccurs(int minOccurs){
       this.minOccurs = minOccurs;
    }

    public int getMinOccurs(){
        return this.minOccurs;
    }

    public void setDescriptorPath(String descriptorPath){
        this.descriptorPath = descriptorPath;
    }

    public String getDescriptorPath(){
        return this.descriptorPath;
    }

    public void setDescriptor(D descriptor){
        this.descriptor = descriptor;
    }

    public D getDescriptor(){
        return this.descriptor;
    }

    //criar um tipo xtype em xtempatevalues

    public void setXType(XTemplateXType xtype){
        this.xtype = xtype;
    }

    public XTemplateXType getXType(){
        return this.xtype;
    }

    public boolean addComponentPort(P cPort) {
         //Se a porta for inserida com sucesso, entra no if
         //senão, retorna falso
         if(interfaces.add(cPort)){
           // se a porta existir, será atribuída ao component atual
            if(cPort != null)
                cPort.setParent(this);

            return true;
        }
        return false;
    }



    public boolean removeComponentPort(String xlabel) {
        for(P cPort : interfaces){
            if(cPort.getXLabel().equals(xlabel))
                return removeComponentPort(cPort);
        }
        return false;
    }


    public boolean removeComponentPort(P cPort) {
         //Se a porta for inserida com sucesso, entra no if
         //senão, retorna falso
        if(interfaces.remove(cPort)){
            //Se a porta existir, retira o parentesco com o component atual
            if(cPort != null)
                cPort.setParent(null);

            return true;
        }
        return false;
    }



    public boolean hasComponentPort(String xlabel) {
        for(ComponentPort port : interfaces){
            if(port.getXLabel().equals(xlabel))
                return true;
        }
        return false;
    }



    public boolean hasComponentPort(P cPort) {
        return interfaces.contains(cPort);
    }



    public boolean hasComponentPort() {
        return !interfaces.isEmpty();
    }



    public List<P> getComponentPorts() {
        return interfaces;
    }

    public boolean addComponent(C component) {
         //Se a porta for inserida com sucesso, entra no if
         //senão, retorna falso
         if(components.add(component)){
           // se a porta existir, será atribuída ao component atual
            if(component != null)
                component.setParent(this);

            return true;
        }
        return false;
    }



    public boolean removeComponent(String xlabel) {
        for(C component : components){
            if(component.getXLabel().equals(xlabel))
                return removeComponent(component);
        }
        return false;
    }


    public boolean removeComponent(C component) {
         //Se a porta for inserida com sucesso, entra no if
         //senão, retorna falso
        if(components.remove(component)){
            //Se a porta existir, retira o parentesco com o component atual
            if(component != null)
                component.setParent(null);

            return true;
        }
        return false;
    }



    public boolean hasComponent(String xlabel) {
        for(C component : components){
            if(component.getXLabel().equals(xlabel))
                return true;
        }
        return false;
    }



    public boolean hasComponent(C cPort) {
        return components.contains(cPort);
    }



    public boolean hasComponent() {
        return !components.isEmpty();
    }



    public List<C> getComponents() {
        return components;
    }

    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("component")&& !insideComponent){
                System.out.println("start element do component");
                cleanWarnings();
                cleanErrors();
                insideComponent = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("xlabel")){
                        
                        setXLabel(attributes.getValue(i));
                        
                    }
                    else if(attributes.getLocalName(i).equals("maxOccurs")){
                        
                        if(attributes.getValue(i).equals("unbounded"))
                            setMaxOccurs(unbounded);
                        else
                            setMaxOccurs(Integer.parseInt(attributes.getValue(i)));
                       
                        }
                    else if(attributes.getLocalName(i).equals("minOccurs")){
                       
                        setMinOccurs(Integer.parseInt(attributes.getValue(i)));
                        }
                    else if(attributes.getLocalName(i).equals("xtype")){
                            
                            setXType(null);
                            for(XTemplateXType type : XTemplateXType.values()){
                            if(type.toString().equals(attributes.getValue(i))){
                                setXType(type);
                                break;
                            }
                                
                        }
                            System.out.println(getXType().toString());
                        }
                    else if(attributes.getLocalName(i).equals("descriptor")){
                        
                        setDescriptorPath(attributes.getValue(i));
                        
                    }
                }
            }
            else if(localName.equals("port")){
                
                P child = createComponentPort();
                
                child.startElement(uri, localName, qName, attributes);
                addComponentPort(child);
            }
            else if(localName.equals("component") && insideComponent){
                C child = createComponent();
                child.startElement(uri, localName, qName, attributes);
                addComponent(child);
            }
        }
       catch(/*NCLInvalidIdentifierException*/Exception ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        System.out.println("endDocument de xtemplateComponent");
        if(getParent() != null){

                //descriptorReference();

            }
        

        if(hasComponentPort()){
            for(P port : interfaces){
                port.endDocument();
                addWarning(port.getWarnings());
                addError(port.getErrors());
            }
        }

        if(hasComponent()){
            for(C component : components){
                component.endDocument();
                addWarning(component.getWarnings());
                addError(component.getErrors());
            }
        }
    }

    public void searchDescriptor(Iterable<D> descriptors) {
        if(descriptorPath!= null){
        String[] uri = this.descriptorPath.split("#",2);
        if(findDescriptor(descriptors)!= null){
            setDescriptor(findDescriptor(descriptors));
            return;
        }
        addWarning("Could not find descriptor in descriptorBase with id:"+uri[1]);
        
    }
    }
    


    public D findDescriptor(Iterable<D> descriptors) {
        String[] uri = this.descriptorPath.split("#",2);
        for(D d : descriptors){
                if(d.getId().equals(uri[1]))
                return d;
                }

            return null;
        }


    

    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addWarning(String warning) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected P createComponentPort() {
        return (P) new ComponentPort(getReader(), this);
    }

    protected C createComponent() {
        return (C) new XTemplateComponent(getReader(), this);
    }

    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setId(String id) throws NCLInvalidIdentifierException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
