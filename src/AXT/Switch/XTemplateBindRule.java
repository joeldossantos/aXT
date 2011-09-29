

package AXT.Switch;

import AXT.Component.XTemplateComponent;
import AXT.Context.XTemplateContext;
import AXT.Media.XTemplateMedia;
import AXT.XMLElement;
import AXT.XTemplateDoc;
import AXT.XTemplateVocabulary;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitchBindRule;
import br.uff.midiacom.ana.rule.NCLRule;
import java.util.List;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


public class XTemplateBindRule<R extends NCLRule, N extends NCLNode, Cp extends XTemplateComponent> extends NCLSwitchBindRule{

    private String select;
    private String rulePath;
    private XMLElement selectedElement;

    public XTemplateBindRule(){}

    public XTemplateBindRule(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    //metodos de acesso

    public String getSelect(){
        return this.select;
    }

    public void setSelect(String select){
        this.select = select;
    }

    public String getRulePath(){
        return this.rulePath;
    }

    public void setRulePath(String rulePath){
        this.rulePath = rulePath;
    }

    public String getSelectedComponentXLabel(String select){
                    String pxlabel;
                    if(select.contains("@xlabel")){
                        pxlabel = select.substring(select.indexOf('@')+1);


                        String[] split =  pxlabel.split("'", 3);
                        pxlabel = split[1];

                        return pxlabel;
                    }
                    else{
                    return pxlabel = "null";
                    }


                }
     public String getSelectedInterfaceXLabel(String select){
            String pxlabel;
                    if(select.contains("@xlabel")){
                        pxlabel = select.substring(select.indexOf('@')+1);
                        if(pxlabel.contains("@xlabel")){
                            pxlabel = pxlabel.substring(pxlabel.indexOf('@'));
                            String[] split =  pxlabel.split("'", 3);
                            pxlabel = split[1];
                            return pxlabel;
                        }
                    }
                    return null;
    }
    public XMLElement getSelectedElement(){
        return this.selectedElement;
    }

    public void setSelectedElement(XMLElement element){
        this.selectedElement = element;
    }
    private void SelectedComponentReference(){
        XMLElement root = getParent();
        while(!(root instanceof XTemplateDoc)){
            root = root.getParent();
        }

        if(this.getSelectedInterfaceXLabel(select)!=null){
            XMLElement component = ((XTemplateDoc)root).getVocabulary().findComponent(getSelectedInterfaceXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }
        if(this.getSelectedComponentXLabel(select)!=null){
            XMLElement component = ((XTemplateDoc)root).getVocabulary().findComponent(getSelectedComponentXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }

        addWarning("Could not find selected Element with xlabel"+getSelectedComponentXLabel(select)+"with interface"+getSelectedInterfaceXLabel(select));
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("select"))
                    setSelect(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("rule"))
                    setRulePath(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("constituent"))
                    setConstituent((N) new XTemplateContext(attributes.getValue(i)));
            }
        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
     public void endDocument() {
        if(getParent() == null)
            return;

        if(getSelect() != null)
            SelectedComponentReference();
        if(getConstituent() != null)
            constituentReference();

        if(getRule() != null);
            //ruleReference();
    }


    


    private void constituentReference() {

        XMLElement root = this.getParent();
         while(!(root instanceof XTemplateDoc)){
            root = root.getParent();
         }
         
        if(((XTemplateDoc) root).getVocabulary()!=null){
            
         
            Iterable<Cp> components = (((XTemplateVocabulary)((XTemplateDoc) root).getVocabulary())).getComponents();
            if(findConstituent(components)!=null){
                setConstituent(findConstituent(components));
                return;
             }

        }
        
        else{
        Set<N> nodes = ((XTemplateSwitch) getParent()).getNodes();

        for(N node : nodes){
            if(node.getId().equals(getConstituent().getId())){
                setConstituent(node);
                return;
            }
            else{
            if(node instanceof XTemplateMedia){
                    if(((XTemplateMedia) node).getXLabel().equals(getConstituent().getId())){
                        setConstituent(node);
                        return;
                    }

                }
                else if(node instanceof XTemplateContext){
                    if(((XTemplateContext) node).getXLabel().equals(getConstituent().getId())){
                        setConstituent(node);
                        return;
                    }
                }
                else if(node instanceof XTemplateSwitch){
                    if(((XTemplateSwitch) node).getXLabel().equals(getConstituent().getId())){
                        setConstituent(node);
                        return;
                    }
                }

        }
        }
        }
        addWarning("Could not find node in switch with id: " + getConstituent().getId());

    }

    private Cp findConstituent(Iterable<Cp> components){

        for(Cp component : components){
            if(this.getConstituent().getId().equals(component.getXLabel())){
                
                return component;
            }
            else if(component.hasComponent()){
                List<Cp> ccomp = component.getComponents();
                Cp cp = findConstituent(ccomp);
                 if(cp != null)
                        return (Cp) cp;
            }
        }
        
        return null;
    }


    public void searchBindRule(Iterable<R> rules) {
        
        if(rules == null)
            return;
        String[] uri = rulePath.split("#",2);
        for(R rul : rules){
            if(rul.getId().equals(uri[1])){
                setRule(rul);
                return;
            }
        }
        //@todo: regras internas a regras compostas podem ser utilizadas?

        addWarning("Could not find rule in ruleBase with id: " + getRule().getId());
    }

    @Override
    public String parse(int ident) {return null;}

    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}

}
