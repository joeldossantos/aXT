package br.uff.midiacom.axt.datatype.xtemplate.body.node;

import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.datatype.enums.NCLInstanceType;
import br.uff.midiacom.ana.datatype.enums.NCLMediaType;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.datatype.ncl.node.NCLMediaPrototype;
import br.uff.midiacom.axt.datatype.auxiliar.LabeledElementList;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElement;
import br.uff.midiacom.axt.datatype.xtemplate.XTPElementImpl;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPAreaPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.body.interfaces.XTPPropertyPrototype;
import br.uff.midiacom.axt.datatype.xtemplate.vocabulary.XTPVocabularyElement;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.XMLIdentifiableElementPrototype;


public class XTPMediaPrototype<T extends XTPMediaPrototype, P extends XTPElement, I extends XTPElementImpl, Ea extends XTPAreaPrototype, Ep extends XTPPropertyPrototype, Ed extends NCLLayoutDescriptor, En extends XTPNode>
        extends XMLIdentifiableElementPrototype<En, P, I> implements XTPNode<En, P> {

    protected XTPVocabularyElement xlabel;
    protected SrcType src;
    protected NCLMimeType type;
    protected Ed descriptor;
    protected T refer;
    protected NCLInstanceType instance;
    protected LabeledElementList<Ea, T> areas;
    protected LabeledElementList<Ep, T> properties;
    
    
    public XTPMediaPrototype(String id, NCLMimeType type, XTPVocabularyElement xlabel) throws XMLException {
        super();
        setId(id);
        setType(type);
        setXLabel(xlabel);
        areas = new LabeledElementList<Ea, T>();
        properties = new LabeledElementList<Ep, T>();
    }
    
    public XTPMediaPrototype() throws XMLException {
        super();
        areas = new LabeledElementList<Ea, T>();
        properties = new LabeledElementList<Ep, T>();
    }

    
    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new XTPElementImpl<T, P>();
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
     * Atribui a URI do conteúdo da mídia.
     * 
     * @param src
     *          String contendo a URI do conteúdo da mídia.
     * @throws java.net.URISyntaxException
     *          se a URI não for válida.
     *
     * @see java.net.URI
     */
    public void setSrc(SrcType src) {
        this.src = src;
    }
    
    
    /**
     * Retorna o valor da URI do conteúdo da mídia.
     * 
     * @return
     *          String contendo a URI do conteúdo da mídia.
     *
     * @see NCLMediaPrototype#setSrc(java.lang.String)
     * @see NCLMediaPrototype#setSrc(br.pensario.datatype.NCLUriType, java.lang.String)
     * @see NCLMediaPrototype#setSrc(br.pensario.interfaces.TimeType)
     */
    public SrcType getSrc() {
        return src;
    }
    
    
    /**
     * Determina o tipo da mídia.
     * O tipo da mídia será um dos tipos padronizados na norma.
     * 
     * @param type
     *          tipo da mídia.
     */
    public void setType(NCLMimeType type) {
        this.type = type;
    }
    
    
    /**
     * Retorna o tipo da mídia.
     * 
     * @return
     *          tipo da mídia.
     */
    public NCLMimeType getType() {
        return type;
    }
    
    
    /**
     * Determina o descritor da mídia.
     * 
     * @param descriptor
     *          elemento representando o descritor da mídia.
     */
    public void setDescriptor(Ed descriptor) {
        this.descriptor = descriptor;
    }
    
    
    /**
     * Retorna o descritor da mídia.
     * 
     * @return
     *          elemento representando o descritor da mídia.
     */
    public Ed getDescriptor() {
        return descriptor;
    }


    /**
     * Atribui uma media para ser reutilizada pela media.
     *
     * @param refer
     *          elemento representando a media a ser reutilizado.
     */
    public void setRefer(T refer) {
        this.refer = refer;
    }


    /**
     * Retorna a media reutilizada pela media.
     *
     * @return
     *          elemento representando a media a ser reutilizado.
     */
    public T getRefer() {
        return refer;
    }


    /**
     * Determina o tipo de instância de outra media essa media é.
     *
     * @param instance
     *          elemento representando o tipo de instancia.
     */
    public void setInstance(NCLInstanceType instance) {
        this.instance = instance;
    }


    /**
     * Retorna o tipo de instância de outra media essa media é.
     *
     * @return
     *          elemento representando o tipo de instancia.
     */
    public NCLInstanceType getInstance() {
        return instance;
    }
    
    
    /**
     * Adiciona uma âncora a mídia.
     * 
     * @param area
     *          elemento representando a âncora a ser adicionada.
     * @return
     *          Verdadeiro se a âncora foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addArea(Ea area) throws XMLException {
        return areas.add(area, (T) this);
    }


    /**
     * Remove uma âncora a mídia.
     *
     * @param area
     *          elemento representando a âncora a ser removida.
     * @return
     *          Verdadeiro se a âncora foi removida.
     *
     * @see TreeSet#add
     */
    public boolean removeArea(Ea area) throws XMLException {
        return areas.remove(area);
    }
    
    
    /**
     * Remove uma âncora da mídia.
     *
     * @param id
     *          identificador da âncora a ser removida.
     * @return
     *          Verdadeiro se a âncora foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeArea(String id) throws XMLException {
        return areas.remove(id);
    }


    /**
     * Verifica se a mídia possui uma âncora.
     * 
     * @param area
     *          elemento representando a âncora a ser verificada.
     * @return
     *          verdadeiro se a âncora existir.
     */
    public boolean hasArea(Ea area) throws XMLException {
        return areas.contains(area);
    }
    
    
    /**
     * Verifica se a mídia possui uma âncora.
     * 
     * @param id
     *          identificador da âncora a ser verificada.
     * @return
     *          verdadeiro se a âncora existir.
     */
    public boolean hasArea(String id) throws XMLException {
        return areas.get(id) != null;
    }
    
    
    /**
     * Verifica se a mídia possui alguma âncora.
     * 
     * @return
     *          verdadeiro se a mídia possuir alguma âncora.
     */
    public boolean hasArea() {
        return !areas.isEmpty();
    }
    
    
    /**
     *  Retorna as âncoras da mídia.
     *
     * @return
     *          lista contendo as âncoras da mídia.
     */
    public LabeledElementList<Ea, T> getAreas() {
        return areas;
    }
    
    
    /**
     * Adiciona uma propriedade a mídia.
     *
     * @param property
     *          elemento representando a propriedade a ser adicionada.
     * @return
     *          Verdadeiro se a propriedade foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addProperty(Ep property) throws XMLException {
        return properties.add(property, (T) this);
    }


    /**
     * Remove uma propriedade da mídia.
     *
     * @param property
     *          elemento representando a propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(Ep property) throws XMLException {
        return properties.remove(property);
    }
    

    /**
     * Remove uma propriedade da mídia.
     *
     * @param name
     *          nome da propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(String name) throws XMLException {
        return properties.remove(name);
    }


    /**
     * Verifica se a mídia possui uma propriedade.
     *
     * @param property
     *          elemento representando a propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(Ep property) throws XMLException {
        return properties.contains(property);
    }


    /**
     * Verifica se a mídia possui uma propriedade.
     *
     * @param name
     *          nome da propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(String name) throws XMLException {
        return properties.get(name) != null;
    }


    /**
     * Verifica se a mídia possui alguma propriedade.
     *
     * @return
     *          verdadeiro se a mídia possuir alguma propriedade.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }


    /**
     * Retorna as propriedades da mídia.
     *
     * @return
     *          lista contendo as propriedades da mídia.
     */
    public LabeledElementList<Ep, T> getProperties() {
        return properties;
    }


    /**
     * Retorna o tipo da mídia de acordo com seu tipo especificado ou sua URL.
     *
     * @return
     *          elemento representando o tipo da mídia.
     */
    public NCLMediaType getMediaType() {
        if(getType() != null){
            boolean status = false;

            status |= (getType() == NCLMimeType.APPLICATION_X_GINGA_NCLET);
            status |= (getType() == NCLMimeType.APPLICATION_X_GINGA_NCLUA);
            if(status)
                return NCLMediaType.PROCEDURAL;

            status |= (getType() == NCLMimeType.AUDIO_BASIC);
            status |= (getType() == NCLMimeType.AUDIO_MP2);
            status |= (getType() == NCLMimeType.AUDIO_MP3);
            status |= (getType() == NCLMimeType.AUDIO_MPEG);
            status |= (getType() == NCLMimeType.AUDIO_MPEG4);
            if(status)
                return NCLMediaType.AUDIO;

            status |= (getType() == NCLMimeType.IMAGE_BMP);
            status |= (getType() == NCLMimeType.IMAGE_GIF);
            status |= (getType() == NCLMimeType.IMAGE_JPEG);
            status |= (getType() == NCLMimeType.IMAGE_PNG);
            if(status)
                return NCLMediaType.IMAGE;

            status |= (getType() == NCLMimeType.VIDEO_MPEG);
            if(status)
                return NCLMediaType.VIDEO;

            status |= (getType() == NCLMimeType.TEXT_CSS);
            status |= (getType() == NCLMimeType.TEXT_HTML);
            status |= (getType() == NCLMimeType.TEXT_PLAIN);
            status |= (getType() == NCLMimeType.TEXT_XML);
            if(status)
                return NCLMediaType.TEXT;
        }

        if(getSrc() != null){
            boolean status = false;
            String ext = getSrc().getExtension();

            status |= ext.contentEquals(".html");
            status |= ext.contentEquals(".html");
            status |= ext.contentEquals(".xhtml");
            status |= ext.contentEquals(".css");
            status |= ext.contentEquals(".xml");
            status |= ext.contentEquals(".txt");
            if(status)
                return NCLMediaType.TEXT;

            status |= ext.contentEquals(".bmp");
            status |= ext.contentEquals(".png");
            status |= ext.contentEquals(".gif");
            status |= ext.contentEquals(".jpg");
            status |= ext.contentEquals(".jpeg");
            status |= ext.contentEquals(".jpe");
            if(status)
                return NCLMediaType.IMAGE;

            status |= ext.contentEquals(".ua");
            status |= ext.contentEquals(".wav");
            status |= ext.contentEquals(".mp1");
            status |= ext.contentEquals(".mp2");
            status |= ext.contentEquals(".mp3");
            status |= ext.contentEquals(".mp4");
            status |= ext.contentEquals(".mpg4");
            if(status)
                return NCLMediaType.AUDIO;

            status |= ext.contentEquals(".mpeg");
            status |= ext.contentEquals(".mpg");
            status |= ext.contentEquals(".mpe");
            status |= ext.contentEquals(".mng");
            status |= ext.contentEquals(".qt");
            status |= ext.contentEquals(".mov");
            status |= ext.contentEquals(".avi");
            if(status)
                return NCLMediaType.VIDEO;

            status |= ext.contentEquals(".class");
            status |= ext.contentEquals(".xlet");
            status |= ext.contentEquals(".xlt");
            status |= ext.contentEquals(".lua");
            if(status)
                return NCLMediaType.PROCEDURAL;
        }

        return NCLMediaType.OTHER;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <media> element and attributes declaration
        content = space + "<media";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getSrc() != null)
            content += " src='" + getSrc().parse() + "'";
        if(getType() != null)
            content += " type='" + getType().toString() + "'";
        if(getDescriptor() != null)
            content += " descriptor='" + getDescriptor().getId() + "'";
        if(getRefer() != null)
            content += " refer='" + getRefer().getId() + "'";
        if(getInstance() != null)
            content += " instance='" + getInstance().toString() + "'";
        
        // Test if the media has content
        if(hasArea() || hasProperty()){
            content += ">\n";
            
            if(hasArea()){
                for(Ea area : areas)
                    content += area.parse(ident + 1);
            }
            if(hasProperty()){
                for(Ep prop : properties)
                    content += prop.parse(ident + 1);
            }
            
            content += space + "</media>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
}
