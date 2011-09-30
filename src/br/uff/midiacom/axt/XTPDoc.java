package br.uff.midiacom.axt;

import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.axt.constraints.XTPConstraints;
import AXT.XTPValues.XTemplateNamespace;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.axt.datatype.OptionalStringType;
import br.uff.midiacom.axt.datatype.RequiredStringType;
import java.io.FileReader;
import java.net.URI;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Flavia
 */

public class XTPDoc<H extends XTPHead, V extends XTPVocabulary,B extends XTPBody, C extends XTPConstraints> extends XTPElement {

    private RequiredStringType id;
    private OptionalStringType name;
    private OptionalStringType description;
    private H head;
    private B body;
    private V vocabulary;
    private C constraints;
    
    
    public XTPDoc(String id) throws NullPointerException, IllegalArgumentException {
        setId(id);
    }
    
    
    public void setId(String id) throws NullPointerException, IllegalArgumentException {
        this.id = new RequiredStringType(id);
    }

    
    public String getId() {
        return id.getValue();
    }


    public void setName(String name) throws IllegalArgumentException {
        this.name = new OptionalStringType(name);
    }


    public String getName() {
        return name.getValue();
    }


    public void setDescription(String description) throws IllegalArgumentException {
        this.description = new OptionalStringType(description);
    }


    public String getDescription() {
        return description.getValue();
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
            
            getReader().setErrorHandler(new XTPParsingErrorHandler(getReader()));
            
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
        return (H) new XTPHead(getReader(), this);
    }

    protected V createVocabulary() {
        return (V) new XTPVocabulary(getReader(), this);
    }

    
    protected B createBody() {
        return (B) new XTPBody(getReader(), this);
    }

    protected C createConstraints() {
        return (C) new XTPConstraints(getReader(), this);
    }
     

}