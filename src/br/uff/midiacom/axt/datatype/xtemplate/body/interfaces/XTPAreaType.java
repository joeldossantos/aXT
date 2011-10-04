package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.interfaces.NCLArea;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPAreaType<T extends XTPAreaType, I extends NCLInterface> extends NCLArea<I> implements XTPLabeledElement<T> {

    protected StringType xlabel;
    
    
    public XTPAreaType(String id, String xlabel) throws NCLInvalidIdentifierException, NullPointerException, IllegalArgumentException {
        super(id);
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


    @Override
    public boolean compare(T other) {
        return getId().equals(other.getId());
    }
}
