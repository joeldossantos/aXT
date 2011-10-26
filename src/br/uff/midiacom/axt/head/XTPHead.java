package br.uff.midiacom.axt.head;

import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.head.XTPHeadPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPHead<T extends XTPHead, P extends XTPElement, I extends XMLElementImpl, Ee extends XTPExtends, Ed extends NCLDescriptorBase, Ec extends NCLConnectorBase> extends XTPHeadPrototype<T, P, I, Ee, Ed, Ec> implements XTPElement<T, P> {

    
    public XTPHead() throws XMLException {
        super();
    }
    
    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//       System.out.println("entrou no startElement d head");
//       if(localName.equals("head")){
//            cleanWarnings();
//            cleanErrors();
//        }
//
//        else if(localName.equals("descriptorBase")){
//            System.out.println("entrou no startElement d descriptor base");
//            setDescriptorBase(createDescriptorBase());
//            getDescriptorBase().startElement(uri, localName, qName, attributes);
//        }
//        else if(localName.equals("connectorBase")){
//            setConnectorBase(createConnectorBase());
//            getConnectorBase().startElement(uri, localName, qName, attributes);
//        }
//        else if(localName.equals("extends")){
//            E child = createExtend();
//            child.startElement(uri, localName, qName, attributes);
//            addExtend(child);
//        }
//        
//    }
//    @Override
//    public void endDocument() {
//        System.out.println("endDocument de xtemplateHead");
//        if(getDescriptorBase() != null){
//            getDescriptorBase().endDocument();
//            addWarning(getDescriptorBase().getWarnings());
//            addError(getDescriptorBase().getErrors());
//        }
//        if(getConnectorBase() != null){
//            getConnectorBase().endDocument();
//            addWarning(getConnectorBase().getWarnings());
//            addError(getConnectorBase().getErrors());
//        }
//        if(hasExtend()){
//            for(E extend : xtextends){
//                extend.endDocument();
//                addWarning(extend.getWarnings());
//                addError(extend.getErrors());
//            }
//        }
//
//        }
    
    
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
