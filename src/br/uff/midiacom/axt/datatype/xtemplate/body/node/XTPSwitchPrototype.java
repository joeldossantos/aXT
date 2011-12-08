package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.axt.datatype.auxiliar.LabeledElementList;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPSwitchPortPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPSwitchPrototype<T extends XTPSwitchPrototype, P extends XTPElement, I extends XTPElementImpl, En extends XTPNode, Ep extends XTPSwitchPortPrototype, Eb extends XTPBindRulePrototype>
        extends XMLIdentifiableElementPrototype<En, P, I> implements XTPNode<En, P> {

    protected XTPVocabularyElement xlabel;
    protected T refer;
    protected En defaultComponent;
    protected LabeledElementList<Ep, T> ports;
    protected ElementList<Eb, T> binds;
    protected LabeledElementList<En, T> nodes;
    
    
    public XTPSwitchPrototype(String id, XTPVocabularyElement xlabel) throws XMLException {
        super();
        setId(id);
        setXLabel(xlabel);
        ports = new LabeledElementList<Ep, T>();
        binds = new ElementList<Eb, T>();
        nodes = new LabeledElementList<En, T>();
    }
    
    public XTPSwitchPrototype() throws XMLException {
        super();
        ports = new LabeledElementList<Ep, T>();
        binds = new ElementList<Eb, T>();
        nodes = new LabeledElementList<En, T>();
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
     * Atribui um switch para ser reutilizado pelo switch.
     *
     * @param refer
     *          elemento representando o switch a ser reutilizado.
     */
    public void setRefer(T refer) {
        this.refer = refer;
    }


    /**
     * Retorna o switch reutilizado pelo switch.
     *
     * @return
     *          elemento representando o switch a ser reutilizado.
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
    public boolean addPort(Ep port) throws XMLException {
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
    public boolean removePort(Ep port) throws XMLException {
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
    public boolean hasPort(Ep port) throws XMLException {
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
    public LabeledElementList<Ep, T> getPorts() {
        return ports;
    }


    /**
     * Determina o componente padrão do switch.
     *
     * @param defaultComponent
     *          elemento representando o componente padrão.
     */
    public void setDefaultComponent(En defaultComponent) {
        this.defaultComponent = defaultComponent;
    }


    /**
     * Retorna o componente padrão do switch.
     *
     * @return
     *          elemento representando o componente padrão.
     */
    public En getDefaultComponent() {
        return defaultComponent;
    }


    /**
     * Adiciona um bind ao switch.
     *
     * @param bind
     *          elemento representando o bind a ser adicionado.
     *
     * @see ArrayList#add
     */
    public boolean addBind(Eb bind) throws XMLException {
        return binds.add(bind, (T) this);
    }


    /**
     * Remove um bind do switch.
     *
     * @param bind
     *          elemento representando o bind a ser removido.
     *
     * @see ArrayList#remove
     */
    public boolean removeBind(Eb bind) throws XMLException {
        return binds.remove(bind);
    }


    /**
     * Verifica se o switch contém um bind.
     *
     * @param bind
     *          elemento representando o bind a ser verificado.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifica se o switch possui algum bind.
     *
     * @return
     *          verdadeiro se o switch possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Retorna os binds do switch.
     *
     * @return
     *          lista contendo os binds do switch.
     */
    public ElementList<Eb, T> getBinds() {
        return binds;
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

    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<switch";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getRefer() != null)
            content += " refer='" + getRefer() + "'";

        if(hasPort() || hasBind() || hasNode()){
            content += ">\n";

            if(hasPort()){
                for(Ep port : ports)
                    content += port.parse(ident + 1);
            }

            if(hasBind()){
                for(Eb bind : binds)
                    content += bind.parse(ident + 1);
            }

            if(getDefaultComponent() != null)
                content += space + "\t" + "<defaultComponent component='" + getDefaultComponent().getId() + "'/>\n";

            if(hasNode()){
                for(En node : nodes)
                    content += node.parse(ident + 1);
            }

            content += space + "</switch>\n";
        }
        else
            content += "/>\n";

        return content;
    }
}
