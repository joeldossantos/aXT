/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT;

import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author Flavia
 */
public class XTPExtends<XT extends XTPDoc> extends XTPElement {
    
    private XT xtemplate;
    private boolean overwriteConstraints;

    public XTPExtends(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }

   public void setXTemplate(XT xtemplate){
        this.xtemplate = xtemplate;
   }

   public XT getXTemplate(){
        return xtemplate;
   }

   public void setOverwriteConstraints(boolean value){
        overwriteConstraints = value;
   }

   public boolean getOverwriteConstraints(){
        return overwriteConstraints;
   }


   public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("extends")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("overwriteConstraints")){
                        setOverwriteConstraints((attributes.getValue(i)).equals("true"));
                    }
                    else if(attributes.getLocalName(i).equals("xtemplate")){
                        //chamar metodo merge
                        //ou criar um contrutor com title

                    }
                }
            }

        }
        catch(/*NCLInvalidIdentifierException*/Exception ex){
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

    protected XT createXTemplate(){
        return (XT) new XTPDoc();
    }

    }
