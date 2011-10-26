package br.uff.midiacom.axt.body.link;

import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPParamPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPParam<T extends XTPParam, P extends XTPElement, I extends XMLElementImpl, Ec extends NCLConnectorParam> extends XTPParamPrototype<T, P, I, Ec> implements XTPElement<T, P> {

    
    public XTPParam(NCLParamInstance paramType) throws XMLException{
        super(paramType);
    }

//        public String getSelectedComponentXLabel(String select){
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
//
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("name"))
//                    setName((C) new NCLConnectorParam(attributes.getValue(i)));
//                else if(attributes.getLocalName(i).equals("select"))
//                    setSelect(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("value"))
//                    setValue(attributes.getValue(i));
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument() {
//        if(getParent() == null)
//            return;
//        if(getSelect() != null)
//            SelectedComponentReference();
//        if(getName() != null);
//            //nameReference();
//    }
//    private void searchName() {
//        
//        XMLElement link = this.getParent();
//        
//        while(!(link instanceof XTPLink)){
//            link = (XMLElement) link.getParent();
//                if(link == null){
//                    addWarning("Could not find a parent link");
//                    return;
//                }
//            
//        }
//        
//        if(this.getParent() instanceof XTPLink){
//            Con connector = (Con) ((XTPLink)(this.getParent())).getXType().getConnector();
//            if(connector == null){
//                addWarning("Could not find a connector");
//                return;
//            }
//            else{
//                Iterable<C> params = connector.getConnectorParams();
//
//                for(C param : params){
//                    if(param.getName().equals(getName().getName())){
//                        setName(param);
//                        return;
//                    }
//                }
//            }
//        }
//        addWarning("Could not find connectorParam in connector with name: " + getName().getName());
//    }
}
