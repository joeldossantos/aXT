package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetaPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetadataPrototype;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.auxiliar.LabeledElementList;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public class XTPContextPrototype<T extends XTPContextPrototype, P extends XTPElement, I extends XTPElementImpl, Ept extends XTPPortPrototype, Epp extends XTPPropertyPrototype, En extends XTPNode, El extends XTPLinkPrototype, Em extends NCLMetaPrototype, Emt extends NCLMetadataPrototype>
        extends XMLIdentifiableElementPrototype<En, P, I> implements XTPNode<En, P> {

    protected XTPVocabularyElement xlabel;
    protected T refer;
    protected LabeledElementList<Ept, T> ports;
    protected LabeledElementList<Epp, T> properties;
    protected LabeledElementList<En, T> nodes;
    protected IdentifiableElementList<El, T> links;
    protected ElementList<Em, T> metas;
    protected ElementList<Emt, T> metadatas;
    
    
    public XTPContextPrototype(String id, XTPVocabularyElement xlabel) throws XMLException {
        super();
        setId(id);
        setXLabel(xlabel);
        ports = new LabeledElementList<Ept, T>();
        properties = new LabeledElementList<Epp, T>();
        nodes = new LabeledElementList<En, T>();
        links = new IdentifiableElementList<El, T>();
        metas = new ElementList<Em, T>();
        metadatas = new ElementList<Emt, T>();
    }
    
    public XTPContextPrototype() throws XMLException {
        super();
        ports = new LabeledElementList<Ept, T>();
        properties = new LabeledElementList<Epp, T>();
        nodes = new LabeledElementList<En, T>();
        links = new IdentifiableElementList<El, T>();
        metas = new ElementList<Em, T>();
        metadatas = new ElementList<Emt, T>();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<T, P>();
    }


    public void setXLabel(XTPVocabularyElement xlabel) throws XMLException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

        this.xlabel = xlabel;
    }


    public XTPVocabularyElement getXLabel(){
        return xlabel;
    }


    /**
     * Atribui um contexto para ser reutilizado pelo contexto.
     *
     * @param refer
     *          elemento representando o contexto a ser reutilizado.
     */
    public void setRefer(T refer) {
        this.refer = refer;
    }


    /**
     * Retorna o contexto reutilizado pelo contexto.
     *
     * @return
     *          elemento representando o contexto a ser reutilizado.
     */
    public T getRefer() {
        return refer;
    }
    
    
    /**
     * Adiciona uma porta ao contexto.
     *
     * @param port
     *          elemento representando a porta a ser adicionada.
     * @return
     *          Verdadeiro se a porta foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addPort(Ept port) throws XMLException {
        return ports.add(port, (T) this);
    }


    /**
     * Remove uma porta do contexto.
     *
     * @param port
     *          elemento representando a porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(Ept port) throws XMLException {
        return ports.remove(port);
    }


    /**
     * Remove uma porta do contexto.
     *
     * @param id
     *          identificador da porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(String id) throws XMLException {
        return ports.remove(id);
    }


    /**
     * Verifica se o contexto possui uma porta.
     *
     * @param port
     *          elemento representando a porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(Ept port) throws XMLException {
        return ports.contains(port);
    }


    /**
     * Verifica se o contexto possui uma porta.
     *
     * @param id
     *          identificador da porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(String id) throws XMLException {
        return ports.get(id) != null;
    }


    /**
     * Verifica se o contexto possui alguma porta.
     *
     * @return
     *          verdadeiro se o contexto possuir alguma porta.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }


    /**
     * Retorna as portas do contexto.
     *
     * @return
     *          lista contendo as portas do contexto.
     */
    public LabeledElementList<Ept, T> getPorts() {
        return ports;
    }


    /**
     * Adiciona uma propriedade ao contexto.
     *
     * @param property
     *          elemento representando a propriedade a ser adicionada.
     * @return
     *          Verdadeiro se a propriedade foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addProperty(Epp property) throws XMLException {
        return properties.add(property, (T) this);
    }


    /**
     * Remove uma propriedade do contexto.
     *
     * @param property
     *          elemento representando a propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(Epp property) throws XMLException {
        return properties.remove(property);
    }


    /**
     * Remove uma propriedade do contexto.
     *
     * @param name
     *          nome da propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(String name) throws XMLException {
        return properties.remove(name);
    }


    /**
     * Verifica se o contexto possui uma propriedade.
     *
     * @param property
     *          elemento representando a propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(Epp property) throws XMLException {
        return properties.contains(property);
    }


    /**
     * Verifica se o contexto possui uma propriedade.
     *
     * @param name
     *          nome da propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(String name) throws XMLException {
        return properties.get(name) != null;
    }


    /**
     * Verifica se o contexto possui alguma propriedade.
     *
     * @return
     *          verdadeiro se o contexto possuir alguma propriedade.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }


    /**
     * Retorna as propriedades do contexto.
     *
     * @return
     *          lista contendo as propriedades do contexto.
     */
    public LabeledElementList<Epp, T> getProperties() {
        return properties;
    }


    /**
     * Adiciona um nó ao contexto.
     *
     * @param node
     *          elemento representando o nó a ser adicionado.
     * @return
     *          Verdadeiro se o nó foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addNode(En node) throws XMLException {
        return nodes.add(node, (T) this);
    }


    /**
     * Remove um nó do contexto.
     *
     * @param node
     *          elemento representando um nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(En node) throws XMLException {
        return nodes.remove(node);
    }


    /**
     * Remove um nó do contexto.
     *
     * @param id
     *          identificador do nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(String id) throws XMLException {
        return nodes.remove(id);
    }


    /**
     * Verifica se o contexto possui um nó.
     *
     * @param node
     *          elemento representando o nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(En node) throws XMLException {
        return nodes.contains(node);
    }


    /**
     * Verifica se o contexto possui um nó.
     *
     * @param id
     *          identificador do nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(String id) throws XMLException {
        return nodes.get(id) != null;
    }


    /**
     * Verifica se o contexto possui algum nó.
     *
     * @return
     *          verdadeiro se o contexto possuir algum nó.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }


    /**
     * Retorna os nós do contexto.
     *
     * @return
     *          lista contendo os nós do contexto.
     */
    public LabeledElementList<En, T> getNodes() {
        return nodes;
    }


    /**
     * Adiciona um link ao contexto.
     *
     * @param link
     *          elemento representando o link a ser adicionado.
     * @return
     *          Verdadeiro se o link foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addLink(El link) throws XMLException {
        return links.add(link, (T) this);
    }


    /**
     * Remove um link do contexto.
     *
     * @param link
     *          elemento representando o link a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeLink(El link) throws XMLException {
        return links.remove(link);
    }
    
    
    public boolean removeLink(String id) throws XMLException {
        return links.remove(id);
    }


    /**
     * Verifica se o contexto possui um link.
     *
     * @param link
     *          elemento representando o link a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasLink(El link) throws XMLException {
        return links.contains(link);
    }
    
    
    public boolean hasLink(String id) throws XMLException {
        return links.get(id) != null;
    }


    /**
     * Verifica se o contexto possui algum link.
     *
     * @return
     *          verdadeiro se o contexto possuir algum link.
     */
    public boolean hasLink() {
        return !links.isEmpty();
    }


    /**
     * Retorna os links do contexto.
     *
     * @return
     *          lista contendo os links do contexto.
     */
    public IdentifiableElementList<El, T> getLinks() {
        return links;
    }


    /**
     * Adiciona um metadado ao cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando o metadado a ser adicionado.
     * @return
     *          Verdadeiro se o metadado foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMeta(Em meta) throws XMLException {
        return metas.add(meta, (T) this);
    }


    /**
     * Remove um metadado do cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando o metadado a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMeta(Em meta) throws XMLException {
        return metas.remove(meta);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui um metadado.
     *
     * @param meta
     *          elemento representando o metadado a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasMeta(Em meta) throws XMLException {
        return metas.contains(meta);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui algum metadado.
     *
     * @return
     *          verdadeiro se o corpo do cabeçalho NCL possuir algum metadado.
     */
    public boolean hasMeta() {
        return !metas.isEmpty();
    }


    /**
     * Retorna os metadados do cabeçalho do documento NCL.
     *
     * @return
     *          conjunto contendo os metadados do cabeçalho do documento NCL.
     */
    public ElementList<Em, T> getMetas() {
        return metas;
    }


    /**
     * Adiciona um metadado ao cabeçalho do documento NCL.
     *
     * @param metadata
     *          elemento representando o metadado a ser adicionado.
     * @return
     *          Verdadeiro se o metadado foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMetadata(Emt metadata) throws XMLException {
        return metadatas.add(metadata, (T) this);
    }


    /**
     * Remove um metadado do cabeçalho do documento NCL.
     *
     * @param metadata
     *          elemento representando o metadado a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMetadata(Emt metadata) throws XMLException {
        return metadatas.remove(metadata);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui um metadado.
     *
     * @param metadata
     *          elemento representando o metadado a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasMetadata(Emt metadata) throws XMLException {
        return metadatas.contains(metadata);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui algum metadado.
     *
     * @return
     *          verdadeiro se o corpo do cabeçalho NCL possuir algum metadado.
     */
    public boolean hasMetadata() {
        return !metadatas.isEmpty();
    }


    /**
     * Retorna os metadados do cabeçalho do documento NCL.
     *
     * @return
     *          conjunto contendo os metadados do cabeçalho do documento NCL.
     */
    public ElementList<Emt, T> getMetadatas() {
        return metadatas;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <context> element and attributes declaration
        content = space + "<context";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getRefer() != null)
            content += " refer='" + getRefer().getId() + "'";
        
        // Test if the media has content
        if(hasMeta() || hasMetadata() || hasPort() || hasProperty() || hasNode() || hasLink()){
            content += ">\n";

            if(hasMeta()){
                for(Em meta : metas)
                    content += meta.parse(ident + 1);
            }
            if(hasMetadata()){
                for(Emt metadata : metadatas)
                    content += metadata.parse(ident + 1);
            }
            if(hasPort()){
                for(Ept port : ports)
                    content += port.parse(ident + 1);
            }
            if(hasProperty()){
                for(Epp property : properties)
                    content += property.parse(ident + 1);
            }
            if(hasNode()){
                for(En node : nodes)
                    content += node.parse(ident + 1);
            }
            if(hasLink()){
                for(El link : links)
                    content += link.parse(ident + 1);
            }
            
            // <context> element end declaration
            content += space + "</context>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
}
