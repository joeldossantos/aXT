package br.uff.midiacom.axt.datatype.xtemplate.head;

import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.xml.Element;
import br.uff.midiacom.xml.datatype.elementList.ElementList;


public class XTPConnectorBaseType<T extends XTPConnectorBaseType, I extends NCLImport> extends Element<T> implements XTPElement<T> {

    protected ElementList<I> imports;


    public XTPConnectorBaseType() {
        imports = new ElementList<I>();
    }


    public boolean addImportBase(I importBase) {
        //If the import was inserted, set its parent
        if(imports.add(importBase)){
            importBase.setParent(this);
            return true;
        }
        return false;
    }


    public boolean removeImportBase(I importBase) {
        //If the extends was removed, remove its parent
        if(imports.remove(importBase)){
            importBase.setParent(null);
            return true;
        }
        return false;
    }


    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }


    public boolean hasImportBase() {
        return !imports.isEmpty();
    }
    
    
    public ElementList<I> getImportBases() {
        return imports;
    }


    @Override
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
