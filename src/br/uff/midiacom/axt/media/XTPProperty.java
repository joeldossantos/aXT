/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.midiacom.axt.Media;

import AXT.XMLElement;
import AXT.XTPDoc;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author flavia
 */
public class XTPProperty extends NCLProperty{

    private String xlabel;
    private String select;
    private XMLElement selectedElement;
    //construtores

    public XTPProperty(){}

    public XTPProperty(String name, String xlabel, String select) throws NCLInvalidIdentifierException{
        super(name);
        this.xlabel = xlabel;
        this.select = select;
    }

    public XTPProperty(XMLReader reader, XMLElement parent) {
        super();
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }

        
    //metodos de acesso

    public String getXLabel(){
        return this.xlabel;
    }

    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }

     public String getSelect(){
        return this.select;
    }

    public void setSelect(String select){
        this.select = select;
    }

    public XMLElement getSelectedElement(){
        return this.selectedElement;
    }

    public void setSelectedElement(XMLElement element){
        this.selectedElement = element;
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
   
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("name"))
                    setName(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("value"))
                    setValue(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("xlabel"))
                    setXLabel(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("select"))
                    setSelect(attributes.getValue(i));
            }
        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public void endDocument(){
        if(getSelect()!=null)
            SelectedComponentReference();
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
    public String parse(int ident) {return null;}
    
    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}

    
}
