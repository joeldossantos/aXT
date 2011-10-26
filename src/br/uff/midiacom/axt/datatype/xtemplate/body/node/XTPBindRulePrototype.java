package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitchBindRule;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPBindRulePrototype<T extends XTPBindRulePrototype, P extends XTPElement, I extends XMLElementImpl, En extends NCLNode, Er extends NCLTestRule> extends NCLSwitchBindRule<T, P, I, En, Er> implements XTPElement<T, P> {

    protected StringType select;


    public XTPBindRulePrototype() throws XMLException{
        super();
    }


    public void setSelect(String select) throws XMLException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }


    @Override
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
