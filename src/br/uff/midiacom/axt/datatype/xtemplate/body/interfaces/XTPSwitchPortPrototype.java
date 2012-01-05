package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPSwitchPortPrototype<T extends XTPSwitchPortPrototype, P extends XTPElement, I extends XTPElementImpl, Em extends XTPMappingPrototype, Ei extends XTPInterface>
        extends XMLIdentifiableElementPrototype<Ei, P, I> implements XTPInterface<Ei, P> {

    protected XTPVocabularyElement xlabel;
    protected ElementList<Em, T> mappings;
    

    public XTPSwitchPortPrototype(String id) throws XMLException {
        super();
        setId(id);
        mappings = new ElementList<Em, T>();
    }
    
    
    public XTPSwitchPortPrototype() throws XMLException {
        super();
        mappings = new ElementList<Em, T>();
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
     * Adiciona um mapeamento a porta de switch.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser adicionado.
     * @return
     *          Verdadeiro se o mapeamento foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMapping(Em mapping) throws XMLException {
        return mappings.add(mapping, (T) this);
    }


    /**
     * Remove um mapeamento da porta de switch.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser removido.
     * @return
     *          Verdadeiro se o mapeamento foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMapping(Em mapping) throws XMLException {
        return mappings.remove(mapping);
    }


    /**
     * Verifica se a porta de switch possui um mapeamento.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser verificado.
     * @return
     *          verdadeiro se o mapeamento existir.
     */
    public boolean hasMapping(Em mapping) throws XMLException {
        return mappings.contains(mapping);
    }


    /**
     * Verifica se a porta de switch possui algum mapeamento.
     *
     * @return
     *          verdadeiro se a porta de switch possuir algum mapeamento.
     */
    public boolean hasMapping() {
        return !mappings.isEmpty();
    }


    /**
     * Retorna os mapeamentos da porta de switch.
     *
     * @return
     *          lista contendo os mapeamentos da porta de switch.
     */
    public ElementList<Em, T> getMappings() {
        return mappings;
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
        content = space + "<switchPort";
        if(getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";

        if(hasMapping()){
            for(Em mapping : mappings)
                content += mapping.parse(ident + 1);
        }

        content += "</switchPort>\n";

        return content;
    }
}
