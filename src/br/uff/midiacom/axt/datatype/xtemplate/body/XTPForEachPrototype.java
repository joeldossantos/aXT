package br.uff.midiacom.axt.datatype.xtemplate.body;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPSwitchPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPForEachPrototype<T extends XTPForEachPrototype, P extends XTPElement, I extends XMLElementImpl, Ei extends XTPInterface, Es extends XTPSwitchPrototype, El extends XTPLinkPrototype, Ev extends XTPVariablePrototype> extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {
    
    protected StringType select;
    protected ElementList<Ei, T> interfaces;
    protected ElementList<Es, T> switches;
    protected ElementList<El, T> links;
    protected ElementList<Ev, T> variables;


    public XTPForEachPrototype(String select) throws XMLException {
        setSelect(select);
        interfaces = new ElementList<Ei, T>();
        switches = new ElementList<Es, T>();
        links = new ElementList<El, T>();
        variables = new ElementList<Ev, T>();
    }


    public void setSelect(String select) throws XMLException {
        if(select == null)
            throw new XMLException("Null String.");

        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }


    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
