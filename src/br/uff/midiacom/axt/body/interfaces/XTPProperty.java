package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPXLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPProperty<T extends XTPProperty, P extends XTPElement, I extends XMLElementImpl, Ei extends NCLInterface> extends XTPPropertyPrototype<T, P, I, Ei> implements XTPXLabeledElement<T, P> {

    
    public XTPProperty(NCLSystemVariable name, String xlabel) throws XMLException {
        super(name, xlabel);
    }
    
    
    public XTPProperty(String name, String xlabel) throws XMLException {
        super(name, xlabel);
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
//   
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("name"))
//                    setName(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("value"))
//                    setValue(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("xlabel"))
//                    setXLabel(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("select"))
//                    setSelect(attributes.getValue(i));
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument(){
//        if(getSelect()!=null)
//            SelectedComponentReference();
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
}
