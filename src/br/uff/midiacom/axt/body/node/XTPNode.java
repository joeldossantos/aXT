package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.body.interfaces.XTPInterface;
import br.uff.midiacom.xml.XMLException;


public interface XTPNode<T extends XTPNode, P extends XTPElement, Ei extends XTPInterface>
        extends XTPElement<T, P>, br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPNode<T, P> {
    
    
    /**
     * Searches for an interface inside a node and its descendants. The interface
     * could be: area, property, port, switchPort.
     * 
     * @param xlabel
     *          xlabel of the interface to be found.
     * @return 
     *          interface or null if no interface was found.
     */
    public Ei findInterface(String xlabel) throws XMLException;
    
    
    /**
     * Searches for a node inside a node and its descendants. The node will be
     * searched inside contexts and switches.
     * 
     * @param xlabel
     *          xlabel of the node to be found.
     * @return 
     *          node or null if no node was found.
     */
    public T findNode(String xlabel) throws XMLException;
}
