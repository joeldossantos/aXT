package br.uff.midiacom.axt.datatype.xtemplate.body.interfaces;

import br.uff.midiacom.ana.datatype.auxiliar.SampleType;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;
import br.uff.midiacom.xml.datatype.array.ArrayType;
import br.uff.midiacom.xml.datatype.string.StringType;


public class XTPAreaPrototype<T extends XTPAreaPrototype, P extends XTPElement, I extends XTPElementImpl, Ei extends XTPInterface>
        extends XMLIdentifiableElementPrototype<Ei, P, I> implements XTPInterface<Ei, P> {

    protected XTPVocabularyElement xlabel;
    protected ArrayType coords;
    protected TimeType begin;
    protected TimeType end;
    protected StringType text;
    protected Integer position;
    protected SampleType first;
    protected SampleType last;
    protected StringType label;
    
    
    /**
     * Construtor do elemento <i>area</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador da âncora.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da âncora for inválido.
     */
    public XTPAreaPrototype(String id, XTPVocabularyElement xlabel) throws XMLException {
        super();
        setId(id);
        setXLabel(xlabel);
    }
    
    
    public XTPAreaPrototype() throws XMLException {
        super();
    }


    public void setXLabel(XTPVocabularyElement xlabel) throws XMLException {
        if(xlabel == null)
            throw new NullPointerException("Null String.");

        this.xlabel = xlabel;
    }


    public XTPVocabularyElement getXLabel(){
        return xlabel;
    }


    /**
     * Define uma âncora de conteudo representando porções espaciais.
     * x1,y1,x2,y2,...,xN,yN
     * 
     * @param coords
     *          coordenadas da âncora espacial.
     */
    public void setCoords(ArrayType coords){
        this.coords = coords;
    }
    
    
    /**
     * Retorna as coordenadas espaciais da âncora.
     * 
     * @return
     *          array de inteiros com as coordenadas.
     */
    public ArrayType getCoords() {
        return coords;
    }
    
    
    /**
     * Define uma âncora de conteúdo representando porções temporais.
     * Caso a media que contém a âncora for do tipo "application/x-ginga-time" o tempo deve ser completo.
     * 
     * @param begin
     *          tempo de inicio da âncora temporal.
     *
     * @see TimeType
     */
    public void setBegin(TimeType begin) {
        this.begin = begin;
    }
    
    
    /**
     * Retorna o valor de inicio da âncora.
     * 
     * @return
     *          elemento representando o valor de tempo.
     */
    public TimeType getBegin() {
        return begin;
    }
    
    
    /**
     * Define uma âncora de conteúdo representando porções temporais.
     * Caso a media que contém a âncora for do tipo "application/x-ginga-time" o tempo deve ser completo.
     * 
     * @param end
     *          tempo de fim da âncora temporal.
     *
     * @see TimeType
     */
    public void setEnd(TimeType end) {
        this.end = end;
    }
    
    
    /**
     * Retorna o valor de fim da âncora.
     * 
     * @return
     *          elemento representando o valor de tempo.
     */
    public TimeType getEnd() {
        return end;
    }
    
    
    /**
     * Define uma ancora textual.
     * 
     * @param text
     *          padrao de texto usado como âncora textual.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setText(String text) throws XMLException {
        this.text = new StringType(text);
    }
    
    
    /**
     * Retorna o conteúdo da ancora textual.
     * 
     * @return
     *          String com conteúdo da âncora textual.
     */
    public String getText() {
        if(text != null)
            return text.getValue();
        else
            return null;
    }
    
    
    /**
     * Define a posição do texto da ancora textual.
     * 
     * @param position
     *          posicao do texto.
     * @throws java.lang.IllegalArgumentException
     *          se a posição for um valor negativo.
     */
    public void setPosition(Integer position) throws XMLException {
        if(position != null && position < 0)
            throw new XMLException("Invalid position");
        this.position = position;
    }
    
    
    /**
     * Retorna a posição do texto da ancora textual.
     * 
     * @return
     *          inteiro com a posicao do texto.
     */
    public Integer getPosition() {
        return position;
    }
    
    
    /**
     * Define uma ancora de conteudo com base em uma amostra.
     * 
     * @param first
     *          amostra de inicio da ancora.
     *
     * @see SampleType
     */
    public void setFirst(SampleType first) {
        this.first = first;
    }
    
    
    /**
     * Retorna o início da ancora de conteudo com base em uma amostra.
     * 
     * @return
     *          amostra de inicio da ancora.
     */
    public SampleType getFirst() {
        return first;
    }
    
    
    /**
     * Define uma ancora de conteudo com base em uma amostra.
     *
     * @param last
     *          amostra de fim da ancora.
     *
     * @see SampleType
     */
    public void setLast(SampleType last) {
        this.last = last;
    }
    
    
    /**
     * Retorna o fim da ancora de conteudo com base em uma amostra.
     *
     * @return
     *          amostra de fim da ancora.
     */
    public SampleType getLast() {
        return last;
    }
    
    
    /**
     * define uma ancora de conteudo baseada no atributo label,
     * que especifica uma cadeia de caracteres que deve ser utilizada
     * pelo exibidor de midias para identificar uma regiao de conteudo.
     * 
     * @param label
     *          identificacao da região do conteúdo.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setLabel(String label) throws XMLException {
        this.label = new StringType(label);
    }
    
    
    /**
     * Retorna o label da ancora de conteudo.
     * 
     * @return
     *          String contendo a identificacao da região do conteúdo.
     */
    public String getLabel() {
        if(label != null)
            return label.getValue();
        else
            return null;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
                
        // <area> element and attributes declaration
        content = space + "<area";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getCoords() != null)
            content += " coords='" + coords.parse() + "'";
        if(getBegin() != null)
            content += " begin='" + getBegin().parse() + "'";
        if(getEnd() != null)
            content += " end='" + getEnd().parse() + "'";
        if(getText() != null)
            content += " text='" + getText() + "'";
        if(getPosition() != null)
            content += " position='" + getPosition() + "'";
        if(getFirst() != null)
            content += " first='" + getFirst().parse() + "'";
        if(getLast() != null)
            content += " last='" + getLast().parse() + "'";
        if(getLabel() != null)
            content += " label='" + getLabel() + "'";
        content += "/>\n";
        
        return content;
    }
}
