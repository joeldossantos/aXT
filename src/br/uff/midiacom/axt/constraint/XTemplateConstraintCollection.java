/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.midiacom.axt.constraint;

import AXT.XTemplateElement;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author Flavia
 */
public class XTemplateConstraintCollection extends XTemplateElement{

     private Set<XTemplateConstraint> constraints ;
     
     
     //construtores
     
     public XTemplateConstraintCollection(){
        constraints = new TreeSet<XTemplateConstraint>();
     }
     
     public XTemplateConstraintCollection(XMLReader reader, XTemplateElement parent) {
        setReader(reader);
        setParent(parent);
        getReader().setContentHandler(this);
    }
     
    //metodos de acesso

     public boolean addXTemplateConstraint(XTemplateConstraint constraint) {
         //Se a restrição for inserida com sucesso, entra no if
         //senão, retorna falso
         if(constraints.add(constraint)){
           // se a porta restrição, será atribuída ao component atual
            if(constraint != null)
                constraint.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeComponentPort(XTemplateConstraint constraint) {
         //Se a restrição for removida com sucesso, entra no if
         //senão, retorna falso
        if(constraints.remove(constraint)){
            //Se a restrição existir, retira o parentesco com o component atual
            if(constraint != null)
                constraint.setParent(null);

            return true;
        }
        return false;
    }







    public boolean hasConstraint(XTemplateConstraint constraint) {
        return constraints.contains(constraint);
    }



    public boolean hasConstraints() {
        return !constraints.isEmpty();
    }



    public Iterable<XTemplateConstraint> getConstraints() {
        return constraints;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
