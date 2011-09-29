/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT;

import br.uff.midiacom.axt.context.XTPContext;
import br.uff.midiacom.axt.iteration.XTPForEach;
import br.uff.midiacom.axt.iteration.XTPVariable;
import br.uff.midiacom.axt.link.XTPLink;
import br.uff.midiacom.axt.Media.XTPMedia;
import br.uff.midiacom.axt.Media.XTPProperty;
import br.uff.midiacom.axt.port.XTPPort;
import br.uff.midiacom.axt.Switch.XTPSwitch;
import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.rule.NCLRule;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author Flavia
 */
public class XTPBody<FE extends XTPForEach, V extends XTPVariable, N extends NCLNode, Pt extends NCLPort,
        M extends NCLMeta, MT extends NCLMetadata, Pp extends NCLProperty, L extends NCLLink,
        D extends NCLLayoutDescriptor, C extends NCLCausalConnector, R extends NCLRule>  extends NCLBody {

    

    private Set<FE> forEachs = new TreeSet<FE>();
    private Set<V> variables = new TreeSet<V>();
    
    public XTPBody (){};

    public XTPBody(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }

    

    public boolean addForEach(FE forEach) {
        if(forEachs.add(forEach)){

            if(forEach != null)
                forEach.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeForEach(FE forEach) {
        if(forEachs.remove(forEach)){

            if(forEach != null)
                forEach.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasForEach(FE forEach) {
        return forEachs.contains(forEach);
    }


    public boolean hasForEach() {
        return !forEachs.isEmpty();
    }


    public Iterable<FE> getForEachs() {
        return forEachs;
    }

    public boolean addVariable(V variable) {
        if(variables.add(variable)){

            if(variable != null)
                variable.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeVariable(V variable) {
        if(variables.remove(variable)){

            if(variable != null)
                variable.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasVariable(V variable) {
        return variables.contains(variable);
    }


    public boolean hasVariables() {
        return !variables.isEmpty();
    }


    public Iterable<V> getVariables() {
        return variables;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
      try{
        System.out.println("entrou no startElement d body");
        if(localName.equals("body")){
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
        }

        else if(localName.equals("media")){
                System.out.println("entrou no startElement d elemento media");
                N child = createXTemplateMedia();

                child.startElement(uri, localName, qName, attributes);
                addNode(child);
        }
        else if(localName.equals("meta")){
                M child = (M) createMeta();
                child.startElement(uri, localName, qName, attributes);
                addMeta(child);
            }
            else if(localName.equals("metadata")){
                MT child = (MT) createMetadata();
                child.startElement(uri, localName, qName, attributes);
                addMetadata(child);
            }
            else if(localName.equals("port")){
                System.out.println("entrou no startElement d elemento port");
                Pt child = createXTemplatePort();
                child.startElement(uri, localName, qName, attributes);
                addPort(child);
            }
            else if(localName.equals("property")){
                Pp child = createXTemplateProperty();
                child.startElement(uri, localName, qName, attributes);
                addProperty(child);
            }
            
            else if(localName.equals("context")){
                N child = createXTemplateContext();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("switch")){
                N child = createXTemplateSwitch();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("link")){
                L child = createXTemplateLink();
                child.startElement(uri, localName, qName, attributes);
                addLink(child);
            }
            else if(localName.equals("variable")){
                V child = createXTemplateVariable();
                child.startElement(uri, localName, qName, attributes);
                addVariable(child);
            }
            else if(localName.equals("for-each")){
                FE child = createForEach();
                child.startElement(uri, localName, qName, attributes);
                addForEach(child);
            }
        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public void endDocument(){
        if(hasMeta()){
            Iterable<M> metas = this.getMetas();
            for(M meta : metas){
                meta.endDocument();
                addWarning(meta.getWarnings());
                addError(meta.getErrors());
            }
        }
        if(hasMetadata()){
            Iterable<MT> metadatas = this.getMetadatas();
            for(MT metadata : metadatas){
                metadata.endDocument();
                addWarning(metadata.getWarnings());
                addError(metadata.getErrors());
            }
        }
        if(hasPort()){
            Iterable<Pt> ports = this.getPorts();
            for(Pt port : ports){
                port.endDocument();
                addWarning(port.getWarnings());
                addError(port.getErrors());
            }
        }
        if(hasProperty()){
            Iterable<Pp> properties = this.getProperties();
            for(Pp property : properties){
                property.endDocument();
                addWarning(property.getWarnings());
                addError(property.getErrors());
            }
        }
        if(hasLink()){
            Iterable<L> links = this.getLinks();
            for(L link : links){
                link.endDocument();
                addWarning(link.getWarnings());
                addError(link.getErrors());
            }
        }
        if(hasNode()){
            Iterable<N> nodes = this.getNodes();
             for(N node : nodes){
                node.endDocument();
                addWarning(node.getWarnings());
                addError(node.getErrors());
            }
        }
        if(hasVariables()){
             for(V variable : variables){
                variable.endDocument();
                addWarning(variable.getWarnings());
                addError(variable.getErrors());
            }
        }
        if(hasForEach()){
             for(FE forEach : forEachs){
                forEach.endDocument();
                addWarning(forEach.getWarnings());
                addError(forEach.getErrors());
            }
        }
    }

    public void searchNodes(Iterable<D> descriptors, Iterable<C> connectors, Iterable<R> rules, Iterable<N> nodes){
       for(N node : nodes){
            if(node instanceof XTPMedia)
                ((XTPMedia)node).searchMedia(descriptors);
            else if(node instanceof XTPContext)
                ((XTPContext) node).searchContext(descriptors, connectors, rules);
             else if (node instanceof XTPSwitch)
               ((XTPSwitch) node).searchSwitch(descriptors, connectors, rules);


    }
}

    public void searchForExternalReferences(Iterable<D> descriptors, Iterable<C> connectors,Iterable<R> rules){
        Iterable<N> nodes = this.getNodes();
        searchNodes(descriptors, connectors, rules, nodes);
        Iterable<L> links = this.getLinks();
        for(L  link : links ){
            ((XTPLink)link).searchLink(descriptors, connectors);
        }
        for(FE forEach : forEachs){
            forEach.searchForEach(descriptors, connectors, rules);
        }



    }
    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected N createXTemplateMedia()throws NCLInvalidIdentifierException {
        return  (N) new  XTPMedia(getReader(), this);
    }

    protected Pt createXTemplatePort() {
        return (Pt) new XTPPort(getReader(), this);
        //adicionei um construtor vazio na ana (nclport)
    }

     protected Pp createXTemplateProperty() {
        return (Pp) new XTPProperty(getReader(), this);
    }

     protected N createXTemplateContext() {
        return (N) new XTPContext(getReader(), this);
        //adicionei um construtor vazio na ana (nclcontext)
    }

     protected N createXTemplateSwitch() {
        return (N) new XTPSwitch(getReader(), this);
        //adicionei um construtor vazio na ana (nclswitch)
    }

    protected L createXTemplateLink() {
        return (L) new XTPLink(getReader(), this);
        //adicionei um construtor vazio na ana (nclswitch)
    }

    protected V createXTemplateVariable() {
        return (V) new XTPVariable(getReader(), this);
        //adicionei um construtor vazio na ana (nclswitch)
    }
    protected FE createForEach() {
        return (FE) new XTPForEach(getReader(), this);
        //adicionei um construtor vazio na ana (nclswitch)
    }
}
