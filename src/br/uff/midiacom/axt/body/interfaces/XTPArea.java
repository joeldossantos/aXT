package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPAreaPrototype;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class XTPArea<T extends XTPArea, P extends XTPElement, I extends XMLElementImpl, Ei extends XTPInterface> extends XTPAreaPrototype<T, P, I, Ei> implements XTPInterface<Ei, P> {

    
    public XTPArea(String id, String xlabel) throws XMLException {
        super(id, xlabel);
    }
    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("id"))
//                    setId(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("xlabel")){
//                    setXLabel(attributes.getValue(i));
//                }
//                else if(attributes.getLocalName(i).equals("coords")){
//                    Vector<Integer>  coord = new Vector<Integer>();
//                    String value = attributes.getValue(i);
//                    while(value.contains(",")){
//                        int index = value.indexOf(",");
//                        coord.add(new Integer(value.substring(0, index)));
//                        value = value.substring(index + 1);
//                    }
//                    coord.add(new Integer(value));
//                    int[] a = new int[coord.size()];
//                    for(int k = 0; k < coord.size(); k++)
//                        a[k] = (int) coord.elementAt(k);
//                    setCoords(a);
//                }
//                else if(attributes.getLocalName(i).equals("begin"))
//                    setBegin(new NCLTime(attributes.getValue(i)));
//                else if(attributes.getLocalName(i).equals("end"))
//                    setEnd(new NCLTime(attributes.getValue(i)));
//                else if(attributes.getLocalName(i).equals("text"))
//                    setText(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("position"))
//                    setPosition(new Integer(attributes.getValue(i)));
//                else if(attributes.getLocalName(i).equals("first"))
//                    setFirst(new NCLSample(attributes.getValue(i)));
//                else if(attributes.getLocalName(i).equals("last"))
//                    setLast(new NCLSample(attributes.getValue(i)));
//                else if(attributes.getLocalName(i).equals("label"))
//                    setLabel(attributes.getValue(i));
//            }
//        }
//        catch(Exception ex){
//            addError(ex.getMessage());
//        }
//    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
