package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.ana.datatype.enums.NCLSystemVariable;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.axt.vocabulary.XTPVocabulary;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPProperty<T extends XTPProperty, P extends XTPElement, I extends XTPElementImpl, Ei extends XTPInterface>
        extends XTPPropertyPrototype<T, P, I, Ei> implements XTPInterface<Ei, P> {

    
    public XTPProperty(NCLSystemVariable name, XTPVocabularyElement xlabel) throws XMLException {
        super(name, xlabel);
    }
    
    
    public XTPProperty() throws XMLException {
        super();
    }
    
    
    public XTPProperty(String name, XTPVocabularyElement xlabel) throws XMLException {
        super(name, xlabel);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
        // set the xLabel (required)
        if(!(att_var = element.getAttribute("xlabel")).isEmpty())
            setXLabel(((XTPVocabulary)impl.getDoc().getVocabulary()).findComponent(att_var));
        else
            throw new XMLException("Could not find xlabel attribute.");
        
        // set the name
        if(!(att_var = element.getAttribute("name")).isEmpty())
                setName(att_var);
        
        // set the type
        if(!(att_var = element.getAttribute("value")).isEmpty())
            setValue(att_var);
        
        // set the select
        if(!(att_var = element.getAttribute("select")).isEmpty())
            setSelect(att_var);
    }
}
