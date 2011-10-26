package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPXLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPComponentPortPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPComponentPort<T extends XTPComponentPort, P extends XTPElement, I extends XMLElementImpl> extends XTPComponentPortPrototype<T, P, I> implements XTPXLabeledElement<T, P> {

    
    public XTPComponentPort(String xlabel) throws XMLException{
        super(xlabel);
    }




//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("port")){
//                System.out.println("entrou n start element do port");
//                cleanWarnings();
//                cleanErrors();
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
//                        }
//                    else if(attributes.getLocalName(i).equals("minOccurs")){
//                        
//                        setMinOccurs(Integer.parseInt(attributes.getValue(i)));}
//                }
//            }
//        }
//       catch(/*NCLInvalidIdentifierException*/Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument() {
//        if(getParent() == null)
//            return;
//
//       //escrever algo que faça sentido
//    }
    
    
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
