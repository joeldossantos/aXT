package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPBindRulePrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPBindRule<T extends XTPBindRule, P extends XTPElement, I extends XMLElementImpl, En extends NCLNode, Er extends NCLTestRule> extends XTPBindRulePrototype<T, P, I, En, Er> implements XTPElement<T, P> {

    
    public XTPBindRule() throws XMLException {
        super();
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
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("select"))
//                    setSelect(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("rule"))
//                    setRulePath(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("constituent"))
//                    setConstituent((N) new XTPContext(attributes.getValue(i)));
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
//
//    @Override
//     public void endDocument() {
//        if(getParent() == null)
//            return;
//
//        if(getSelect() != null)
//            SelectedComponentReference();
//        if(getConstituent() != null)
//            constituentReference();
//
//        if(getRule() != null);
//            //ruleReference();
//    }
//private void constituentReference() {
//
//        XMLElement root = this.getParent();
//         while(!(root instanceof XTPDoc)){
//            root = root.getParent();
//         }
//         
//        if(((XTPDoc) root).getVocabulary()!=null){
//            
//         
//            Iterable<Cp> components = (((XTPVocabulary)((XTPDoc) root).getVocabulary())).getComponents();
//            if(findConstituent(components)!=null){
//                setConstituent(findConstituent(components));
//                return;
//             }
//
//        }
//        
//        else{
//        Set<N> nodes = ((XTPSwitch) getParent()).getNodes();
//
//        for(N node : nodes){
//            if(node.getId().equals(getConstituent().getId())){
//                setConstituent(node);
//                return;
//            }
//            else{
//            if(node instanceof XTPMedia){
//                    if(((XTPMedia) node).getXLabel().equals(getConstituent().getId())){
//                        setConstituent(node);
//                        return;
//                    }
//
//                }
//                else if(node instanceof XTPContext){
//                    if(((XTPContext) node).getXLabel().equals(getConstituent().getId())){
//                        setConstituent(node);
//                        return;
//                    }
//                }
//                else if(node instanceof XTPSwitch){
//                    if(((XTPSwitch) node).getXLabel().equals(getConstituent().getId())){
//                        setConstituent(node);
//                        return;
//                    }
//                }
//
//        }
//        }
//        }
//        addWarning("Could not find node in switch with id: " + getConstituent().getId());
//
//    }
//
//    private Cp findConstituent(Iterable<Cp> components){
//
//        for(Cp component : components){
//            if(this.getConstituent().getId().equals(component.getXLabel())){
//                
//                return component;
//            }
//            else if(component.hasComponent()){
//                List<Cp> ccomp = component.getComponents();
//                Cp cp = findConstituent(ccomp);
//                 if(cp != null)
//                        return (Cp) cp;
//            }
//        }
//        
//        return null;
//    }
//
//
//    public void searchBindRule(Iterable<R> rules) {
//        
//        if(rules == null)
//            return;
//        String[] uri = rulePath.split("#",2);
//        for(R rul : rules){
//            if(rul.getId().equals(uri[1])){
//                setRule(rul);
//                return;
//            }
//        }
//        //@todo: regras internas a regras compostas podem ser utilizadas?
//
//        addWarning("Could not find rule in ruleBase with id: " + getRule().getId());
//    }
}
