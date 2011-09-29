/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.midiacom.axt.importBase;

import AXT.XMLElement;
import AXT.XTPElement;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.NCLImportType;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author flavia
 */
public class XTPConnectorBase<I extends NCLImport, C extends NCLCausalConnector> extends XTPElement {

    private String id;
    private Set<I> imports = new TreeSet<I>();

    public XTPConnectorBase() {}

    public XTPConnectorBase(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    public void setId(String id){
        this.id=id;
    }

    public String getId(){
        return id;
    }

    public boolean addImportBase(I importBase) {
        if(imports.add(importBase)){
            //Se importBase existe, atribui este como seu parente
            if(importBase != null)
                importBase.setParent(this);

            return true;
        }
        return false;
    }



    public boolean removeImportBase(I importBase) {
        if(imports.remove(importBase)){
            //Se importBase existe, retira o seu parentesco
            if(importBase != null)
                importBase.setParent(null);

            return true;
        }
        return false;
    }



    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }



    public boolean hasImportBase() {
        return !imports.isEmpty();
    }



    public Iterable<I> getImportBases() {
        return imports;
    }

    public Iterable<C> getImportedConnectors(String uri) throws NCLParsingException {
            System.out.println(uri);
            NCLDoc doc = new NCLDoc();
            System.out.println("antes do load");
            File nclFile = new File(uri);
            doc.loadXML(nclFile, doc);

            System.out.println("depois do load");
            return doc.getHead().getConnectorBase().getCausalConnectors();

    }


    public Iterable<C> getAllConnectors() throws NCLParsingException{

            Set<C> connectors = new TreeSet<C>();
            for(I importBase : imports){
                for(C connector : getImportedConnectors(importBase.getDocumentURI().toString())){
                connectors.add(connector);
                }
        }
            return connectors;
        }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("connectorBase")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("importBase")){
                I child = createImportBase();
                child.startElement(uri, localName, qName, attributes);
                addImportBase(child);
            }

        }
        catch(/*NCLInvalidIdentifierException*/Exception ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasImportBase()){
            for(I imp : imports){
                imp.endDocument();
                addWarning(imp.getWarnings());
                addError(imp.getErrors());
            }
        }
        
    }

    public String parse(int ident) {return null;}

    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}

    protected I createImportBase() {
        return (I) new NCLImport(NCLImportType.BASE, getReader(), this);
    }
}
