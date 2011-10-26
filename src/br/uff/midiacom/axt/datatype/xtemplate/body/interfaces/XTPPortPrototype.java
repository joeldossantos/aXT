package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPPortPrototype<T extends XTPPortPrototype, P extends XTPElement, I extends XMLElementImpl, En extends NCLNnode, Ei extends NCLInterface> extends NCLPort<T, P, I, En, Ei> implements XTPLabeledElement<T, P> {

    protected StringType xlabel;
    protected StringType select;


    public XTPPortPrototype(String id) throws XMLException {
        super(id);
    }


    public void setXLabel(String xlabel) throws XMLException {
        this.xlabel = new StringType(xlabel);
    }


    public String getXLabel(){
        return xlabel.getValue();
    }


    public void setSelect(String select) throws XMLException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }
}
