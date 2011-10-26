package br.uff.midiacom.axt.datatype.xtemplate.body;

import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortType;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkType;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPBodyType<T extends XTPBodyType, P extends XTPElement, I extends XMLElementImpl, Ep extends XTPPortType, En extends NCLNode, El extends XTPLinkType, Ev extends XTPVariableType, Ef extends XTPForEachType> extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected ElementList<Ep, T> ports;
    protected ElementList<En, T> nodes;
    protected ElementList<El, T> links;
    protected ElementList<Ev, T> variables;
    protected ElementList<Ef, T> forEachs;
    
    
    public XTPBodyType() throws XMLException {
        super();
        ports = new ElementList<Ep, T>();
        nodes = new ElementList<En, T>();
        links = new ElementList<El, T>();
        variables = new ElementList<Ev, T>();
        forEachs = new ElementList<Ef, T>();
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
