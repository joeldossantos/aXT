/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXT;

/**
 *
 * @author Flavia
 */
public abstract class XTPXLabeledElement extends XTPElement{

    
    private String xlabel;


    public String getXLabel(){
        return this.xlabel;
    }

    public void setXLabel(String xlabel){
        this.xlabel = xlabel;
    }
    /**metodos get e set de xlabel, serão usados nos construtores das classe que  herdam de xtemplate xlabeled element**/
}
