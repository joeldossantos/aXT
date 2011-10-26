package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPXLabeledElement;
import br.uff.midiacom.axt.body.interfaces.XTPArea;
import br.uff.midiacom.axt.body.interfaces.XTPProperty;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPMediaPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPMedia<T extends XTPMedia, P extends XTPElement, I extends XMLElementImpl, Ea extends XTPArea, Ep extends XTPProperty, Ed extends NCLLayoutDescriptor, En extends NCLNode, Ei extends NCLInterface> extends XTPMediaPrototype<T, P, I, Ea, Ep, Ed, En, Ei> implements XTPXLabeledElement<T, P> {

    
    public XTPMedia(String id, NCLMimeType type, String xlabel) throws XMLException {
        super(id, type, xlabel);
    }
    

//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("media")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("xlabel")){
//                        setXLabel(attributes.getValue(i));
//                    }
//                    else if(attributes.getLocalName(i).equals("src")){
//                         try{
//                            setSrc(attributes.getValue(i));
//                        }
//                        catch(URISyntaxException ex){
//                            setSrc(new NCLTime(attributes.getValue(i)));
//                        }
//
//                    }
//                    else if(attributes.getLocalName(i).equals("type")){
//                        for(NCLMimeType m : NCLMimeType.values()){
//                            if(m.toString().equals(attributes.getValue(i)))
//                                setType(m);
//                        }
//                    }
//                    else if(attributes.getLocalName(i).equals("descriptor"))
//                        setDescriptor((D) new NCLDescriptor(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("refer"))
//                        setRefer((M) new XTPMedia(attributes.getValue(i),"unknown"));
//                    else if(attributes.getLocalName(i).equals("instance")){
//                        for(NCLInstanceType in : NCLInstanceType.values()){
//                            if(in.toString().equals(attributes.getValue(i)))
//                                setInstance(in);
//                        }
//                    }
//                }
//            }
//            else if(localName.equals("area")){
//            A child = createXTemplateArea();
//                child.startElement(uri, localName, qName, attributes);
//                addArea(child);
//            }
//            else if(localName.equals("property")){
//                P child = createXTemplateProperty();
//                child.startElement(uri, localName, qName, attributes);
//                addProperty(child);
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument(){
//        if(getParent() != null){
//            if(getDescriptor() != null);
//                //descriptorReference();
//            if(getRefer() != null)
//                mediaReference();
//        }
//
//        if(hasArea()){
//            Iterable<A> areas = this.getAreas();
//            for(A area : areas){
//                area.endDocument();
//                addWarning(area.getWarnings());
//                addError(area.getErrors());
//            }
//        }
//        if(hasProperty()){
//            Iterable<P> properties = this.getProperties();
//            for(P property : properties){
//                property.endDocument();
//                addWarning(property.getWarnings());
//                addError(property.getErrors());
//            }
//        }
//    }
//
//    private void mediaReference() {
//        //Search for the interface inside the node
//        XMLElement body = getParent();
//
//        while(!(body instanceof XTPBody)){
//            body = body.getParent();
//            if(body == null){
//                addWarning("Could not find a body");
//                return;
//            }
//        }
//
//        setRefer(findMedia(((XTPBody) body).getNodes()));
//    }
//
//    private M findMedia(Set<N> nodes) {
//        for(N n : nodes){
//            if(n instanceof XTPMedia){
//                if(n.getId().equals(getRefer().getId()))
//                    return (M) n;
//            }
//            else if(n instanceof XTPContext){
//                if( ((XTPContext) n).hasNode()){
//                    Set<N> cnodes = ((XTPContext) n).getNodes();
//                    M m = findMedia(cnodes);
//                    if(m != null)
//                        return (M) m;
//                }
//            }
//            else if(n instanceof XTPSwitch){
//                if( ((XTPSwitch) n).hasNode()){
//                    Set<N> snodes = ((XTPSwitch) n).getNodes();
//                    M m = findMedia(snodes);
//                    if(m != null)
//                        return (M) m;
//                }
//            }
//        }
//
//        addWarning("Could not find media with id: " + getRefer().getId());
//        return null;
//    }
//
//    public void searchMedia(Iterable<D> descriptors) {
//
//        if(this.getDescriptor()!= null){
//        for(D desc : descriptors){
//            if(desc.getId().equals(getDescriptor().getId())){
//                setDescriptor(desc);
//                return;
//            }
//        }
//
//
//        addWarning("Could not find descriptor in descriptorBase with id: " + getDescriptor().getId());
//        }
//    }
}
