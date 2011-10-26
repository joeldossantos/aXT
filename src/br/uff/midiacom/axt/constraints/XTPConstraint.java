package br.uff.midiacom.axt.constraints;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.constraints.XTPConstraintPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPConstraint<T extends XTPConstraint, P extends XTPElement, I extends XMLElementImpl> extends XTPConstraintPrototype<T, P, I> implements XTPElement<T, P> {

    
    public XTPConstraint(String select) throws XMLException {
        super(select);
    }

    
//    public String getSelectedComponentXLabel(String select){
//                    String pxlabel;
//                    if(select.contains("@xlabel")){
//                        pxlabel = select.substring(select.indexOf('@')+1);
//
//
//                        String[] split =  pxlabel.split("'", 3);
//                        pxlabel = split[1];
//
//                        return pxlabel;
//                    }
//                    else{
//                    return pxlabel = "null";
//                    }
//
//
//                }
//     public String getSelectedInterfaceXLabel(String select){
//            String pxlabel;
//                    if(select.contains("@xlabel")){
//                        pxlabel = select.substring(select.indexOf('@')+1);
//                        if(pxlabel.contains("@xlabel")){
//                            pxlabel = pxlabel.substring(pxlabel.indexOf('@'));
//                            String[] split =  pxlabel.split("'", 3);
//                            pxlabel = split[1];
//                            return pxlabel;
//                        }
//                    }
//                    return null;
//    }
//    private void SelectedComponentReference(){
//        XMLElement root = getParent();
//        while(!(root instanceof XTPDoc)){
//            root = root.getParent();
//        }
//
//        if(this.getSelectedInterfaceXLabel(select)!=null){
//            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedInterfaceXLabel(select));
//            if(component!=null){
//                setSelectedElement(component);
//                return;
//            }
//        }
//        if(this.getSelectedComponentXLabel(select)!=null){
//            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedComponentXLabel(select));
//            if(component!=null){
//                setSelectedElement(component);
//                return;
//            }
//        }
//
//        addWarning("Could not find selected Element with xlabel"+getSelectedComponentXLabel(select)+"with interface"+getSelectedInterfaceXLabel(select));
//    }
    
    
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
