package br.uff.midiacom.axt;

import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.axt.body.XTPBody;
import br.uff.midiacom.axt.constraints.XTPConstraints;
import br.uff.midiacom.axt.datatype.xtemplate.XTPDocPrototype;
import br.uff.midiacom.axt.head.XTPHead;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class XTPDoc<T extends XTPDoc, P extends XTPElement, I extends XTPElementImpl, Eh extends XTPHead, Ev extends XTPVocabulary, Eb extends XTPBody, Ec extends XTPConstraints>
        extends XTPDocPrototype<T, P, I, Eh, Ev, Eb, Ec> implements XTPElement<T, P> {

    
    public XTPDoc(String id) throws XMLException {
        super(id);
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<T, P>(this);
    }
    
    
    /**
     * Loads the objects structure representing an NCL document from an XML file.
     *
     * @param xmlFile
     *          file with the NCL document content.
     * @throws NCLParsingException
     *          if an error occur while parsing the document.
     */
    public void load(File xmlFile) throws XMLException {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.parse(xmlFile);
            ReferenceManager.getInstance().setBaseSrc(xmlFile.getParent());
            load(doc.getDocumentElement());
        }catch(SAXException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }catch(ParserConfigurationException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }catch(IOException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }
    }
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        Element el;
        
        try{
            // set the id (required)
            if(!(att_var = element.getAttribute("id")).isEmpty())
                setId(att_var);
            else
                throw new XMLException("Could not find id attribute.");
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("XTemplate" + aux + ":\n" + ex.getMessage());
        }
        
        // set the name
        if(!(att_var = element.getAttribute("name")).isEmpty())
            setName(att_var);
        
        // set the description
        if(!(att_var = element.getAttribute("description")).isEmpty())
            setDescription(att_var);
                
        try{
            // create the head
            el = (Element) element.getElementsByTagName("head").item(0);
            if(el != null){
                Eh inst = createHead();
                setHead(inst);
                inst.load(el);
            }
            // create the vocabulary
            el = (Element) element.getElementsByTagName("vocabulary").item(0);
            if(el != null){
                Ev inst = createVocabulary();
                setVocabulary(inst);
                inst.load(el);
            }
            // create the body
            el = (Element) element.getElementsByTagName("body").item(0);
            if(el != null){
                Eb inst = createBody();
                setBody(inst);
                inst.load(el);
            }
            // create the constraints
            el = (Element) element.getElementsByTagName("constraints").item(0);
            if(el != null){
                Ec inst = createConstraints();
                setConstraints(inst);
                inst.load(el);
            }
        }catch(XMLException ex){
            throw new XMLException("XTemplate > " + ex.getMessage());
        }
    }
    
    
    /**
     * Function to create the child element <i>head</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>head</i>.
     */
    protected Eh createHead() throws XMLException {
        return (Eh) new XTPHead();
    }
    
    
    /**
     * Function to create the child element <i>body</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>body</i>.
     */
    protected Eb createBody() throws XMLException {
        return (Eb) new XTPBody();
    }
    
    
    /**
     * Function to create the child element <i>constraints</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>constraints</i>.
     */
    protected Ec createConstraints() throws XMLException {
        return (Ec) new XTPConstraints();
    }
    
    
    /**
     * Function to create the child element <i>vocabulary</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>vocabulary</i>.
     */
    protected Ev createVocabulary() throws XMLException {
        return (Ev) new XTPVocabulary();
    }
}
