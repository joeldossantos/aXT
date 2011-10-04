package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPComponentType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPPortType<T extends XTPPortType, C extends XTPComponentType> extends NCLPort implements XTPLabeledElement<T> {

    protected StringType xlabel;
    protected StringType select;


    public XTPPortType(String id) throws NCLInvalidIdentifierException {
        super(id);
    }


    public void setXLabel(String xlabel) throws NullPointerException, IllegalArgumentException {
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
