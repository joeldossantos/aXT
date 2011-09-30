package br.uff.midiacom.axt;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.XMLReader;


public abstract class XMLElement extends DefaultHandler {

    private XMLElement parent;
    private XMLReader reader;
    
    private List<String> warnings = new ArrayList<String>();
    private List<String> errors = new ArrayList<String>();


    /**
     * Atribui um elemento pai ao elemento NCL.
     *
     * @param parent
     *          elemento NCL representando o elemento pai.
     * @return
     *          verdadeiro se o elemento pai foi atribuido. Caso o elemento já possua um elemento pai, o retorno será falso.
     */
    public boolean setParent(XMLElement parent) {
        if(this.parent != null && parent != null)
            return false;

        this.parent = parent;
        return true;
    }


    /**
     * Retorna o elemento pai do elemento NCL.
     *
     * @return
     *          elemento NCL representando o elemento pai.
     */
    public XMLElement getParent() {
        return parent;
    }


    /**
     * Atribui um leitor XML ao elemento NCL.
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @return
     *          verdadeiro se o leitor XML foi atribuido. Caso o elemento já possua um leitor, o retorno será falso.
     */
    public boolean setReader(XMLReader reader) {
        if(this.reader != null && reader != null)
            return false;

        this.reader = reader;
        return true;
    }


    /**
     * Retorna o leitor XML do elemento NCL.
     *
     * @return
     *          elemento representando o leitor XML do parser SAX.
     */
    public XMLReader getReader() {
        return reader;
    }




    /**
     * Cria o código XML do elemento da <i>Nested Context Language</i> (NCL).<br>
     *
     * @param ident
     *          Inteiro indicando o nível de indentação do elemento, ou seja,
     *          o número de tabulações usadas na indentação do elemento.
     *
     * @return
     *          String contendo o código XML do elemento.
     */
    //public abstract String parse(int ident);


    /**
     * Adiciona uma mensagem de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param warning
     *          String contendo a mensagem de aviso.
     */
    public void addWarning(String warning) {
        warnings.add(warning);
    }


    /**
     * Adiciona mensagens de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param warnings
     *          Lista contendo mensagens de aviso.
     */
    public void addWarning(List<String> warnings) {
        for(String warning : warnings)
            addWarning(warning);
    }


    /**
     * Retorna a mensagem de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @return
     *          lista de mensagens de aviso.
     */
    public List<String> getWarnings() {
        return warnings;
    }


    /**
     * Limpa a lista de mensagens de aviso.
     */
    public void cleanWarnings() {
        warnings.clear();
    }


    /**
     * Adiciona uma mensagem de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param error
     *          String contendo a mensagem de erro.
     */
    public void addError(String error) {
        errors.add(error);
    }


    /**
     * Adiciona mensagens de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param errors
     *          Lista contendo mensagens de erro.
     */
    public void addError(List<String> errors) {
        for(String error : errors)
            addError(error);
    }


    /**
     * Retorna a mensagem de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @return
     *          lista de mensagens de erro.
     */
    public List<String> getErrors() {
        return errors;
    }


    /**
     * Limpa a lista de mensagens de erro.
     */
    public void cleanErrors() {
        errors.clear();
    }


    /**
     * Implementa o método startElement do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     *
    @Override
    public abstract void startElement(String uri, String localName, String qName, Attributes attributes);


    /**
     * Implementa o método endElement do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     *
    @Override
    public void endElement(String uri, String localName, String qName) {
        if(getParent() != null)
            getReader().setContentHandler(getParent());
    }


    /**
     * Implementa o método endDocument do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     *
    @Override
    public void endDocument() {}

*/

}
