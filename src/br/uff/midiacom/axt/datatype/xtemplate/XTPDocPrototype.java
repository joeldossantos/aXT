package br.uff.midiacom.axt.datatype.xtemplate;

import br.uff.midiacom.axt.datatype.xtemplate.body.XTPBodyPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.constraints.XTPConstraintsPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.head.XTPHeadPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPDocPrototype<T extends XTPDocPrototype, P extends XTPElement, I extends XTPElementImpl, Eh extends XTPHeadPrototype, Ev extends XTPVocabularyPrototype, Eb extends XTPBodyPrototype, Ec extends XTPConstraintsPrototype>
        extends XMLIdentifiableElementPrototype<T, P, I> implements XTPElement<T, P> {

    protected StringType name;
    protected StringType description;
    protected Eh head;
    protected Eb body;
    protected Ev vocabulary;
    protected Ec constraints;
    
    
    public XTPDocPrototype(String id) throws XMLException {
        setId(id);
    }


    public void setName(String name) throws XMLException {
        this.name = new StringType(name);
    }


    public String getName() {
        return name.getValue();
    }


    public void setDescription(String description) throws XMLException {
        this.description = new StringType(description);
    }


    public String getDescription() {
        return description.getValue();
    }


    public void setHead(Eh head) {
        //Retira o parentesco do head atual
        if(this.head != null)
            this.head.setParent(null);

        this.head = head;

        //Se head existe, atribui este como seu parente
        if(this.head != null)
            this.head.setParent(this);
    }


    public Eh getHead() {
        return head;
    }
    
    
    public void setVocabulary(Ev vocabulary) {
        //Retira o parentesco do head atual
        if(this.vocabulary != null)
            this.vocabulary.setParent(null);

        this.vocabulary = vocabulary;

        //Se head existe, atribui este como seu parente
        if(this.vocabulary != null)
            this.vocabulary.setParent(this);
    }


    public Ev getVocabulary() {
        return vocabulary;
    }

     
    public void setBody(Eb body) {
        //Retira o parentesco do body atual
        if(this.body != null)
            this.body.setParent(null);

        this.body = body;

        //Se body existe, atribui este como seu parente
        if(this.body != null)
            this.body.setParent(this);
    }


    public Eb getBody(){
        return body;
    }


    public void setConstraints(Ec constraints) {
        //Retira o parentesco do body atual
        if(this.constraints != null)
            this.constraints.setParent(null);

        this.constraints = constraints;

        //Se body existe, atribui este como seu parente
        if(this.constraints != null)
            this.constraints.setParent(this);
    }


    public Ec getConstraints(){
        return constraints;
    }

    
    @Override
    public boolean compare(XTPDocPrototype other) {
        return getId().equals(other.getId());
    }
    
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
