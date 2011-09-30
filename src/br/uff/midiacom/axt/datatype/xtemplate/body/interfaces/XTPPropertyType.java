package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.string.StringType;


public class XTPPropertyType<T extends XTPPropertyType, I extends NCLInterface> extends NCLProperty<I> implements XTPLabeledElement<T> {

    protected StringType xlabel;
    protected StringType select;


    public XTPPropertyType(NCLSystemVariable name, String xlabel) throws NCLInvalidIdentifierException, NullPointerException, IllegalArgumentException {
        super(name);
        setXLabel(xlabel);
    }


    public XTPPropertyType(String name, String xlabel) throws NCLInvalidIdentifierException, NullPointerException, IllegalArgumentException {
        super(name);
        setXLabel(xlabel);
    }


    public void setXLabel(String xlabel) throws NullPointerException, IllegalArgumentException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

        this.xlabel = new StringType(xlabel);
    }


    public String getXLabel(){
        return xlabel.getValue();
    }


    public void setSelect(String select) throws NullPointerException, IllegalArgumentException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }


    @Override
    public boolean compare(T other) {
        return getId().equals(other.getId());
    }
}
