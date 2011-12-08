package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPNode;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPPortPrototype<T extends XTPPortPrototype, P extends XTPElement, I extends XTPElementImpl, En extends XTPNode, Ei extends XTPInterface>
        extends XMLIdentifiableElementPrototype<Ei, P, I> implements XTPInterface<Ei, P> {

    protected XTPVocabularyElement xlabel;
    protected StringType select;
    protected En component;
    protected Ei interfac;


    public XTPPortPrototype(String id) throws XMLException {
        super();
        setId(id);
    }
    
    
    public XTPPortPrototype() throws XMLException {
        super();
    }


    public void setXLabel(XTPVocabularyElement xlabel) throws XMLException {
        this.xlabel = xlabel;
    }


    public XTPVocabularyElement getXLabel(){
        return xlabel;
    }


    public void setSelect(String select) throws XMLException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }


    /**
     * Atribui um nó a porta.
     * 
     * @param component
     *          elemento representando o nó.
     */
    public void setComponent(En component) {
        this.component = component;
    }
    
    
    /**
     * Retorna o nó atribuido a porta.
     * 
     * @return
     *          elemento representando o nó.
     */
    public En getComponent() {
        return component;
    }
    
    
    /**
     * Determina a interface de nó atributa a porta.
     * 
     * @param interfac
     *          elemento representando a interface do nó.
     */
    public void setInterface(Ei interfac) {
        this.interfac = interfac;
    }
    
    
    /**
     * Retorna a interface de nó atributa a porta.
     * 
     * @return
     *          elemento representando a interface do nó.
     */
    public Ei getInterface() {
        return interfac;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <port> element and attributes declaration
        content = space + "<port";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getComponent() != null)
            content += " component='" + getComponent().getId() + "'";
        if(getInterface() != null)
            content += " interface='" + getInterface().getId() + "'";
        content += "/>\n";
        
        return content;
    }
}
