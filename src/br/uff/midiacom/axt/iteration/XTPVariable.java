
package br.uff.midiacom.axt.iteration;

import AXT.XMLElement;
import AXT.XTPElement;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


public class XTPVariable extends XTPElement {

    private String name;
    private String select;
    private String increment;

    //construtores

     public XTPVariable(){};

     public XTPVariable(String name, String select){
        this.name = name;
        this.select = select;
     }

     public XTPVariable(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);
        getReader().setContentHandler(this);
    }

    //metodos de acesso
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSelect(){
        return this.select;
    }

    public void setSelect(String select){
        this.select = select;
    }

    public String getIncrement(){
        return this.increment;
    }

    public void setIncrement(String increment){
        this.increment = increment;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
         try{

        if(localName.equals("variable")){
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("name"))
                        setName(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("select"))
                        setSelect(attributes.getValue(i));
                }
        }
        }
         catch(Exception ex){
            addError(ex.getMessage());
         }
    }

    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
