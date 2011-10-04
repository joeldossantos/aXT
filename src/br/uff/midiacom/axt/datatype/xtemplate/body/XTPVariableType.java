package br.uff.midiacom.axt.datatype.xtemplate.body;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.Element;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPVariableType<T extends XTPVariableType> extends Element<T> implements XTPElement<T> {

    protected StringType name;
    protected StringType select;
    
    
    public XTPVariableType(String name, String select) {
        setName(name);
        setSelect(select);
    }
    
    
    public void setName(String name) throws NullPointerException, IllegalArgumentException {
        if(name == null)
            throw new NullPointerException("Null String.");
        
        this.name = new StringType(name);
    }
    
    
    public String getName() {
        return name.getValue();
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
        return name.getValue().equals(other.getName());
    }
}
