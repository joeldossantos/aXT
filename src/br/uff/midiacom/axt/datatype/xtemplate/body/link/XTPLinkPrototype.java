package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPConnectorPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


public class XTPLinkPrototype<T extends XTPLinkPrototype, P extends XTPElement, I extends XTPElementImpl, Ep extends XTPParamPrototype, Eb extends XTPBindPrototype, Ec extends NCLCausalConnector, Exc extends XTPConnectorPrototype>
        extends XMLIdentifiableElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected Exc xtype;
    protected ElementList<Ep, T> linkParams;
    protected ElementList<Eb, T> binds;
    
    
    public XTPLinkPrototype() throws XMLException {
        super();
        linkParams = new ElementList<Ep, T>();
        binds = new ElementList<Eb, T>();
    }
    
    
    public void setXType(Exc xtype){
        this.xtype = xtype;
    }
    
    
    public Exc getXType(){
        return xtype;
    }
    
    
    /**
     * Adiciona um parâmetro ao link.
     * 
     * @param param
     *          elemento representando o parâmetro a ser adicionado.
     * @return
     *          verdadeiro se o parâmetro foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addLinkParam(Ep param) throws XMLException {
        return linkParams.add(param, (T) this);
    }
    
    
    /**
     * Remove um parâmetro do link.
     * 
     * @param param
     *          elemento representando o parâmetro a ser removido.
     * @return
     *          verdadeiro se o parâmetro foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeLinkParam(Ep param) throws XMLException {
        return linkParams.remove(param);
    }
    
    
    /**
     * Verifica se o link possui um parâmetro.
     * 
     * @param param
     *          elemento representando o parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasLinkParam(Ep param) throws XMLException {
        return linkParams.contains(param);
    }
    
    
    /**
     * Verifica se o link possui algum parâmetro.
     * 
     * @return
     *          verdadeiro se o link possui algum parâmetro.
     */
    public boolean hasLinkParam() {
        return !linkParams.isEmpty();
    }
    
    
    /**
     * Retorna os parâmetros do link.
     * 
     * @return
     *          lista contendo os parâmetros do link.
     */
    public ElementList<Ep, T> getLinkParams() {
        return linkParams;
    }
    
    
    /**
     * Adiciona um bind ao link.
     * 
     * @param bind
     *          elemento representando o bind a ser adicionado.
     * @return
     *          verdadeiro se o bind for adicionado.
     *
     * @see ArrayList#add
     */
    public boolean addBind(Eb bind) throws XMLException {
        return binds.add(bind, (T) this);
    }
    
    
    /**
     * Remove um bind do link.
     * 
     * @param bind
     *          elemento representando o bind a ser removido.
     * @return
     *          verdadeiro se o bind for removido.
     *
     * @see ArrayList#remove
     */
    public boolean removeBind(Eb bind) throws XMLException {
        return binds.remove(bind);
    }
    
    
    /**
     * Verifica se o link possui um bind.
     * 
     * @param bind
     *          elemento representando o bind a ser verificado.
     * @return
     *          verdadeiro se o bind existir.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifica se o link possui algum bind.
     *
     * @return
     *          verdadeiro se o link possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }
    
    
    /**
     * Retorna os binds do link
     * 
     * @return
     *          lista contendo os binds do link.
     */
    public ElementList<Eb, T> getBinds() {
        return binds;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <link> element and attributes declaration
        content = space + "<link";
        if(getId() != null)
            content += " id='" + getId() + "'";
        
        content += ">\n";
        
        // <link> element content
        if(hasLinkParam()){
            for(Ep param : linkParams)
                content += param.parse(ident + 1);
        }
        if(hasBind()){
            for(Eb bind : binds)
                content += bind.parse(ident + 1);
        }

        // <link> element end declaration
        content += space + "</link>\n";
        
        return content;
    }


    public boolean compare(T other) {
        boolean comp = true;

        // Compara pelo xconnector
        if(getXType() != null && other.getXType() != null)
            comp &= getXType().compare(other.getXType());

        // Compara o número de parâmetros
        comp &= linkParams.size() == other.getLinkParams().size();

        // Compara o número de binds
        comp &= binds.size() == other.getBinds().size();

        // Compara os parâmetros
        Iterator it = other.getLinkParams().iterator();
        for(Ep param : linkParams){
            Ep other_param = (Ep) it.next();
            comp &= param.compare(other_param);
            if(comp)
                break;
        }

        // Compara os binds
        it = other.getBinds().iterator();
        for(Eb bind : binds){
            if(!it.hasNext())
                continue;
            Eb other_bind = (Eb) it.next();
            comp = bind.compare(other_bind);
            if(comp)
                break;
        }


        return comp;
    }
}
