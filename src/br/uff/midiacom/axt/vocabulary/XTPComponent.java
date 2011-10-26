package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPXLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPComponentPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;



public class XTPComponent<T extends XTPComponent, P extends XTPElement, I extends XMLElementImpl, Ed extends NCLLayoutDescriptor, Ep extends XTPComponentPort> extends XTPComponentPrototype<T, P, I, Ed, Ep> implements XTPXLabeledElement<T, P> {

    
    public XTPComponent(String xlabel) throws XMLException {
        super(xlabel);
    }

    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("component")&& !insideComponent){
//                System.out.println("start element do component");
//                cleanWarnings();
//                cleanErrors();
//                insideComponent = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("xlabel")){
//                        
//                        setXLabel(attributes.getValue(i));
//                        
//                    }
//                    else if(attributes.getLocalName(i).equals("maxOccurs")){
//                        
//                        if(attributes.getValue(i).equals("unbounded"))
//                            setMaxOccurs(unbounded);
//                        else
//                            setMaxOccurs(Integer.parseInt(attributes.getValue(i)));
//                       
//                        }
//                    else if(attributes.getLocalName(i).equals("minOccurs")){
//                       
//                        setMinOccurs(Integer.parseInt(attributes.getValue(i)));
//                        }
//                    else if(attributes.getLocalName(i).equals("xtype")){
//                            
//                            setXType(null);
//                            for(XTemplateXType type : XTemplateXType.values()){
//                            if(type.toString().equals(attributes.getValue(i))){
//                                setXType(type);
//                                break;
//                            }
//                                
//                        }
//                            System.out.println(getXType().toString());
//                        }
//                    else if(attributes.getLocalName(i).equals("descriptor")){
//                        
//                        setDescriptorPath(attributes.getValue(i));
//                        
//                    }
//                }
//            }
//            else if(localName.equals("port")){
//                
//                P child = createComponentPort();
//                
//                child.startElement(uri, localName, qName, attributes);
//                addComponentPort(child);
//            }
//            else if(localName.equals("component") && insideComponent){
//                C child = createComponent();
//                child.startElement(uri, localName, qName, attributes);
//                addComponent(child);
//            }
//        }
//       catch(/*NCLInvalidIdentifierException*/Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        System.out.println("endDocument de xtemplateComponent");
//        if(getParent() != null){
//
//                //descriptorReference();
//
//            }
//        
//
//        if(hasComponentPort()){
//            for(P port : interfaces){
//                port.endDocument();
//                addWarning(port.getWarnings());
//                addError(port.getErrors());
//            }
//        }
//
//        if(hasComponent()){
//            for(C component : components){
//                component.endDocument();
//                addWarning(component.getWarnings());
//                addError(component.getErrors());
//            }
//        }
//    }
//
//    public void searchDescriptor(Iterable<D> descriptors) {
//        if(descriptorPath!= null){
//        String[] uri = this.descriptorPath.split("#",2);
//        if(findDescriptor(descriptors)!= null){
//            setDescriptor(findDescriptor(descriptors));
//            return;
//        }
//        addWarning("Could not find descriptor in descriptorBase with id:"+uri[1]);
//        
//    }
//    }
//    
//
//
//    public D findDescriptor(Iterable<D> descriptors) {
//        String[] uri = this.descriptorPath.split("#",2);
//        for(D d : descriptors){
//                if(d.getId().equals(uri[1]))
//                return d;
//                }
//
//            return null;
//        }
    
    
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
