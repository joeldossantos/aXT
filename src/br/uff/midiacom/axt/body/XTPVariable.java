package br.uff.midiacom.axt.body;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.XTPVariablePrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPVariable<T extends XTPVariable, P extends XTPElement, I extends XMLElementImpl> extends XTPVariablePrototype<T, P, I> implements XTPElement<T, P> {

    
    public XTPVariable(String name, String select) throws XMLException {
        super(name, select);
    }
    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//         try{
//
//        if(localName.equals("variable")){
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("name"))
//                        setName(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("select"))
//                        setSelect(attributes.getValue(i));
//                }
//        }
//        }
//         catch(Exception ex){
//            addError(ex.getMessage());
//         }
//    }
    
    
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
