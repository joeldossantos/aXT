package br.uff.midiacom.axt.datatype.xtemplate.constraints;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.XMLElement;
import br.uff.midiacom.xml.string.StringType;


public class XTPConstraintType<T extends XTPConstraintType> extends XMLElement<T> implements XTPElement<T> {

    protected StringType select;
    protected StringType description;
    
    
    public XTPConstraintType(String select) throws NullPointerException, IllegalArgumentException {
        setSelect(select);
    }
    
    
    public void setSelect(String select) throws NullPointerException, IllegalArgumentException {
        if(select == null)
            throw new NullPointerException("Null select String");

        this.select = new StringType(select);
    }
    
    
    public String getSelect() {
        return this.select.getValue();
    }
    
    
    public void setDescription(String description) throws IllegalArgumentException {
        this.description = new StringType(description);
    }
    
    
    public String getDescription() {
        return this.description.getValue();
    }


    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
