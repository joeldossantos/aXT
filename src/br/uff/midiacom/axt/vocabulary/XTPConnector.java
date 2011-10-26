package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPXLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPConnectorPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPConnector<T extends XTPConnector, P extends XTPElement, I extends XMLElementImpl> extends XTPConnectorPrototype<T, P, I> implements XTPXLabeledElement<T, P> {

    
    public XTPConnector(String xlabel, String src) throws XMLException {
        super(xlabel, src);
    }

//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//
//            if(localName.equals("connector")){
//                cleanWarnings();
//                cleanErrors();
//
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("xlabel"))
//                        setXLabel(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("maxOccurs")){
//
//                        if(attributes.getValue(i).equals("unbounded"))
//                            setMaxOccurs(unbounded);
//                        else
//                            setMaxOccurs(Integer.parseInt(attributes.getValue(i)));
//                        }
//                    else if(attributes.getLocalName(i).equals("minOccurs"))
//                        setMinOccurs(Integer.parseInt(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("src")){
//                            setSrc(attributes.getValue(i));
//
//                        }
//                        }
//
//                }
//
//
//    }
//
//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//
//                //connectorReference();
//
//            }
//    }
//
//    public void searchConnector(Iterable<C> connectors) {
//        String[] uri = this.src.split("#",2);
//        if(findConnector(connectors)!= null){
//            setConnector(findConnector(connectors));
//            return;
//        }
//        addWarning("Could not find descriptor in descriptorBase with id:"+uri[1]);
//
//    }
//
//
//
//
//    public C findConnector(Iterable<C> connectors) {
//        String[] uri = this.src.split("#",2);
//        for(C c : connectors){
//                if(c.getId().equals(uri[1]))
//                return c;
//                }
//
//            return null;
//        }
}
