package br.uff.midiacom.axt.body.node;

import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.axt.ReferenceManager;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPBindRulePrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import org.w3c.dom.Element;


public class XTPBindRule<T extends XTPBindRule, P extends XTPElement, I extends XTPElementImpl, En extends XTPNode, Er extends NCLTestRule>
        extends XTPBindRulePrototype<T, P, I, En, Er> implements XTPElement<T, P> {

    
    public XTPBindRule() throws XMLException {
        super();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>(this);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
        // set the constituent
        if(!(att_var = element.getAttribute("constituent")).isEmpty()){
            P aux;
            if((aux = (P) getParent()) == null)
                throw new XMLException("Could not find element " + att_var);

            En refEl = (En) ((En) aux).findNode(att_var);
            if(refEl == null)
                throw new XMLException("Could not find element " + att_var);

            setConstituent(refEl);
        }
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
            setSelect(att_var);
        
        // set the rule
        if(!(att_var = element.getAttribute("rule")).isEmpty()){
            Er rul = (Er) ReferenceManager.getInstance().findRuleReference(impl.getDoc(), att_var);
            setRule(rul);
        }
        else
            throw new XMLException("Could not find rule attribute.");
    }
}
