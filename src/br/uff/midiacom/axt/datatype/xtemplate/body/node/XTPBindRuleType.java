package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitchBindRule;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.string.StringType;


public class XTPBindRuleType<T extends XTPBindRuleType, N extends NCLNode, R extends NCLTestRule> extends NCLSwitchBindRule<T, N, R> implements XTPElement<T> {

    protected StringType select;


    public XTPBindRuleType(){
        super();
    }


    public void setSelect(String select) throws NullPointerException, IllegalArgumentException {
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
