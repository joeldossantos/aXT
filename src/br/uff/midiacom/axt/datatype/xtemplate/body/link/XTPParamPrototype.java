package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPParamPrototype<T extends XTPParamPrototype, P extends XTPElement, I extends XTPElementImpl, Ec extends NCLConnectorParam>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected Ec name;
    protected StringType value;
    protected NCLParamInstance paramType;
    protected StringType select;
    
    
    public XTPParamPrototype(NCLParamInstance paramType) throws XMLException {
        super();
        if(paramType == null)
            throw new XMLException("Null type");

        this.paramType = paramType;
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>();
    }
    
    
    /**
     * Attribui um <i>connectorParam</i> ao parâmetro.
     * 
     * @param connectorParam
     *          elemento representando o parâmetro do conector ao qual este parâmetro se refere.
     */
    public void setName(Ec connectorParam) {
        this.name = connectorParam;
    }
    
    
    /**
     * Retorna o <i>connectorParam</i> do parâmetro.
     * 
     * @return NCLConnectorParam representando o nome do parâmetro.
     */
    public Ec getName() {
        return name;
    }
    
    
    /**
     * Determina o valor do atributo value do parâmetro.
     * 
     * @param value
     *          String contendo o valor a ser atribuído ao parâmetro.
     *
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(String value)  throws XMLException {
        this.value = new StringType(value);
    }
    
    
    /**
     * Retorna o valor do atributo value do parâmetro.
     * 
     * @return
     *          String contendo o valor atribuído ao parâmetro.
     */
    public String getValue() {
        if(value != null)
            return value.getValue();
        else
            return null;
    }


    public NCLParamInstance getType() {
        return paramType;
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
        
        
        // param element and attributes declaration
        content = space + "<" + paramType.toString();
        if(getName() != null)
            content += " name='" + getName().getName() + "'";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";
        
        return content;
    }
    
    
    public boolean compare(T other) {
        return getName().equals(other.getName());
    }
}
