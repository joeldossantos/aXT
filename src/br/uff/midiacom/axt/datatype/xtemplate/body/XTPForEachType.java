package br.uff.midiacom.axt.datatype.xtemplate.body;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkType;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPSwitchType;
import br.uff.midiacom.xml.XMLElement;
import br.uff.midiacom.xml.elementList.ElementList;
import br.uff.midiacom.xml.string.StringType;


public class XTPForEachType<T extends XTPForEachType, I extends NCLInterface, S extends XTPSwitchType, L extends XTPLinkType, V extends XTPVariableType> extends XMLElement<T> implements XTPElement<T> {
    
    protected StringType select;
    protected ElementList<I> interfaces;
    protected ElementList<S> switches;
    protected ElementList<L> links;
    protected ElementList<V> variables;


    public XTPForEachType(String select) {
        setSelect(select);
    }


    public void setSelect(String select) throws NullPointerException, IllegalArgumentException {
        if(select == null)
            throw new NullPointerException("Null String.");

        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }


    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
