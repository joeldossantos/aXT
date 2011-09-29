

package br.uff.midiacom.axt.link;

import br.uff.midiacom.ana.link.NCLParam;
import org.xml.sax.XMLReader;
import AXT.XMLElement;
import AXT.XTPDoc;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.datatype.NCLParamInstance;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import org.xml.sax.Attributes;


public class XTPParam <C extends NCLConnectorParam, Con extends NCLCausalConnector, D extends NCLLayoutDescriptor> extends NCLParam {

    private String select;
    private XMLElement selectedElement;
    //construtores

    public XTPParam(NCLParamInstance paramType){
    super(paramType);
    this.select = null;
    }

      public XTPParam(NCLParamInstance paramType, XMLReader reader, XMLElement parent) throws NullPointerException {

        super(paramType);

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
        while(!(root instanceof XTPDoc)){
            root = root.getParent();
        }

        if(this.getSelectedInterfaceXLabel(select)!=null){
            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedInterfaceXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }
        if(this.getSelectedComponentXLabel(select)!=null){
            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedComponentXLabel(select));
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
                if(attributes.getLocalName(i).equals("name"))
                    setName((C) new NCLConnectorParam(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("select"))
                    setSelect(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("value"))
                    setValue(attributes.getValue(i));
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
        if(getName() != null);
            //nameReference();
    }

    public void paramSearch(){
        searchName();
    }

    private void searchName() {
        
        XMLElement link = this.getParent();
        
        while(!(link instanceof XTPLink)){
            link = (XMLElement) link.getParent();
                if(link == null){
                    addWarning("Could not find a parent link");
                    return;
                }
            
        }
        
        if(this.getParent() instanceof XTPLink){
            Con connector = (Con) ((XTPLink)(this.getParent())).getXType().getConnector();
            if(connector == null){
                addWarning("Could not find a connector");
                return;
            }
            else{
                Iterable<C> params = connector.getConnectorParams();

                for(C param : params){
                    if(param.getName().equals(getName().getName())){
                        setName(param);
                        return;
                    }
                }
            }
        }
        addWarning("Could not find connectorParam in connector with name: " + getName().getName());
    }

    @Override
    public String parse(int ident) {return null;}

   
    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}

}
