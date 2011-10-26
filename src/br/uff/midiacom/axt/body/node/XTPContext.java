package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.body.interfaces.XTPPort;
import br.uff.midiacom.axt.body.interfaces.XTPProperty;
import br.uff.midiacom.axt.body.link.XTPLink;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPContextPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPContext<T extends XTPContext, P extends XTPElement, I extends XMLElementImpl, Ept extends XTPPort, Epp extends XTPProperty, En extends XTPNode, Ei extends XTPInterface, El extends XTPLink, Em extends NCLMeta, Emt extends NCLMetadata> extends XTPContextPrototype<T, P, I, Ept, Epp, En, Ei, El, Em, Emt> implements XTPNode<En, P> {

    
    public XTPContext(String id, String xlabel) throws XMLException {
        super(id, xlabel);
    }

//     @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("context") && !insideContextFlag){
//                cleanWarnings();
//                cleanErrors();
//                insideContextFlag = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("xlabel"))
//                        setXLabel(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("refer"))
//                        setRefer((C) new XTPContext(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("meta")){
//                M child = (M) createMeta();
//                child.startElement(uri, localName, qName, attributes);
//                addMeta(child);
//            }
//            else if(localName.equals("metadata")){
//                MT child = (MT) createMetadata();
//                child.startElement(uri, localName, qName, attributes);
//                addMetadata(child);
//            }
//            else if(localName.equals("port")){
//                Pt child = createPort();
//                child.startElement(uri, localName, qName, attributes);
//                addPort(child);
//            }
//            else if(localName.equals("property")){
//                Pp child = createProperty();
//                child.startElement(uri, localName, qName, attributes);
//                addProperty(child);
//            }
//            else if(localName.equals("media")){
//                N child = createMedia();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("context") && insideContextFlag){
//                N child = createContext();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("switch")){
//                N child = createSwitch();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("link")){
//                L child = createLink();
//                child.startElement(uri, localName, qName, attributes);
//                addLink(child);
//            }
//            else if(localName.equals("for-each")){
//                FE child = createForEach();
//                child.startElement(uri, localName, qName, attributes);
//                addForEach(child);
//            }
//            else if(localName.equals("variable")){
//                V child = createVariable();
//                child.startElement(uri, localName, qName, attributes);
//                addVariable(child);
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getRefer() != null)
//                contextReference();
//        }
//
//        if(hasMeta()){
//            Iterable<M> metas = this.getMetas();
//            for(M meta : metas){
//                meta.endDocument();
//                addWarning(meta.getWarnings());
//                addError(meta.getErrors());
//            }
//        }
//        if(hasMetadata()){
//            Iterable<MT> metadatas = this.getMetadatas();
//            for(MT metadata : metadatas){
//                metadata.endDocument();
//                addWarning(metadata.getWarnings());
//                addError(metadata.getErrors());
//            }
//        }
//        if(hasPort()){
//            Iterable<Pt> ports = this.getPorts();
//            for(Pt port : ports){
//                port.endDocument();
//                addWarning(port.getWarnings());
//                addError(port.getErrors());
//            }
//        }
//        if(hasProperty()){
//            Iterable<Pp> properties = this.getProperties();
//            for(Pp property : properties){
//                property.endDocument();
//                addWarning(property.getWarnings());
//                addError(property.getErrors());
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
//        if(hasLink()){
//            Iterable<L> links = this.getLinks();
//            for(L link : links){
//                link.endDocument();
//                addWarning(link.getWarnings());
//                addError(link.getErrors());
//            }
//        }
//        if(hasForEach()){
//            for(FE forEach : forEachs){
//                forEach.endDocument();
//                addWarning(forEach.getWarnings());
//                addError(forEach.getErrors());
//            }
//        }
//        if(hasVariables()){
//            for(V var : variables){
//                var.endDocument();
//                addWarning(var.getWarnings());
//                addError(var.getErrors());
//            }
//        }
//    }
//
//    private void contextReference() {
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
//        setRefer(findContext(((XTPBody) body).getNodes()));
//    }
//
//
//    private C findContext(Set<N> nodes) {
//        for(N n : nodes){
//            if(n instanceof XTPContext){
//                if(n.getId().equals(getRefer().getId()))
//                    return (C) n;
//                else if( ((NCLContext) n).hasNode()){
//                    Set<N> cnodes = ((NCLContext) n).getNodes();
//                    C c = findContext(cnodes);
//                    if(c != null)
//                        return (C) c;
//                }
//            }
//            else if(n instanceof XTPSwitch){
//                if( ((XTPSwitch) n).hasNode()){
//                    Set<N> snodes = ((XTPSwitch) n).getNodes();
//                    C c = findContext(snodes);
//                    if(c != null)
//                        return (C) c;
//                }
//            }
//        }
//
//        addWarning("Could not find context with id: " + getRefer().getId());
//        return null;
//    }
//    public void searchNodes(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules, N nodeS ){
//        Iterable<N> nodes = ((XTPContext)nodeS).getNodes();
//        for(N node : nodes){
//            if(node instanceof XTPMedia)
//                ((XTPMedia)node).searchMedia(descriptors);
//            else if(node instanceof XTPContext)
//                ((XTPContext)node).searchContext(descriptors, connectors, rules);
//             else if (node instanceof XTPSwitch)
//                ((XTPSwitch)node).searchSwitch(descriptors, connectors, rules);
//
//              
//    }
//    }
//
//    public void searchContext(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules){
//        if(this.hasNode())
//        searchNodes(descriptors, connectors, rules, (N) this);
//
//        if(this.hasLink()){
//            Iterable<L> links = this.getLinks();
//            for(L link: links ){
//                ((XTPLink)link).searchLink(descriptors, connectors);
//            }
//        }
//
//        if(this.hasForEach()){
//            for(FE forEach: forEachs){
//                forEach.searchForEach(descriptors, connectors, rules);
//            }
//        }
//    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
