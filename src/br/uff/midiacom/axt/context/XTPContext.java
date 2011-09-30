
package br.uff.midiacom.axt.context;

import br.uff.midiacom.axt.iteration.XTPForEach;
import br.uff.midiacom.axt.iteration.XTPVariable;
import br.uff.midiacom.axt.link.XTPLink;
import br.uff.midiacom.axt.Media.XTPMedia;
import br.uff.midiacom.axt.Media.XTPProperty;
import br.uff.midiacom.axt.port.XTPPort;
import br.uff.midiacom.axt.Switch.XTPSwitch;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.rule.NCLRule;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 *
 * @author flavia
 */
public class XTPContext<C extends NCLContext, M extends NCLMeta, MT extends NCLMetadata, Pt extends NCLPort,
        Pp extends NCLProperty, N extends NCLNode, L extends NCLLink, FE extends XTPForEach, V extends XTPVariable,
        Cn extends NCLCausalConnector, D extends NCLLayoutDescriptor, R extends NCLRule> extends NCLContext  {


    String xlabel;
    boolean insideContextFlag;
    private Set<FE> forEachs = new TreeSet<FE>();
    private Set<V> variables = new TreeSet<V>();

    //construtores

    public XTPContext(String id) throws NCLInvalidIdentifierException{
        super(id);
    }

    public XTPContext(XMLReader reader, XMLElement parent){
        super();
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);



    }

     public String getXLabel(){
        return this.xlabel;
    }

    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
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
            if(localName.equals("context") && !insideContextFlag){
                cleanWarnings();
                cleanErrors();
                insideContextFlag = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("xlabel"))
                        setXLabel(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("refer"))
                        setRefer((C) new XTPContext(attributes.getValue(i)));//cast retirado na correcao das referencias
                }
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
                Pt child = createPort();
                child.startElement(uri, localName, qName, attributes);
                addPort(child);
            }
            else if(localName.equals("property")){
                Pp child = createProperty();
                child.startElement(uri, localName, qName, attributes);
                addProperty(child);
            }
            else if(localName.equals("media")){
                N child = createMedia();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("context") && insideContextFlag){
                N child = createContext();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("switch")){
                N child = createSwitch();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("link")){
                L child = createLink();
                child.startElement(uri, localName, qName, attributes);
                addLink(child);
            }
            else if(localName.equals("for-each")){
                FE child = createForEach();
                child.startElement(uri, localName, qName, attributes);
                addForEach(child);
            }
            else if(localName.equals("variable")){
                V child = createVariable();
                child.startElement(uri, localName, qName, attributes);
                addVariable(child);
            }
        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getRefer() != null)
                contextReference();
        }

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
        if(hasNode()){
            Iterable<N> nodes = this.getNodes();
            for(N node : nodes){
                node.endDocument();
                addWarning(node.getWarnings());
                addError(node.getErrors());
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
        if(hasForEach()){
            for(FE forEach : forEachs){
                forEach.endDocument();
                addWarning(forEach.getWarnings());
                addError(forEach.getErrors());
            }
        }
        if(hasVariables()){
            for(V var : variables){
                var.endDocument();
                addWarning(var.getWarnings());
                addError(var.getErrors());
            }
        }
    }

    private void contextReference() {
        //Search for the interface inside the node
        XMLElement body = (XMLElement) getParent();

        while(!(body instanceof XTPBody)){
            body = (XMLElement) body.getParent();
            if(body == null){
                addWarning("Could not find a body");
                return;
            }
        }

        setRefer(findContext(((XTPBody) body).getNodes()));
    }


    private C findContext(Set<N> nodes) {
        for(N n : nodes){
            if(n instanceof XTPContext){
                if(n.getId().equals(getRefer().getId()))
                    return (C) n;
                else if( ((NCLContext) n).hasNode()){
                    Set<N> cnodes = ((NCLContext) n).getNodes();
                    C c = findContext(cnodes);
                    if(c != null)
                        return (C) c;
                }
            }
            else if(n instanceof XTPSwitch){
                if( ((XTPSwitch) n).hasNode()){
                    Set<N> snodes = ((XTPSwitch) n).getNodes();
                    C c = findContext(snodes);
                    if(c != null)
                        return (C) c;
                }
            }
        }

        addWarning("Could not find context with id: " + getRefer().getId());
        return null;
    }
    public void searchNodes(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules, N nodeS ){
        Iterable<N> nodes = ((XTPContext)nodeS).getNodes();
        for(N node : nodes){
            if(node instanceof XTPMedia)
                ((XTPMedia)node).searchMedia(descriptors);
            else if(node instanceof XTPContext)
                ((XTPContext)node).searchContext(descriptors, connectors, rules);
             else if (node instanceof XTPSwitch)
                ((XTPSwitch)node).searchSwitch(descriptors, connectors, rules);

              
    }
    }

    public void searchContext(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules){
        if(this.hasNode())
        searchNodes(descriptors, connectors, rules, (N) this);

        if(this.hasLink()){
            Iterable<L> links = this.getLinks();
            for(L link: links ){
                ((XTPLink)link).searchLink(descriptors, connectors);
            }
        }

        if(this.hasForEach()){
            for(FE forEach: forEachs){
                forEach.searchForEach(descriptors, connectors, rules);
            }
        }
    }

    @Override
    protected Pt createPort() {
        return (Pt) new XTPPort(getReader(), this);
    }

    @Override
    protected Pp createProperty() {
        return (Pp) new XTPProperty(getReader(), this);
    }

    @Override
    protected N createMedia() {
        //adicionei construtor vazio na aNa (NCLMedia)
        return (N) new XTPMedia(getReader(), this);
    }

    @Override
    protected N createContext() {
        return (N) new XTPContext(getReader(), this);
    }

    @Override
    protected N createSwitch() {
        return (N) new XTPSwitch(getReader(), this);
    }

    @Override
    protected L createLink() {
        return (L) new XTPLink(getReader(), this);
    }

    protected FE createForEach() {
        return (FE) new XTPForEach(getReader(), this);
    }


    protected V createVariable() {
        return (V) new XTPVariable(getReader(), this);
    }

    @Override
    public String parse(int ident) {return null;}

  
    public boolean validate() {return true;}

    @Override
    public void addWarning(String warning) {}



}
