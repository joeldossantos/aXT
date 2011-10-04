package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.axt.datatype.xtemplate.XTPLabeledElement;
import br.uff.midiacom.xml.Element;
import br.uff.midiacom.xml.datatype.number.MaxType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPVocabularyElement<T extends XTPVocabularyElement> extends Element<T> implements XTPLabeledElement<T> {

    protected StringType xlabel;
    protected Integer minOccurs;
    protected MaxType maxOccurs;

    private Integer defMinOccurs = 1;
    private MaxType defMaxOccurs = new MaxType("unbounded");


    public XTPVocabularyElement(String xlabel) throws NullPointerException, IllegalArgumentException {
        setXLabel(xlabel);
    }


    public void setXLabel(String xlabel) throws NullPointerException, IllegalArgumentException {
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
