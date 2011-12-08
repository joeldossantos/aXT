package br.uff.midiacom.axt.datatype.xtemplate;

import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLIdentifiableElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class XTPElementImpl<T extends XMLIdentifiableElement, P extends XTPElement>
        extends XMLElementImpl<T, P> {


    @Override
    protected boolean validate(String id) {
        Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");
        Matcher matcher = pattern.matcher(id);

        return matcher.matches();
    }
}
