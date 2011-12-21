package br.uff.midiacom.axt.datatype.xtemplate.body;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPSwitchPrototype;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPForEachPrototype<T extends XTPForEachPrototype, P extends XTPElement, I extends XTPElementImpl, Ei extends XTPInterface, Es extends XTPSwitchPrototype, El extends XTPLinkPrototype, Ev extends XTPVariablePrototype>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {
    
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
    
    public XTPForEachPrototype() throws XMLException {
        interfaces = new ElementList<Ei, T>();
        switches = new ElementList<Es, T>();
        links = new ElementList<El, T>();
        variables = new ElementList<Ev, T>();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>();
    }

    
    public boolean addPort(Ei port) throws XMLException {
        return interfaces.add(port, (T) this);
    }
    
    
    public boolean addArea(Ei area) throws XMLException {
        return interfaces.add(area, (T) this);
    }
    
    
    public boolean addProperty(Ei property) throws XMLException {
        return interfaces.add(property, (T) this);
    }
    
    
    public boolean addLink(El link) throws XMLException {
        return links.add(link, (T) this);
    }
    
    
    public boolean addSwitch(Es Switch) throws XMLException {
        return switches.add(Switch, (T) this);
    }
    
    
    public boolean addVariable(Ev variable) throws XMLException {
        return variables.add(variable, (T) this);
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
        return getSelect().equals(other.getSelect());
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
