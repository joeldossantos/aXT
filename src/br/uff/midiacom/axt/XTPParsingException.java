package br.uff.midiacom.axt;

import br.uff.midiacom.xml.XMLException;


public class XTPParsingException extends XMLException {


    public XTPParsingException() {
        super();
    }


    public XTPParsingException(String msg) {
        super(msg);
    }


    public XTPParsingException(Throwable ex) {
        super(ex);
    }


    public XTPParsingException(String msg, Throwable ex) {
        super(msg,ex);
    }
}
