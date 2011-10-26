package br.uff.midiacom.axt;

import br.uff.midiacom.axt.body.XTPBody;
import br.uff.midiacom.axt.constraints.XTPConstraints;
import br.uff.midiacom.axt.datatype.xtemplate.XTPDocPrototype;
import br.uff.midiacom.axt.head.XTPHead;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;


public class XTPDoc<T extends XTPDoc, P extends XTPElement, I extends XMLElementImpl, Eh extends XTPHead, Ev extends XTPVocabulary, Eb extends XTPBody, Ec extends XTPConstraints> extends XTPDocPrototype<T, P, I, Eh, Ev, Eb, Ec> implements XTPElement<T, P> {

    
    public XTPDoc(String id) throws XMLException {
        super(id);
    }

//    public void externalReferences(){
//        try {
//            
//            Iterable<Cn> importedConnectors= new TreeSet<Cn>();
//            importedConnectors = this.getHead().getConnectorBase().getAllConnectors();
//            System.out.println("pegou connectors");
//
//            Iterable<D> importedDescriptors= new TreeSet<D>();
//            importedDescriptors = this.getHead().getDescriptorBase().getAllDescriptors();
//            System.out.println("pegou descriptors");
//
//
//            Iterable<R> importedRules = new TreeSet<R>();
//            importedRules = this.getHead().getDescriptorBase().getAllRules();
//            System.out.println("pegou rules");
//
//            //atenção: adicionei cláusula catch para expressão genérica no método loadXML() e NCLDoc
//            this.getVocabulary().searchForExternalReferences(importedDescriptors, importedConnectors);
//            this.getBody().searchForExternalReferences(importedDescriptors, importedConnectors, importedRules);
//
//        } catch (NCLParsingException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//
//
//    }
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("xtemplate")){
//                
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("name"))
//                        setName(attributes.getValue(i));
//                        
//                    else if(attributes.getLocalName(i).equals("description"))
//                        setDescription(attributes.getValue(i));
//                        
//                }
//                   /*if(!uri.equals("")){
//                    for(XTemplateNamespace ns : XTemplateNamespace.values()){
//                        if(ns.toString().equals(uri))
//                            setXmlns(ns);
//                    }
//                }*/
//            }
//            else if(localName.equals("head")){
//                System.out.println("achou head");
//                setHead(createHead());
//                
//                getHead().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("vocabulary")){
//                System.out.println("achou vocabulary");
//                setVocabulary(createVocabulary());
//                getVocabulary().startElement(uri, localName, qName, attributes);
//            }
//            
//            else if(localName.equals("body")){
//                setBody(createBody());
//                getBody().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("constraints")){
//                setConstraints(createConstraints());
//                getConstraints().startElement(uri, localName, qName, attributes);
//            }
//        }
//        catch(/*NCLInvalidIdentifierException*/ Exception ex){
//            addError(ex.getMessage());
//        }
//
//    }
//
//    @Override
//    public void endDocument() {
//        System.out.println("end document de xtemplateDoc");
//        if(getHead() != null){
//            getHead().endDocument();
//            addWarning(getHead().getWarnings());
//            addError(getHead().getErrors());
//        }
//
//        if(getVocabulary() != null){
//            System.out.println("enddocument do vocabulary");
//            getVocabulary().endDocument();
//            addWarning(getVocabulary().getWarnings());
//            addError(getVocabulary().getErrors());
//        }
//        if(getBody() != null){
//            getBody().endDocument();
//            addWarning(getBody().getWarnings());
//            addError(getBody().getErrors());
//        }
//        if(getConstraints() != null){
//            getConstraints().endDocument();
//            addWarning(getConstraints().getWarnings());
//            addError(getConstraints().getErrors());
//        }
//    }
}
