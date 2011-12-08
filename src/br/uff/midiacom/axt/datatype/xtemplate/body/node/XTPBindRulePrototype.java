package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPBindRulePrototype<T extends XTPBindRulePrototype, P extends XTPElement, I extends XTPElementImpl, En extends XTPNode, Er extends NCLTestRule>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected StringType select;
    protected En constituent;
    protected Er rule;


    public XTPBindRulePrototype() throws XMLException{
        super();
    }


    public void setSelect(String select) throws XMLException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }

    
    /**
     * Atribui um constituent ao bind.
     *
     * @param constituent
     *          elemento representando o nó mapeado pelo bind.
     */
    public void setConstituent(En constituent) {
        this.constituent = constituent;
    }


    /**
     * Retorna o constituent do bind.
     *
     * @return
     *          elemento representando o nó mapeado pelo bind.
     */
    public En getConstituent() {
        return constituent;
    }


    /**
     * Atribui uma regra de avaliação ao bind.
     *
     * @param rule
     *          elemento representando a regra de avaliação do bind.
     */
    public void setRule(Er rule) {
        this.rule = rule;
    }


    /**
     * Retorna a regra de avaliação do bind.
     *
     * @return
     *          elemento representando a regra de avaliação do bind.
     */
    public Er getRule() {
        return rule;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<bindRule";
        if(getRule() != null)
            content += " rule='" + getRule().getId() + "'";
        if(getConstituent() != null)
            content += " constituent='" + getConstituent().getId() + "'";
        content += "/>\n";


        return content;
    }


    public boolean compare(T other) {
        boolean comp = true;
        
        String this_sb, other_sb;

        // Compara pela regra
        if(getRule() == null) this_sb = ""; else this_sb = getRule().getId();
        if(other.getRule() == null) other_sb = ""; else other_sb = other.getRule().getId();
        comp &= this_sb.equals(other_sb);

        // Compara pelo constituent
        if(getConstituent() == null) this_sb = ""; else this_sb = getConstituent().getId();
        if(other.getConstituent() == null) other_sb = ""; else other_sb = other.getConstituent().getId();
        comp &= this_sb.equals(other_sb);

        return comp;
    }
}
