/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.midiacom.axt.Media;

import br.uff.midiacom.axt.context.XTemplateContext;
import br.uff.midiacom.axt.Switch.XTemplateSwitch;
import AXT.XMLElement;
import AXT.XTemplateBody;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLInstanceType;
import br.uff.midiacom.ana.datatype.NCLMimeType;
import br.uff.midiacom.ana.datatype.NCLTime;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLArea;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLNode;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author flavia
 */
public class XTemplateMedia<A extends NCLArea, P extends NCLProperty, D extends NCLLayoutDescriptor,M extends NCLMedia,N extends NCLNode> extends NCLMedia{

    private String xlabel;
   

    public XTemplateMedia()throws NCLInvalidIdentifierException{
        super("unidentified");
    }

    public XTemplateMedia(String id, String xlabel) throws NCLInvalidIdentifierException{
        super(id);
        this.xlabel = xlabel;
    }

    public XTemplateMedia(XMLReader reader, XMLElement parent){
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

    

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("media")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("xlabel")){
                        setXLabel(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).equals("src")){
                         try{
                            setSrc(attributes.getValue(i));
                        }
                        catch(URISyntaxException ex){
                            setSrc(new NCLTime(attributes.getValue(i)));
                        }

                    }
                    else if(attributes.getLocalName(i).equals("type")){
                        for(NCLMimeType m : NCLMimeType.values()){
                            if(m.toString().equals(attributes.getValue(i)))
                                setType(m);
                        }
                    }
                    else if(attributes.getLocalName(i).equals("descriptor"))
                        setDescriptor((D) new NCLDescriptor(attributes.getValue(i)));
                    else if(attributes.getLocalName(i).equals("refer"))
                        setRefer((M) new XTemplateMedia(attributes.getValue(i),"unknown"));
                    else if(attributes.getLocalName(i).equals("instance")){
                        for(NCLInstanceType in : NCLInstanceType.values()){
                            if(in.toString().equals(attributes.getValue(i)))
                                setInstance(in);
                        }
                    }
                }
            }
            else if(localName.equals("area")){
            A child = createXTemplateArea();
                child.startElement(uri, localName, qName, attributes);
                addArea(child);
            }
            else if(localName.equals("property")){
                P child = createXTemplateProperty();
                child.startElement(uri, localName, qName, attributes);
                addProperty(child);
            }
        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public void endDocument(){
        if(getParent() != null){
            if(getDescriptor() != null);
                //descriptorReference();
            if(getRefer() != null)
                mediaReference();
        }

        if(hasArea()){
            Iterable<A> areas = this.getAreas();
            for(A area : areas){
                area.endDocument();
                addWarning(area.getWarnings());
                addError(area.getErrors());
            }
        }
        if(hasProperty()){
            Iterable<P> properties = this.getProperties();
            for(P property : properties){
                property.endDocument();
                addWarning(property.getWarnings());
                addError(property.getErrors());
            }
        }
    }

    private void mediaReference() {
        //Search for the interface inside the node
        XMLElement body = getParent();

        while(!(body instanceof XTemplateBody)){
            body = body.getParent();
            if(body == null){
                addWarning("Could not find a body");
                return;
            }
        }

        setRefer(findMedia(((XTemplateBody) body).getNodes()));
    }

    private M findMedia(Set<N> nodes) {
        for(N n : nodes){
            if(n instanceof XTemplateMedia){
                if(n.getId().equals(getRefer().getId()))
                    return (M) n;
            }
            else if(n instanceof XTemplateContext){
                if( ((XTemplateContext) n).hasNode()){
                    Set<N> cnodes = ((XTemplateContext) n).getNodes();
                    M m = findMedia(cnodes);
                    if(m != null)
                        return (M) m;
                }
            }
            else if(n instanceof XTemplateSwitch){
                if( ((XTemplateSwitch) n).hasNode()){
                    Set<N> snodes = ((XTemplateSwitch) n).getNodes();
                    M m = findMedia(snodes);
                    if(m != null)
                        return (M) m;
                }
            }
        }

        addWarning("Could not find media with id: " + getRefer().getId());
        return null;
    }

    public void searchMedia(Iterable<D> descriptors) {

        if(this.getDescriptor()!= null){
        for(D desc : descriptors){
            if(desc.getId().equals(getDescriptor().getId())){
                setDescriptor(desc);
                return;
            }
        }


        addWarning("Could not find descriptor in descriptorBase with id: " + getDescriptor().getId());
        }
    }
    @Override
    public String parse(int ident) {return null;}

    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}


   
    protected A createXTemplateArea(){
       //um construtor vazio foi adicionado a ana (NCLArea)
        return (A) new XTemplateArea(getReader(), this);
    }


    protected P createXTemplateProperty(){
        //um construtor vazio foi adicionadoa ana (NCLProperty)
        return (P) new XTemplateProperty(getReader(), this);
    }

}
