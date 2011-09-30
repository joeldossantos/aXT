package br.uff.midiacom.axt.datatype.xtemplate.vocabulary;

import br.uff.midiacom.xml.string.StringType;


public class XTPConnectorType<T extends XTPConnectorType> extends XTPVocabularyElement<T> {

    protected StringType src;
    
    
    public XTPConnectorType(String xlabel, String src) throws NullPointerException, IllegalArgumentException {
        super(xlabel);
        setSrc(src);
    }
    
    
    public void setSrc(String src) throws NullPointerException, IllegalArgumentException {
        if(src == null)
            throw new NullPointerException("Null String");

        this.src = new StringType(src);
    }


    public String getSrc(){
        return src.getValue();
    }
}
