/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana;

import AXT.XMLElement;
import br.uff.midiacom.ana.datatype.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.NCLElementSets;
import br.uff.midiacom.ana.datatype.NCLNotifier;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.XMLReader;


/**
 * Esta classe define um elemento da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public abstract class NCLElement extends XMLElement{

 
    private NCLModificationListener listener;
   




    /**
     * Atribui um ouvinte para notificações de mudança do elemento. Caso o
     * argumento seja nulo, não utilizará nenhum ouvinte.
     *
     * @param listener
     *          objeto que receberá as notificações.
     */
    public void setModificationListener(NCLModificationListener listener) {
        this.listener = listener;
    }


    /**
     * Retorna o ouvinte para notificações de mudança do elemento.
     *
     * @return
     *          objeto que recebe as notificações ou null se nenhum ouvinte
     *          estiver assiciado.
     */
    public NCLModificationListener getModificationListener() {
        return listener;
    }


    


    
    

    


   


    /**
     * Notify the listener about a child node inserted.
     *
     * @param setName
     *          name of the child set.
     * @param inserted
     *          element inserted.
     */
    protected void notifyInserted(NCLElementSets setName, Element inserted) {
        if(listener != null)
            (new NCLNotifier(0, listener, this, setName, inserted)).start();
    }


    /**
     * Notify the listener about a child node removed.
     *
     * @param setName
     *          name of the child set.
     * @param inserted
     *          element removed.
     */
    protected void notifyRemoved(NCLElementSets setName, Element removed) {
        if(listener != null)
            (new NCLNotifier(1, listener, this, setName, removed)).start();
    }


    /**
     * Notify the listener about an attribute changed.
     *
     * @param attributeName
     *          the attribute changed.
     * @param oldValue
     *          the attribute old value.
     * @param newValue
     *          the attribute new value.
     */
    protected void notifyAltered(NCLElementAttributes attributeName, Object oldValue, Object newValue) {
        if(listener != null)
            (new NCLNotifier(listener, this, attributeName, oldValue, newValue)).start();
    }
}
