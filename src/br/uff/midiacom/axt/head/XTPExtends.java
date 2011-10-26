package br.uff.midiacom.axt.head;

import br.uff.midiacom.axt.XTPDoc;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.head.XTPExtendsPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPExtends<T extends XTPExtends, P extends XTPElement, I extends XMLElementImpl, Ed extends XTPDoc> extends XTPExtendsPrototype<T, P, I, Ed> implements XTPElement<T, P> {

    
    public XTPExtends(Ed xtemplate) throws XMLException {
        super(xtemplate);
    }


//   public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("extends")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("overwriteConstraints")){
//                        setOverwriteConstraints((attributes.getValue(i)).equals("true"));
//                    }
//                    else if(attributes.getLocalName(i).equals("xtemplate")){
//                        //chamar metodo merge
//                        //ou criar um contrutor com title
//
//                    }
//                }
//            }
//
//        }
//        catch(/*NCLInvalidIdentifierException*/Exception ex){
//            addError(ex.getMessage());
//        }
//    }
}
