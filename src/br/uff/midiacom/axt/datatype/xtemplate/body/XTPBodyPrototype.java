package br.uff.midiacom.axt.datatype.xtemplate.body;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPNode;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPBodyPrototype<T extends XTPBodyPrototype, P extends XTPElement, I extends XTPElementImpl, Ep extends XTPPortPrototype, En extends XTPNode, El extends XTPLinkPrototype, Ev extends XTPVariablePrototype, Ef extends XTPForEachPrototype>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected ElementList<Ep, T> ports;
    protected ElementList<En, T> nodes;
    protected ElementList<El, T> links;
    protected ElementList<Ev, T> variables;
    protected ElementList<Ef, T> forEachs;
    
    
    public XTPBodyPrototype() throws XMLException {
        super();
        ports = new ElementList<Ep, T>();
        nodes = new ElementList<En, T>();
        links = new ElementList<El, T>();
        variables = new ElementList<Ev, T>();
        forEachs = new ElementList<Ef, T>();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>();
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
     * Adiciona uma porta ao corpo do documento NCL.
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
     * Adiciona um forEach ao contexto.
     *
     * @param forEach
     *          elemento representando o forEach a ser adicionado.
     * @return
     *          Verdadeiro se o forEach foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addForEach(Ef forEach) throws XMLException {
        return forEachs.add(forEach, (T) this);
    }
    
    /**
     * Adiciona um variable ao contexto.
     *
     * @param variable
     *          elemento representando o variable a ser adicionado.
     * @return
     *          Verdadeiro se o variable foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addVariable(Ev variable) throws XMLException {
        return variables.add(variable, (T) this);
    }
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
