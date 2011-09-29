
package AXT.Component;

import AXT.XTemplateElement;
import AXT.XTemplateXLabeledElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;



/**
 *Este elemento define pontos de interface de um elemento component em XTemplate.
 * @author flavia
 */
public class ComponentPort extends XTemplateElement implements NCLInterface{

    private String xlabel;
    private static final int unbounded = 1000000000;
    private int maxOccurs;
    private int minOccurs;

    //construtores

    public ComponentPort(){
        this.maxOccurs = unbounded;
        this.minOccurs = 0;
        this.setXLabel(null);
    }

    public ComponentPort(int max, int min, String xlabel){
        this.maxOccurs = max;
        this.minOccurs = min;
        this.setXLabel(xlabel);
    }

    public ComponentPort(XMLReader reader, XTemplateElement parent) {
        setReader(reader);
        setParent(parent);
        getReader().setContentHandler(this);
    }

    //métodos de acesso


    public String getXLabel(){
        return this.xlabel;
    }

    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }

    public void setMaxOccurs(int max){
        this.maxOccurs = max;
    }

    public void setMinOccurs(int min){
        this.minOccurs = min;
    }

    public int getMaxOccurs(){
        return this.maxOccurs;
    }

    public int getMinOccurs(){
        return this.maxOccurs;
    }




    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("port")){
                System.out.println("entrou n start element do port");
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("xlabel")){
                        
                        setXLabel(attributes.getValue(i));
                        
                    }
                    else if(attributes.getLocalName(i).equals("maxOccurs")){
                       
                        if(attributes.getValue(i).equals("unbounded"))
                            setMaxOccurs(unbounded);
                        else
                            setMaxOccurs(Integer.parseInt(attributes.getValue(i)));
                        }
                    else if(attributes.getLocalName(i).equals("minOccurs")){
                        
                        setMinOccurs(Integer.parseInt(attributes.getValue(i)));}
                }
            }
        }
       catch(/*NCLInvalidIdentifierException*/Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public void endDocument() {
        if(getParent() == null)
            return;

       //escrever algo que faça sentido
    }

    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addWarning(String warning) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setId(String id) throws NCLInvalidIdentifierException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


   

   
    }

