package br.uff.midiacom.axt.importBase;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.NCLImportType;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.ana.rule.NCLRule;
import br.uff.midiacom.axt.XTPElement;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import java.io.File;

/**
 *
 * @author flavia
 */
public class XTPDescriptorBase<I extends NCLImport, D extends NCLLayoutDescriptor, R extends NCLRule> extends XTPElement {

    private String id;
    private Set<I> imports = new TreeSet<I>();

    public XTPDescriptorBase() {}

    public XTPDescriptorBase(XMLReader reader, XMLElement parent) {
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

    public boolean hasImportBase(String alias){
        for(I importBase: imports){
            if(importBase.getAlias().equals(alias)){
                    return true;
            }
        }
          return false;
    }


    public boolean hasImportBase() {
        return !imports.isEmpty();
    }



    public Set<I> getImportBases() {
        return imports;
    }


   public NCLHead getImportedHead(String uri) throws NCLParsingException {
            System.out.println(uri);
            NCLDoc doc = new NCLDoc();
            System.out.println("antes do load");
            File nclFile = new File(uri);
            doc.loadXML(nclFile, doc);

            System.out.println("depois do load");
            
            return doc.getHead();

    }

    public Iterable<D> getAllDescriptors() throws NCLParsingException{

            Set<D> descriptors = new TreeSet<D>();
            for(I importBase : imports){
                Iterable<D> importedDescriptors = getImportedHead(importBase.getDocumentURI()).getDescriptorBase().getDescriptors();
                for(D descriptor : importedDescriptors ){
                descriptors.add(descriptor);
                }
        }
            return descriptors;
        }     
    
    public Iterable<R> getAllRules() throws NCLParsingException{
        Set<R> rules = new TreeSet<R>();
            for(I importBase : imports){
                Iterable<R> importedRules = getImportedHead(importBase.getDocumentURI()).getRuleBase().getRules();
                for(R rule : importedRules ){
                rules.add(rule);
                }
        }
        return rules;
        }

 
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("descriptorBase")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                        System.out.println(getId());
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
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected I createImportBase() {
        return (I) new NCLImport(NCLImportType.BASE, getReader(), this);
    }

}
