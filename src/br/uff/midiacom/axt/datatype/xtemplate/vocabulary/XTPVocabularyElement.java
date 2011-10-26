package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.MaxType;
import br.uff.midiacom.xml.datatype.string.StringType;


public abstract class XTPVocabularyElement<T extends XTPVocabularyElement, P extends XTPElement, I extends XMLElementImpl> extends XMLElementPrototype<T, P, I> implements XTPLabeledElement<T, P> {

    protected StringType xlabel;
    protected Integer minOccurs;
    protected MaxType maxOccurs;

    private Integer defMinOccurs;
    private MaxType defMaxOccurs;


    public XTPVocabularyElement(String xlabel) throws XMLException {
        super();
        setXLabel(xlabel);
        defMinOccurs = 1;
        defMaxOccurs = new MaxType("unbounded");
    }


    public void setXLabel(String xlabel) throws XMLException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

        this.xlabel = new StringType(xlabel);
    }


    public String getXLabel(){
        return xlabel.getValue();
    }


    public void setMinOccurs(Integer minOccurs){
        if(minOccurs != null && minOccurs < 0)
            throw new IllegalArgumentException("minOccurs must be positive.");

        this.minOccurs = minOccurs;
    }


    public Integer getMinOccurs() {
        return minOccurs;
    }


    public Integer getDefaultMinOccurs() {
        return defMinOccurs;
    }


    public void setMaxOccurs(MaxType maxOccurs){
        this.maxOccurs = maxOccurs;
    }


    public MaxType getMaxOccurs(){
        return maxOccurs;
    }


    public MaxType getDefaultMaxOccurs() {
        return defMaxOccurs;
    }


    @Override
    public boolean compare(T other) {
        return xlabel.getValue().equals(other.getXLabel());
    }
}
