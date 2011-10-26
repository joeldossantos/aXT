package br.uff.midiacom.axt.datatype.xtemplate.body;

import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPortPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.link.XTPLinkPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPBodyPrototype<T extends XTPBodyPrototype, P extends XTPElement, I extends XMLElementImpl, Ep extends XTPPortPrototype, En extends NCLNode, El extends XTPLinkPrototype, Ev extends XTPVariablePrototype, Ef extends XTPForEachPrototype> extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected ElementList<Ep, T> ports;
    protected ElementList<En, T> nodes;
    protected ElementList<El, T> links;
    protected ElementList<Ev, T> variables;
    protected ElementList<Ef, T> forEachs;
    
    
    public XTPBodyPrototype() throws XMLException {
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
