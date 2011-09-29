

package AXT;

import AXT.XTemplateValues.XTemplateNamespace;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.ana.rule.NCLRule;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Flavia
 */

public class XTemplateDoc<V extends XTemplateVocabulary, H extends XTemplateHead, 
        B extends XTemplateBody, C extends XTemplateConstraints, I extends NCLImport,
        Cn extends NCLCausalConnector, D extends NCLLayoutDescriptor, R extends NCLRule> extends XTemplateElement {



    private String id;
    private String name;
    private String description;
    private XTemplateNamespace xsi;
    private XTemplateNamespace xt;
    private XTemplateNamespace schemaLocation;
    
    private H head;
    private B body;
    private V vocabulary;
    
    private C constraints;
    
   public void setId(String id){
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setName(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setXsi(XTemplateNamespace xsi) {
        this.xsi = xsi;
    }

    public XTemplateNamespace getXsi() {
        return xsi;
    }

    public void setXt(XTemplateNamespace xt) {
        this.xt = xt;
    }

    public XTemplateNamespace getXt() {
        return xt;
    }

    public void setSchemaLocation(XTemplateNamespace schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

    public XTemplateNamespace getSchemaLocation() {
        return schemaLocation;
    }

    public void setHead(H head) {
        //Retira o parentesco do head atual
        if(this.head != null)
            this.head.setParent(null);

        this.head = head;
        //Se head existe, atribui este como seu parente
        if(this.head != null)
            this.head.setParent(this);
    }

    public H getHead() {
        return head;
    }

     public void setVocabulary(V vocabulary) {
        //Retira o parentesco do head atual
        if(this.vocabulary != null)
            this.vocabulary.setParent(null);

        this.vocabulary = vocabulary;
        //Se head existe, atribui este como seu parente
        if(this.vocabulary != null)
            this.vocabulary.setParent(this);
    }

    public V getVocabulary() {
        return vocabulary;
    }

     
    public void setBody(B body) {
        //Retira o parentesco do body atual
        if(this.body != null)
            this.body.setParent(null);

        this.body = body;
        //Se body existe, atribui este como seu parente
        if(this.body != null)
            this.body.setParent(this);
    }

    public B getBody(){
        return body;
    }

    public void setConstraints(C constraints) {
        //Retira o parentesco do body atual
        if(this.constraints != null)
            this.constraints.setParent(null);

        this.constraints = constraints;
        //Se body existe, atribui este como seu parente
        if(this.constraints != null)
            this.constraints.setParent(this);
    }

    public C getConstraints(){
        return constraints;
    }

    public void loadXML(String path) throws /*NCLParsingException*/ Exception {
        try{
            
            URI fileURI = new URI(path);
            
            setReader(XMLReaderFactory.createXMLReader());
            
            getReader().setContentHandler(this);
            
            getReader().setErrorHandler(new XTemplateParsingErrorHandler(getReader()));
            
            FileReader r = new FileReader(fileURI.toString());
            
            getReader().parse(new InputSource(r));

            externalReferences();
        }
        catch(/*URISyntaxException*/ Exception ex){
            throw new /*NCLParsingException*/Exception(ex.getMessage());
        }
        /*catch(SAXException ex){
            throw new NCLParsingException(ex.getMessage());
        }
        catch(FileNotFoundException ex){
            throw new NCLParsingException(ex.getMessage());
        }
        catch(IOException ex){
            throw new NCLParsingException(ex.getMessage());
        }*/
    }

    public void externalReferences(){
        try {
            
            Iterable<Cn> importedConnectors= new TreeSet<Cn>();
            importedConnectors = this.getHead().getConnectorBase().getAllConnectors();
            System.out.println("pegou connectors");

            Iterable<D> importedDescriptors= new TreeSet<D>();
            importedDescriptors = this.getHead().getDescriptorBase().getAllDescriptors();
            System.out.println("pegou descriptors");


            Iterable<R> importedRules = new TreeSet<R>();
            importedRules = this.getHead().getDescriptorBase().getAllRules();
            System.out.println("pegou rules");

            //atenção: adicionei cláusula catch para expressão genérica no método loadXML() e NCLDoc
            this.getVocabulary().searchForExternalReferences(importedDescriptors, importedConnectors);
            this.getBody().searchForExternalReferences(importedDescriptors, importedConnectors, importedRules);

        } catch (NCLParsingException ex) {
            System.out.println(ex.getMessage());
        }



    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("xtemplate")){
                
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("name"))
                        setName(attributes.getValue(i));
                        
                    else if(attributes.getLocalName(i).equals("description"))
                        setDescription(attributes.getValue(i));
                        
                }
                   /*if(!uri.equals("")){
                    for(XTemplateNamespace ns : XTemplateNamespace.values()){
                        if(ns.toString().equals(uri))
                            setXmlns(ns);
                    }
                }*/
            }
            else if(localName.equals("head")){
                System.out.println("achou head");
                setHead(createHead());
                
                getHead().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("vocabulary")){
                System.out.println("achou vocabulary");
                setVocabulary(createVocabulary());
                getVocabulary().startElement(uri, localName, qName, attributes);
            }
            
            else if(localName.equals("body")){
                setBody(createBody());
                getBody().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("constraints")){
                setConstraints(createConstraints());
                getConstraints().startElement(uri, localName, qName, attributes);
            }
        }
        catch(/*NCLInvalidIdentifierException*/ Exception ex){
            addError(ex.getMessage());
        }

    }

    @Override
    public void endDocument() {
        System.out.println("end document de xtemplateDoc");
        if(getHead() != null){
            getHead().endDocument();
            addWarning(getHead().getWarnings());
            addError(getHead().getErrors());
        }

        if(getVocabulary() != null){
            System.out.println("enddocument do vocabulary");
            getVocabulary().endDocument();
            addWarning(getVocabulary().getWarnings());
            addError(getVocabulary().getErrors());
        }
        if(getBody() != null){
            getBody().endDocument();
            addWarning(getBody().getWarnings());
            addError(getBody().getErrors());
        }
        if(getConstraints() != null){
            getConstraints().endDocument();
            addWarning(getConstraints().getWarnings());
            addError(getConstraints().getErrors());
        }
    }

    public String parse(int ident) {return null;}

    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        // Documento nao pode ser vazio
        if(getHead() == null && getBody() == null && getVocabulary()==null){
            addWarning("Documento NCL vazio.");
            valid = false;
        }


        if(getHead() != null){
            valid &= getHead().validate();
            addWarning(getHead().getWarnings());
            addError(getHead().getErrors());
        }
        if(getBody() != null){
            valid &= getBody().validate();
            addWarning(getBody().getWarnings());
            addError(getBody().getErrors());
        }

        return valid;
    }

    @Override
    public void addWarning(String warning) {}

    protected H createHead() {
        return (H) new XTemplateHead(getReader(), this);
    }

    protected V createVocabulary() {
        return (V) new XTemplateVocabulary(getReader(), this);
    }

    
    protected B createBody() {
        return (B) new XTemplateBody(getReader(), this);
    }

    protected C createConstraints() {
        return (C) new XTemplateConstraints(getReader(), this);
    }
     

}
