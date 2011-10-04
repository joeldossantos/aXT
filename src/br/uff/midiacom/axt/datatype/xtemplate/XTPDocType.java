package br.uff.midiacom.axt.datatype.xtemplate;

import br.uff.midiacom.axt.datatype.xtemplate.body.XTPBodyType;
import br.uff.midiacom.axt.datatype.xtemplate.constraints.XTPConstraintsType;
import br.uff.midiacom.axt.datatype.xtemplate.head.XTPHeadType;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyType;
import br.uff.midiacom.xml.Element;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPDocType<T extends XTPDocType, H extends XTPHeadType, V extends XTPVocabularyType,B extends XTPBodyType, C extends XTPConstraintsType> extends Element<T> implements XTPElement<T> {

    protected StringType id;
    protected StringType name;
    protected StringType description;
    protected H head;
    protected B body;
    protected V vocabulary;
    protected C constraints;
    
    
    public XTPDocType(String id) throws NullPointerException, IllegalArgumentException {
        setId(id);
    }
    
    
    public void setId(String id) throws NullPointerException, IllegalArgumentException {
        if(id == null)
            throw new NullPointerException("Null id String");

        this.id = new StringType(id);
    }

    
    public String getId() {
        return id.getValue();
    }


    public void setName(String name) throws IllegalArgumentException {
        this.name = new StringType(name);
    }


    public String getName() {
        return name.getValue();
    }


    public void setDescription(String description) throws IllegalArgumentException {
        this.description = new StringType(description);
    }


    public String getDescription() {
        return description.getValue();
    }


    public void setHead(H head) {
        //Retira o parentesco do head atual
        if(this.head != null)
            this.head.setParent(null);

        this.head = head;

        //Se head existe, atribui este como seu parente
        if(this.head != null)
            this.head.setParent(this);
    }


    public H getHead() {
        return head;
    }
    
    
    public void setVocabulary(V vocabulary) {
        //Retira o parentesco do head atual
        if(this.vocabulary != null)
            this.vocabulary.setParent(null);

        this.vocabulary = vocabulary;

        //Se head existe, atribui este como seu parente
        if(this.vocabulary != null)
            this.vocabulary.setParent(this);
    }


    public V getVocabulary() {
        return vocabulary;
    }

     
    public void setBody(B body) {
        //Retira o parentesco do body atual
        if(this.body != null)
            this.body.setParent(null);

        this.body = body;

        //Se body existe, atribui este como seu parente
        if(this.body != null)
            this.body.setParent(this);
    }


    public B getBody(){
        return body;
    }


    public void setConstraints(C constraints) {
        //Retira o parentesco do body atual
        if(this.constraints != null)
            this.constraints.setParent(null);

        this.constraints = constraints;

        //Se body existe, atribui este como seu parente
        if(this.constraints != null)
            this.constraints.setParent(this);
    }


    public C getConstraints(){
        return constraints;
    }

    
    @Override
    public boolean compare(XTPDocType other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
