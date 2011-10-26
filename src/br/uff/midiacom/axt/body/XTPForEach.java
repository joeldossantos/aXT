package br.uff.midiacom.axt.body;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.body.link.XTPLink;
import br.uff.midiacom.axt.body.node.XTPSwitch;
import br.uff.midiacom.axt.datatype.xtemplate.body.XTPForEachPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPForEach<T extends XTPForEach, P extends XTPElement, I extends XMLElementImpl, Ei extends NCLInterface, Es extends XTPSwitch, El extends XTPLink, Ev extends XTPVariable> extends XTPForEachPrototype<T, P, I, Ei, Es, El, Ev> implements XTPElement<T, P> {

    
    public XTPForEach(String select) throws XMLException {
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
//
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//      try{
//        System.out.println("entrou no start element do foreach");
//        if(localName.equals("for-each")){
//            
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("select"))
//                        setSelect(attributes.getValue(i));
//                }
//        }
//
//        else if(localName.equals("area")){
//                System.out.println("entrou no startElement d elemento media");
//                A child = createArea();
//                child.startElement(uri, localName, qName, attributes);
//                addXTArea(child);
//        }
//        else if(localName.equals("property")){
//                Pp child = createProperty();
//                child.startElement(uri, localName, qName, attributes);
//                addXTProperty(child);
//            }
//            else if(localName.equals("port")){
//                Pt child = createPort();
//                child.startElement(uri, localName, qName, attributes);
//                addXTPort(child);
//            }
//            else if(localName.equals("switch")){
//
//                S child = createSwitch();
//                child.startElement(uri, localName, qName, attributes);
//                addXTSwitch(child);
//            }
//            else if(localName.equals("link")){
//                L child = createLink();
//                child.startElement(uri, localName, qName, attributes);
//                addXTLink(child);
//            }
//
//            else if(localName.equals("variable")){
//                V child = createVariable();
//                child.startElement(uri, localName, qName, attributes);
//                addVariable(child);
//            }
//
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void endDocument(){
//        if(getSelect() != null)
//            SelectedComponentReference();
//        if(hasVariable()){
//            for(V var : variables){
//                var.endDocument();
//                addWarning(var.getWarnings());
//                addError(var.getErrors());
//            }
//            //variableReference();
//        }
//        if(hasXTArea()){
//
//            for(A area : areas){
//                area.endDocument();
//                addWarning(area.getWarnings());
//                addError(area.getErrors());
//            }
//        }
//        if(hasXTPort()){
//            for(Pt port : ports){
//                port.endDocument();
//                addWarning(port.getWarnings());
//                addError(port.getErrors());
//            }
//        }
//        if(hasXTProperty()){
//            for(Pp property : properties){
//                property.endDocument();
//                addWarning(property.getWarnings());
//                addError(property.getErrors());
//            }
//        }
//        if(hasXTLink()){
//            for(L link : links){
//                link.endDocument();
//                addWarning(link.getWarnings());
//                addError(link.getErrors());
//            }
//        }
//        if(hasXTSwitch()){
//
//             for(S xtswitch : switches){
//                xtswitch.endDocument();
//                addWarning(xtswitch.getWarnings());
//                addError(xtswitch.getErrors());
//            }
//        }
//    }
//
//    private void variableReference(){
//        if(getParent() == null){
//            addWarning("Could not find a variable parent");
//            return;
//        }
//
//        Iterable<V> exVariables = null;
//
//        if(getParent() instanceof XTPBody)
//            exVariables = ((XTPBody) getParent()).getVariables();
//        else if(getParent() instanceof XTPContext)
//            exVariables = ((XTPContext) getParent()).getNodes();
//
//        if(exVariables!=null){
//            String name1;
//            String name2;
//           for(V inVariable : variables){
//               name1 = inVariable.getName();
//                for(V exVariable : exVariables){
//                    name2 = exVariable.getName();
//                    if(name1.equals(name2)){
//                        exVariable.setIncrement(inVariable.getSelect());
//                        boolean remove = removeVariable(inVariable);
//                        boolean add = addVariable(exVariable);
//                        return;
//                    }
//                    else{
//                        addWarning("Could not find variable"+inVariable.getName());
//                    }
//                }
//            }
//        }
//    }
//
//    public void searchForEach(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules){
//        if(this.hasXTSwitch()){
//            for(S xtswitch: switches){
//               ((XTPSwitch) xtswitch).searchSwitch(descriptors, connectors, rules);
//            }
//        }
//
//
//        if(this.hasXTLink()){
//
//            for(L link: links ){
//                ((XTPLink)link).searchLink(descriptors, connectors);
//            }
//        }
//    }
}
