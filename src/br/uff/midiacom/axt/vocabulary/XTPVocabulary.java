package br.uff.midiacom.axt.vocabulary;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XTPVocabulary<T extends XTPVocabulary, P extends XTPElement, I extends XTPElementImpl, Ecp extends XTPComponent, Ep extends XTPComponentPort, Ecc extends XTPConnector>
        extends XTPVocabularyPrototype<T, P, I, Ecp, Ecc> implements XTPElement<T, P> {

    
    public XTPVocabulary() throws XMLException {
        super();
    }
    
    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<XMLIdentifiableElementPrototype, P>(this);
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        NodeList nl;

        try{
            // create the child nodes (except ports and links)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);
                    
                    // create the component
                    if(el.getTagName().equals("component")){
                        Ecp inst = createComponent();
                        addComponent(inst);
                        inst.load(el);
                    }
                    // create the connector
                    if(el.getTagName().equals("connector")){
                        Ecc inst = createConnector();
                        addConnector(inst);
                        inst.load(el);
                    }
                }
            }
        }
        catch(XMLException ex){
            throw new XMLException("Vocabulary > " + ex.getMessage());
        }
    }
    
    /**
     * Searches for a component inside the vocabulary.
     * 
     * @param xLabel
     *          xLabel of the component to be found.
     * @return 
     *          component or null if no component was found.
     */
    public Ecp findComponent(String xLabel) throws XMLException {
        Ecp result;
        
        for(Ecp c : components){
            result = (Ecp) c.findComponent(xLabel);
            if(result != null)
                return result;
        }
        return null;
    }
    
    
    /**
     * Searches for a component inside the vocabulary.
     * 
     * @param xLabel
     *          xLabel of the component to be found.
     * @return 
     *          component or null if no component was found.
     */
    public Ep findPort(String xLabel) throws XMLException {
        Ep result;
        
        for(Ecp c : components){
            result = (Ep) c.findPort(xLabel);
            if(result != null)
                return result;
        }
        return null;
    }
    
    
    /**
     * Searches for a connector inside the vocabulary.
     * 
     * @param xLabel
     *          xLabel of the connector to be found.
     * @return 
     *          connector or null if no connector was found.
     */
    public Ecc findConnector(String xLabel){
        Ecc result;
        
        // search in the vocabulary
        result = connectors.get(xLabel);
        if(result != null)
            return result;
        
        return null;
    }
    
    
    /**
     * Function to create the child element <i>component</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>component</i>.
     */
    protected Ecp createComponent() throws XMLException {
        return (Ecp) new XTPComponent();
    }
    
    
    /**
     * Function to create the child element <i>connector</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>connector</i>.
     */
    protected Ecc createConnector() throws XMLException {
        return (Ecc) new XTPConnector();
    }
}
