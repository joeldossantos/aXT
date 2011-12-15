package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPNode;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;


public class XTPMappingPrototype<T extends XTPMappingPrototype, P extends XTPElement, I extends XTPElementImpl, En extends XTPNode, Ei extends XTPInterface>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected En component;
    protected Ei interfac;


    /**
     * Construtor do elemento <i>mapping</i> da <i>Nested Context Language</i> (NCL).
     */
    public XTPMappingPrototype() throws XMLException {
        super();
    }


    /**
     * Determina o componente mapeado pelo mapeamento.
     *
     * @param component
     *          elemento representando o componente mapeado.
     */
    public void setComponent(En component) {
        this.component = component;
    }


    /**
     * Retorna o componente mapeado pelo mapeamento.
     *
     * @return
     *          elemento representando o componente mapeado.
     */
    public En getComponent() {
        return component;
    }


    /**
     * Determina a interface mapeada pelo mapeamento.
     *
     * @param interfac
     *          elemento representando a interface mapeada.
     */
    public void setInterface(Ei interfac) {
        this.interfac = interfac;
    }


    /**
     * Retorna a interface mapeada pelo mapeamento.
     *
     * @return
     *          elemento representando a interface mapeada.
     */
    public Ei getInterface() {
        return interfac;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<mapping";
        if(getComponent() != null)
            content += " component='" + getComponent().getId() + "'";
        if(getInterface() != null)
            content += " interface='" + getInterface().getId() + "'";
        content += "/>\n";

        return content;
    }

    public boolean compare(T other) {
        boolean comp = true;

        // Compara pelo componente
        En thisComp = getComponent();
        En otherComp = (En) other.getComponent();
        if(thisComp != null && otherComp != null)
            comp &= thisComp.compare(otherComp);
        else
            comp &= !(thisComp != null || otherComp != null);

        // Compara pela interface
        Ei thisInt = getInterface();
        Ei otherInt = (Ei) other.getInterface();
        if(thisInt != null && otherInt != null)
            comp &= thisInt.compare(otherInt);
        else
            comp &= !(thisInt != null || otherInt != null);

        return comp;
    }
}
