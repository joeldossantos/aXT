/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT;

import br.uff.midiacom.axt.importBase.XTemplateConnectorBase;
import br.uff.midiacom.axt.importBase.XTemplateDescriptorBase;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;

/**
 *
 * @author Flavia
 */
public class XTemplateHead<DB extends XTemplateDescriptorBase, CB extends XTemplateConnectorBase, E extends XTemplateExtends> extends XTemplateElement {

    private DB descriptorBase;
    private CB connectorBase;
    private Set<E> xtextends = new TreeSet<E>();

     public XTemplateHead(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }

     public void setDescriptorBase(DB descriptorBase) {
        //Retira o parentesco do descriptorBase atual
        if(this.descriptorBase != null)
            this.descriptorBase.setParent(null);

        this.descriptorBase = descriptorBase;
        //Se descriptorBase existe, atribui este como seu parente
        if(this.descriptorBase != null)
            this.descriptorBase.setParent(this);
    }



    public DB getDescriptorBase() {
        return descriptorBase;
    }


    public void setConnectorBase(CB connectorBase) {
        //Retira o parentesco do connectorBase atual
        if(this.connectorBase != null)
            this.connectorBase.setParent(null);

        this.connectorBase = connectorBase;
        //Se connectorBase existe, atribui este como seu parente
        if(this.connectorBase != null)
            this.connectorBase.setParent(this);
    }



    public CB getConnectorBase() {
        return connectorBase;
    }

    public boolean addExtend(E xtextend) {
        if(xtextends.add(xtextend)){
            //Se meta existe, atribui este como seu parente
            if(xtextends != null)
                xtextend.setParent(this);

            return true;
        }
        return false;
    }



    public boolean removeExtend(E extend) {
        if(xtextends.remove(extend)){
            //Se meta existe, retira o seu parentesco
            if(extend != null)
                extend.setParent(null);

            return true;
        }
        return false;
    }



    public boolean hasExtend(E extend) {
        return xtextends.contains(extend);
    }


    public boolean hasExtend() {
        return !xtextends.isEmpty();
    }



    public Iterable<E> getExtends() {
        return xtextends;
    }



    //metodos do parse



   @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
       System.out.println("entrou no startElement d head");
       if(localName.equals("head")){
            cleanWarnings();
            cleanErrors();
        }

        else if(localName.equals("descriptorBase")){
            System.out.println("entrou no startElement d descriptor base");
            setDescriptorBase(createDescriptorBase());
            getDescriptorBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("connectorBase")){
            setConnectorBase(createConnectorBase());
            getConnectorBase().startElement(uri, localName, qName, attributes);
        }
        else if(localName.equals("extends")){
            E child = createExtend();
            child.startElement(uri, localName, qName, attributes);
            addExtend(child);
        }
        
    }
    @Override
    public void endDocument() {
        System.out.println("endDocument de xtemplateHead");
        if(getDescriptorBase() != null){
            getDescriptorBase().endDocument();
            addWarning(getDescriptorBase().getWarnings());
            addError(getDescriptorBase().getErrors());
        }
        if(getConnectorBase() != null){
            getConnectorBase().endDocument();
            addWarning(getConnectorBase().getWarnings());
            addError(getConnectorBase().getErrors());
        }
        if(hasExtend()){
            for(E extend : xtextends){
                extend.endDocument();
                addWarning(extend.getWarnings());
                addError(extend.getErrors());
            }
        }

        }


    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     protected DB createDescriptorBase() {
        return (DB) new XTemplateDescriptorBase(getReader(), this);
    }



    protected CB createConnectorBase() {
        return (CB) new XTemplateConnectorBase(getReader(), this);
    }

     protected E createExtend() {
        return (E) new XTemplateExtends(getReader(), this);
    }






}

    