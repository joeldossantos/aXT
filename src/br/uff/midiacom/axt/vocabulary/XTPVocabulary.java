package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPVocabulary<T extends XTPVocabulary, P extends XTPElement, I extends XMLElementImpl, Ecp extends XTPComponent, Ecc extends XTPConnector> extends XTPVocabularyPrototype<T, P, I, Ecp, Ecc> implements XTPElement<T, P> {

    
    public XTPVocabulary() throws XMLException {
        super();
    }

//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("vocabulary")){
//            System.out.println("entrou no start element d vocabulary");
//            cleanWarnings();
//            cleanErrors();
//        }
//        else if(localName.equals("component")){
//                System.out.println("achou o component");
//                Cp child = createComponent();
//                child.startElement(uri, localName, qName, attributes);
//                addComponent(child);
//        }
//        else if(localName.equals("connector")){
//                Conn child = createConnector();
//                child.startElement(uri, localName, qName, attributes);
//                addConnector(child);
//        }
//        }
//
//        catch(/*NCLInvalidIdentifierException*/Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument() {
//            System.out.println("entrou no endDocument do vocabulary");
//            if(hasComponent()){
//            for(Cp component : components){
//                component.endDocument();
//                addWarning(component.getWarnings());
//                addError(component.getErrors());
//            }
//        }
//        if(hasConnector()){
//            for(Conn xtconnector : xtconnectors){
//                xtconnector.endDocument();
//                addWarning(xtconnector.getWarnings());
//                addError(xtconnector.getErrors());
//            }
//
//
//    }
//    }
//
//    public void searchForExternalReferences(Iterable<D> descriptors, Iterable<C> connectors){
//        for(Cp component : components){
//            component.searchDescriptor(descriptors);
//        }
//        for(Conn connector : xtconnectors ){
//            connector.searchConnector(connectors);
//        }
//    }
}
