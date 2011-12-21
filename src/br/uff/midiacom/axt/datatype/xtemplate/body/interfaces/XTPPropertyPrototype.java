package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPPropertyPrototype<T extends XTPPropertyPrototype, P extends XTPElement, I extends XTPElementImpl, Ei extends XTPInterface>
        extends XMLIdentifiableElementPrototype<Ei, P, I> implements XTPInterface<Ei, P> {

    protected XTPVocabularyElement xlabel;
    protected StringType select;
    protected StringType value;


    public XTPPropertyPrototype(NCLSystemVariable name, XTPVocabularyElement xlabel) throws XMLException {
        super();
        setName(name);
        setXLabel(xlabel);
    }


    public XTPPropertyPrototype(String name, XTPVocabularyElement xlabel) throws XMLException {
        super();
        setName(name);
        setXLabel(xlabel);
    }
    
    
    public XTPPropertyPrototype() throws XMLException {
        super();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<T, P>();
    }
    
    
    /**
     * Determina o nome da propriedade sem seguir os valores padrão especificados na norma.
     * O nome, entretando pode estar na forma shared.xxx
     * 
     * @param name
     *          String contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public void setName(String name) throws XMLException {
        setId(name);
    }    


    /**
     * Determina o nome de uma propriedade seguindo os valore padrão especificados na norma.
     * 
     * @param name
     *          NCLSystemVariable contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public void setName(NCLSystemVariable name) throws XMLException {
        if(name == null)
            throw new XMLException("Invalid name");
        setId(name.toString());
    }
    
    
    /**
     * Retorna o nome da propriedade.
     * 
     * @return
     *          String contendo o nome da propriedade.
     */
    public String getName() {
        return getId();
    }
    
    
    /**
     * Atribui um valor a propriedade.
     * 
     * @param value
     *          String representando o valor a ser atribuido.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setValue(String value) throws XMLException {
        this.value = new StringType(value);
    }
    
    
    /**
     * Retorna o valor atributo a propriedade.
     * 
     * @return
     *          String representando o valor atribuido.
     */
    public String getValue() {
        if(value != null)
            return value.getValue();
        else
            return null;
    }


    public void setXLabel(XTPVocabularyElement xlabel) throws XMLException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

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
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        // <property> element and attributes declaration
        content = space + "<property";
        if(getName() != null)
            content += " name='" + getName() + "'";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";
        
        
        return content;
    }
}
