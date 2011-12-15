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
package br.uff.midiacom.axt;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.datatype.auxiliar.PostReferenceElement;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.axt.head.XTPHead;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.io.File;
import java.util.ArrayList;


public class ReferenceManager {

    private static ReferenceManager instance = new ReferenceManager();
    private ArrayList<PostReferenceElement> references = new ArrayList<PostReferenceElement>();
    
    
    private ReferenceManager() {}
    
    
    public static ReferenceManager getInstance() {
        return instance;
    }
    
    
    public NCLImport getImport(String reference, ElementList<NCLImport,NCLElement> imports) throws XMLException {
        String alias = getAlias(reference);
        
        for(NCLImport importBase : imports){
            if(importBase.getAlias().equals(alias))
                return importBase;
        }
        
        return null;
    }
    
    
    public String getAlias(String reference) throws XMLException {
        int index = reference.indexOf("#");
        if(index == -1)
            throw new XTPParsingException("The referred src does not have an alias");
        else
            return reference.substring(index);
    }
    
    
    public String getId(String reference) throws XMLException {
        int index = reference.indexOf("#");
        if(index == -1)
            return reference;
        else if(index == reference.length()-1)
            throw new XTPParsingException("The referred src does not have an id");
        else
            return reference.substring(index+1, reference.length());
    }
    
    
    public NCLDescriptor findDescriptorReference(XTPDoc doc, String id) throws XMLException {
        XTPHead xtphead;
        NCLImport imp;
        NCLDoc ncldoc;
        NCLHead nclhead;
        NCLDescriptorBase base, nclbase;
        NCLDescriptor result = null;
        
        // get the xtemplate head
        xtphead = (XTPHead) doc.getHead();
        if(xtphead == null)
            throw new XTPParsingException("Could not find document head element");
        
        // get the xtemplate base
        base = (NCLDescriptorBase) xtphead.getDescriptorBase();
        if(base == null)
            throw new XTPParsingException("Could not find document descriptorBase element");

        // get the imported bases
        imp = getImport(id, base.getImportBases());
        if(imp == null)
            throw new XTPParsingException("Could not find importBase");
        
        // get the imported document src
        String src = imp.getDocumentURI().parse();
        ncldoc = new NCLDoc();
        ncldoc.loadXML(new File(src));

        nclhead = (NCLHead) ncldoc.getHead();
        if(nclhead == null)
            throw new XTPParsingException("Could not find NCL head");

        nclbase = (NCLDescriptorBase) nclhead.getDescriptorBase();
        if(nclbase == null)
            throw new XTPParsingException("Could not find NCL descriptorBase");

        result = (NCLDescriptor) nclbase.findDescriptor(getId(id));
        if(result == null)
            throw new XTPParsingException("Could not find descriptor with id: " + id);
        
        return result;
    }
    
    
    public NCLTestRule findRuleReference(XTPDoc doc, String id) throws XMLException {
        XTPHead xtphead;
        NCLImport imp;
        NCLDoc ncldoc;
        NCLHead nclhead;
        NCLDescriptorBase base;
        NCLRuleBase nclbase;
        NCLTestRule result = null;
        
        xtphead = (XTPHead) doc.getHead();
        if(xtphead == null)
            throw new XTPParsingException("Could not find document head element");
        
        base = (NCLDescriptorBase) xtphead.getDescriptorBase();
        if(base == null)
            throw new XTPParsingException("Could not find document descriptorBase element");

        // get the imported bases
        imp = getImport(id, base.getImportBases());
        if(imp == null)
            throw new XTPParsingException("Could not find importBase");
        
        // get the imported document src
        String src = imp.getDocumentURI().parse();
        ncldoc = new NCLDoc();
        ncldoc.loadXML(new File(src));

        nclhead = (NCLHead) ncldoc.getHead();
        if(nclhead == null)
            throw new XTPParsingException("Could not find NCL head");

        nclbase = (NCLRuleBase) nclhead.getRuleBase();
        if(nclbase == null)
            throw new XTPParsingException("Could not find NCL descriptorBase");

        result = (NCLTestRule) nclbase.findRule(getId(id));
        if(result == null)
            throw new XTPParsingException("Could not find descriptor with id: " + id);
        
        return result;
    }


    public NCLCausalConnector findConnectorReference(XTPDoc doc, String src) throws XMLException {
        XTPHead xtphead;
        NCLImport imp;
        NCLDoc ncldoc;
        NCLHead nclhead;
        NCLDescriptorBase base;
        NCLConnectorBase nclbase;
        NCLCausalConnector result = null;
        
        xtphead = (XTPHead) doc.getHead();
        if(xtphead == null)
            throw new XTPParsingException("Could not find document head element");
        
        base = (NCLDescriptorBase) xtphead.getDescriptorBase();
        if(base == null)
            throw new XTPParsingException("Could not find document descriptorBase element");

        // get the imported bases
        imp = getImport(src, base.getImportBases());
        if(imp == null)
            throw new XTPParsingException("Could not find importBase");
        
        // get the imported document src
        String docsrc = imp.getDocumentURI().parse();
        ncldoc = new NCLDoc();
        ncldoc.loadXML(new File(docsrc));

        nclhead = (NCLHead) ncldoc.getHead();
        if(nclhead == null)
            throw new XTPParsingException("Could not find NCL head");

        nclbase = (NCLConnectorBase) nclhead.getConnectorBase();
        if(nclbase == null)
            throw new XTPParsingException("Could not find NCL descriptorBase");

        result = (NCLCausalConnector) nclbase.getCausalConnectors().get(getId(src));
        if(result == null)
            throw new XTPParsingException("Could not find connector in connectorBase with id: " + src);
        
        return result;
    }
}
