package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPPropertyType<T extends XTPPropertyType, P extends XTPElement, I extends XMLElementImpl, Ei extends NCLInterface> extends NCLProperty<T, P, I, Ei> implements XTPLabeledElement<T, P> {

    protected StringType xlabel;
    protected StringType select;


    public XTPPropertyType(NCLSystemVariable name, String xlabel) throws XMLException {
        super(name);
        setXLabel(xlabel);
    }


    public XTPPropertyType(String name, String xlabel) throws XMLException {
        super(name);
        setXLabel(xlabel);
    }


    public void setXLabel(String xlabel) throws XMLException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

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
