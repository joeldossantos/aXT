
package br.uff.midiacom.axt.connector;

import AXT.XMLElement;
import AXT.XTemplateDoc;
import AXT.XTemplateElement;
import AXT.XTemplateXLabeledElement;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.reuse.NCLImport;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;



/**
 *
 * @author flavia
 */
public class XTemplateConnector<C extends NCLCausalConnector, I extends NCLImport> extends XTemplateElement {

    private String xlabel;
    private String src;
    private int maxOccurs;
    private int minOccurs;
    private static final int unbounded = 1000000000;
    private C nclConnector;

    public XTemplateConnector(){}

     public XTemplateConnector(String xlabel){
        this.maxOccurs = unbounded;
        this.minOccurs = 0;
        this.setXLabel(xlabel);
    }

    public XTemplateConnector(int maxOccurs, int minOccurs, String src, String xlabel){
        this.maxOccurs = maxOccurs;
        this.minOccurs = minOccurs;
        this.src= src;
        this.setXLabel(xlabel);
    }

     public XTemplateConnector(XMLReader reader, XTemplateElement parent) {
        setReader(reader);
        setParent(parent);
        getReader().setContentHandler(this);
    }

    //métodos de acesso

    @Override
    public String getXLabel(){
        return this.xlabel;
    }

    @Override
    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }
    public void setSrc(String src){
        this.src = src;
    }

    public String getSrc(){
        return this.src;
    }

    public void setConnector(C connector){
        this.nclConnector = connector;
    }

    public C getConnector(){
        return this.nclConnector;
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

            if(localName.equals("connector")){
                cleanWarnings();
                cleanErrors();

                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("xlabel"))
                        setXLabel(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("maxOccurs")){

                        if(attributes.getValue(i).equals("unbounded"))
                            setMaxOccurs(unbounded);
                        else
                            setMaxOccurs(Integer.parseInt(attributes.getValue(i)));
                        }
                    else if(attributes.getLocalName(i).equals("minOccurs"))
                        setMinOccurs(Integer.parseInt(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("src")){
                            setSrc(attributes.getValue(i));

                        }
                        }

                }


    }

    @Override
    public void endDocument() {
        if(getParent() != null){

                //connectorReference();

            }
    }

    public void searchConnector(Iterable<C> connectors) {
        String[] uri = this.src.split("#",2);
        if(findConnector(connectors)!= null){
            setConnector(findConnector(connectors));
            return;
        }
        addWarning("Could not find descriptor in descriptorBase with id:"+uri[1]);

    }




    public C findConnector(Iterable<C> connectors) {
        String[] uri = this.src.split("#",2);
        for(C c : connectors){
                if(c.getId().equals(uri[1]))
                return c;
                }

            return null;
        }

    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
