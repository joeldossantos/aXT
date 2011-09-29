/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public abstract class XTemplateElement extends XMLElement {
    private String xlabel;


    public String getXLabel(){
        return this.xlabel;
    }

    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }
    
}

