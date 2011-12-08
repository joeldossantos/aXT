package br.uff.midiacom.axt.datatype.xtemplate.body.link;

import br.uff.midiacom.ana.connector.NCLRole;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPInterface;
import br.uff.midiacom.axt.datatype.xtemplate.body.node.XTPNode;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.util.Iterator;


public class XTPBindPrototype<T extends XTPBindPrototype, P extends XTPElement, I extends XTPElementImpl, Er extends NCLRole, En extends XTPNode, Ei extends XTPInterface, Ed extends NCLLayoutDescriptor, Ep extends XTPParamPrototype>
        extends XMLElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected Er role;
    protected StringType select;
    protected ElementList<Ep, T> bindParams;


    public XTPBindPrototype() throws XMLException {
        super();
        bindParams = new ElementList<Ep, T>();
    }


    /**
     * Atribui um papel ao bind.
     * 
     * @param role
     *          elemento representando o papel ao qual o bind será associado.
     */
    public void setRole(Er role) {
        this.role = role;
    }
    
    
    /**
     * Retorna o papel relacionado ao bind.
     * 
     * @return
     *          elemento representando o papel ao qual o bind será associado.
     */
    public Er getRole() {
        return role;
    }


    public void setSelect(String select) throws XMLException {
        this.select = new StringType(select);
    }


    public String getSelect() {
        return select.getValue();
    }
    
    
    /**
     * Adiciona um parâmetro ao bind.
     *
     * @param param
     *          elemento representando o parâmetro a ser adicionado.
     * @return
     *          verdadeiro se o parâmetro foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addBindParam(Ep param) throws XMLException {
        return bindParams.add(param, (T) this);
    }


    /**
     * Remove um parâmetro do bind.
     *
     * @param param
     *          elemento representando o parâmetro a ser removido.
     * @return
     *          verdadeiro se o parâmetro foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeBindParam(Ep param) throws XMLException {
        return bindParams.remove(param);
    }


    /**
     * Verifica se o bind possui um parâmetro.
     *
     * @param param
     *          elemento representando o parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasBindParam(Ep param) throws XMLException {
        return bindParams.contains(param);
    }


    /**
     * Verifica se o bind possui algum parâmetro.
     *
     * @return
     *          verdadeiro se o bind possui algum parâmetro.
     */
    public boolean hasBindParam() {
        return !bindParams.isEmpty();
    }


    /**
     * Retorna os parâmetros do bind.
     *
     * @return
     *          lista contendo os parâmetros do bind.
     */
    public ElementList<Ep, T> getBindParams() {
        return bindParams;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <bind> element and attributes declaration
        content = space + "<bind";
        if(getRole() != null)
            content += " role='" + getRole().getName() + "'";
        
        // <bind> element content
        if(hasBindParam()){
            content += ">\n";

            for(Ep param : bindParams)
                content += param.parse(ident + 1);
            
            content += space + "</bind>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
    
    
    public boolean compare(T other) {
        boolean comp = true;

        String this_bind, other_bind;

        // Compara pelo role
        if(getRole() == null) this_bind = ""; else this_bind = getRole().getName();
        if(other.getRole() == null) other_bind = ""; else other_bind = other.getRole().getName();
        comp &= this_bind.equals(other_bind);

        // Compara o número de parâmetros
        comp &= bindParams.size() == other.getBindParams().size();

        // Compara os parâmetros
        Iterator it = other.getBindParams().iterator();
        for(Ep param : bindParams){
            if(!it.hasNext())
                continue;
            Ep other_param = (Ep) it.next();
            comp = param.compare(other_param);
            if(comp)
                break;
        }


        return comp;
    }
}


