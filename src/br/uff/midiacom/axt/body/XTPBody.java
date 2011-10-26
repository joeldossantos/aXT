package br.uff.midiacom.axt.body;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.body.interfaces.XTPPort;
import br.uff.midiacom.axt.body.link.XTPLink;
import br.uff.midiacom.axt.body.node.XTPNode;
import br.uff.midiacom.axt.datatype.xtemplate.body.XTPBodyPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPBody<T extends XTPBody, P extends XTPElement, I extends XMLElementImpl, Ep extends XTPPort, En extends XTPNode, El extends XTPLink, Ev extends XTPVariable, Ef extends XTPForEach> extends XTPBodyPrototype<T, P, I, Ep, En, El, Ev, Ef> implements XTPElement<T, P> {

    
    public XTPBody () throws XMLException {
        super();
    }

//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//      try{
//        System.out.println("entrou no startElement d body");
//        if(localName.equals("body")){
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                }
//        }
//
//        else if(localName.equals("media")){
//                System.out.println("entrou no startElement d elemento media");
//                N child = createXTemplateMedia();
//
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//        }
//        else if(localName.equals("meta")){
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
//                System.out.println("entrou no startElement d elemento port");
//                Pt child = createXTemplatePort();
//                child.startElement(uri, localName, qName, attributes);
//                addPort(child);
//            }
//            else if(localName.equals("property")){
//                Pp child = createXTemplateProperty();
//                child.startElement(uri, localName, qName, attributes);
//                addProperty(child);
//            }
//            
//            else if(localName.equals("context")){
//                N child = createXTemplateContext();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("switch")){
//                N child = createXTemplateSwitch();
//                child.startElement(uri, localName, qName, attributes);
//                addNode(child);
//            }
//            else if(localName.equals("link")){
//                L child = createXTemplateLink();
//                child.startElement(uri, localName, qName, attributes);
//                addLink(child);
//            }
//            else if(localName.equals("variable")){
//                V child = createXTemplateVariable();
//                child.startElement(uri, localName, qName, attributes);
//                addVariable(child);
//            }
//            else if(localName.equals("for-each")){
//                FE child = createForEach();
//                child.startElement(uri, localName, qName, attributes);
//                addForEach(child);
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument(){
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
//        if(hasLink()){
//            Iterable<L> links = this.getLinks();
//            for(L link : links){
//                link.endDocument();
//                addWarning(link.getWarnings());
//                addError(link.getErrors());
//            }
//        }
//        if(hasNode()){
//            Iterable<N> nodes = this.getNodes();
//             for(N node : nodes){
//                node.endDocument();
//                addWarning(node.getWarnings());
//                addError(node.getErrors());
//            }
//        }
//        if(hasVariables()){
//             for(V variable : variables){
//                variable.endDocument();
//                addWarning(variable.getWarnings());
//                addError(variable.getErrors());
//            }
//        }
//        if(hasForEach()){
//             for(FE forEach : forEachs){
//                forEach.endDocument();
//                addWarning(forEach.getWarnings());
//                addError(forEach.getErrors());
//            }
//        }
//    }
//
//    public void searchNodes(Iterable<D> descriptors, Iterable<C> connectors, Iterable<R> rules, Iterable<N> nodes){
//       for(N node : nodes){
//            if(node instanceof XTPMedia)
//                ((XTPMedia)node).searchMedia(descriptors);
//            else if(node instanceof XTPContext)
//                ((XTPContext) node).searchContext(descriptors, connectors, rules);
//             else if (node instanceof XTPSwitch)
//               ((XTPSwitch) node).searchSwitch(descriptors, connectors, rules);
//
//
//    }
//}
//
//    public void searchForExternalReferences(Iterable<D> descriptors, Iterable<C> connectors,Iterable<R> rules){
//        Iterable<N> nodes = this.getNodes();
//        searchNodes(descriptors, connectors, rules, nodes);
//        Iterable<L> links = this.getLinks();
//        for(L  link : links ){
//            ((XTPLink)link).searchLink(descriptors, connectors);
//        }
//        for(FE forEach : forEachs){
//            forEach.searchForEach(descriptors, connectors, rules);
//        }
    
    
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
