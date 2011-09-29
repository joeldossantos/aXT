/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT.Media;

import AXT.XMLElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLSample;
import br.uff.midiacom.ana.datatype.NCLTime;
import br.uff.midiacom.ana.interfaces.NCLArea;
import java.util.Vector;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;

/**
 *
 * @author flavia
 */
public class XTemplateArea extends NCLArea {

    private String xlabel;

    //construtores
    public XTemplateArea()throws NCLInvalidIdentifierException{
        super("unidentified");
    }

    public XTemplateArea(String id, String xlabel)throws NCLInvalidIdentifierException{
        super(id);
        this.xlabel = xlabel;
    }

    public XTemplateArea(XMLReader reader, XMLElement parent){

        super();
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }// verificar problema
   
    //metodos de acesso
    
    public String getXLabel(){
        return this.xlabel;
    }

    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("id"))
                    setId(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("xlabel")){
                    setXLabel(attributes.getValue(i));
                }
                else if(attributes.getLocalName(i).equals("coords")){
                    Vector<Integer>  coord = new Vector<Integer>();
                    String value = attributes.getValue(i);
                    while(value.contains(",")){
                        int index = value.indexOf(",");
                        coord.add(new Integer(value.substring(0, index)));
                        value = value.substring(index + 1);
                    }
                    coord.add(new Integer(value));
                    int[] a = new int[coord.size()];
                    for(int k = 0; k < coord.size(); k++)
                        a[k] = (int) coord.elementAt(k);
                    setCoords(a);
                }
                else if(attributes.getLocalName(i).equals("begin"))
                    setBegin(new NCLTime(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("end"))
                    setEnd(new NCLTime(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("text"))
                    setText(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("position"))
                    setPosition(new Integer(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("first"))
                    setFirst(new NCLSample(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("last"))
                    setLast(new NCLSample(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("label"))
                    setLabel(attributes.getValue(i));
            }
        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public String parse(int ident) {return null;}


    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}


}
