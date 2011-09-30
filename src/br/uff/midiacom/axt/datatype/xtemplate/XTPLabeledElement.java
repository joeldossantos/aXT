package br.uff.midiacom.axt.datatype.xtemplate;


public interface XTPLabeledElement<T extends XTPLabeledElement> extends XTPElement<T> {


    public void setXLabel(String xlabel) throws NullPointerException, IllegalArgumentException;


    public String getXLabel();
}
