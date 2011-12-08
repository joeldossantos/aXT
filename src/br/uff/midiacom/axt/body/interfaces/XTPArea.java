package br.uff.midiacom.axt.body.interfaces;

import br.uff.midiacom.ana.datatype.auxiliar.SampleType;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.axt.XTPElement;
import br.uff.midiacom.axt.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPAreaPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.array.ArrayType;
import org.w3c.dom.Element;


public class XTPArea<T extends XTPArea, P extends XTPElement, I extends XTPElementImpl, Ei extends XTPInterface>
        extends XTPAreaPrototype<T, P, I, Ei> implements XTPInterface<Ei, P> {

    
    public XTPArea(String id, String xlabel) throws XMLException {
        super(id, xlabel);
    }
    
    
    public XTPArea() throws XMLException {
        super();
    }
    
    
    @Override
    public void load(Element element) throws XMLException {
        String att_var;
        
        try{
            // set the xLabel (required)
            if(!(att_var = element.getAttribute("xlabel")).isEmpty())
                setXLabel(att_var);
            else
                throw new XMLException("Could not find xlabel attribute.");
        }
        catch(XMLException ex){
            String aux = getXLabel();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("Area" + aux + ":\n" + ex.getMessage());
        }
        

        try{
            // set the id (required)
            if(!(att_var = element.getAttribute("id")).isEmpty())
                setId(att_var);
            else
                throw new XMLException("Could not find id attribute.");

            // set the coords (optional)
            if(!(att_var = element.getAttribute("coords")).isEmpty())
                setCoords(new ArrayType(att_var));

            // set the begin (optional)
            if(!(att_var = element.getAttribute("begin")).isEmpty())
                setBegin(new TimeType(att_var));

            // set the end (optional)
            if(!(att_var = element.getAttribute("end")).isEmpty())
                setEnd(new TimeType(att_var));

            // set the text (optional)
            if(!(att_var = element.getAttribute("text")).isEmpty())
                setText(att_var);

            // set the position (optional)
            if(!(att_var = element.getAttribute("position")).isEmpty()){
                try{
                    setPosition(new Integer(att_var));
                }catch(Exception e){
                    throw new XMLException("Could not set position value: " + att_var + ".");
                }
            }

            // set the first (optional)
            if(!(att_var = element.getAttribute("first")).isEmpty())
                setFirst(new SampleType(att_var));

            // set the last (optional)
            if(!(att_var = element.getAttribute("last")).isEmpty())
                setLast(new SampleType(att_var));

            // set the label (optional)
            if(!(att_var = element.getAttribute("label")).isEmpty())
                setLabel(att_var);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new XMLException("Area" + aux + ":\n" + ex.getMessage());
        }
    }
}
