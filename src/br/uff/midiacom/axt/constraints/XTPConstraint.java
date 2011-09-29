

package br.uff.midiacom.axt.constraints;

import AXT.XMLElement;
import AXT.XTPDoc;
import AXT.XTPElement;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


public class XTPConstraint extends XTPElement{

    private String select;//tentar implementar como xpath
    private String description;
    private XMLElement selectedElement;

    //construtores

    public XTPConstraint(){
        this.select = null;
        this.description = null;
    }

    public XTPConstraint(String select, String description){
        this. select = select;
        this.description = description;
    }

     public XTPConstraint (XMLReader reader, XTPElement parent){

        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }

    //metodos de acessso
    public String getSelect(){
        return this.select;
    }

    public void setSelect(String select){
        this.select = select;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
