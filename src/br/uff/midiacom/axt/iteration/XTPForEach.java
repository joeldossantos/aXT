package br.uff.midiacom.axt.iteration;

import br.uff.midiacom.axt.context.XTPContext;
import br.uff.midiacom.axt.link.XTPLink;
import br.uff.midiacom.axt.Media.XTPArea;
import br.uff.midiacom.axt.Media.XTPProperty;
import br.uff.midiacom.axt.port.XTPPort;
import br.uff.midiacom.axt.Switch.XTPSwitch;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.interfaces.NCLArea;
import br.uff.midiacom.ana.interfaces.NCLPort;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import br.uff.midiacom.ana.rule.NCLRule;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 *
 * @author flavia
 */
public class XTPForEach<L extends NCLLink,Pt extends NCLPort, A extends NCLArea, Pp extends NCLProperty,
        S extends NCLSwitch, V extends XTPVariable, D extends NCLLayoutDescriptor,
        Cn extends NCLCausalConnector, R extends NCLRule> extends XTPElement{
    
    private String select;
    private XMLElement selectedElement;
    private Set<V> variables = new TreeSet<V>();

    private Set<L> links = new TreeSet<L>();
    private Set<Pt> ports = new TreeSet<Pt>();
    private Set<A> areas = new TreeSet<A>();
    private Set<Pp> properties = new TreeSet<Pp>();
    private Set<S> switches = new TreeSet<S>();

    //construtores

    public XTPForEach(){}

    public XTPForEach(String select){
        this.select = select;
    }

    public XTPForEach(XMLReader reader, XMLElement parent) {
        setReader(reader);
        setParent(parent);
        getReader().setContentHandler(this);
    }

    //metodos de acesso

    public String getSelect(){
        return this.select;
    }

    public void setSelect(String select){
        this.select = select;
    }

    public String getSelectedComponentXLabel(String select){
                    String pxlabel;
                    if(select.contains("@xlabel")){
                        pxlabel = select.substring(select.indexOf('@')+1);


                        String[] split =  pxlabel.split("'", 3);
                        pxlabel = split[1];

                        return pxlabel;
                    }
                    else{
                    return pxlabel = "null";
                    }


                }
     public String getSelectedInterfaceXLabel(String select){
            String pxlabel;
                    if(select.contains("@xlabel")){
                        pxlabel = select.substring(select.indexOf('@')+1);
                        if(pxlabel.contains("@xlabel")){
                            pxlabel = pxlabel.substring(pxlabel.indexOf('@'));
                            String[] split =  pxlabel.split("'", 3);
                            pxlabel = split[1];
                            return pxlabel;
                        }
                    }
                    return null;
    }

    public boolean addXTLink(L link) {
        if(links.add((link))){
            
            if(link != null)
                link.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeXTLink(L link) {
        if(links.remove(link)){
            
            if(link != null)
                link.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasXTLink(L link) {
        return links.contains(link);
    }


    public boolean hasXTLink() {
        return !links.isEmpty();
    }


    public Iterable<L> getXTLink() {
        return links;
    }
    
    public boolean addXTPort(Pt port) {
        if(ports.add(port)){
            
            if(port != null)
                port.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeXTPort(Pt port) {
        if(ports.remove(port)){
            
            if(port != null)
                port.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasXTPort(Pt port) {
        return ports.contains(port);
    }


    public boolean hasXTPort() {
        return !ports.isEmpty();
    }


    public Iterable<Pt> getXTPorts() {
        return ports;
    }
     
    public boolean addXTArea(A area) {
        if(areas.add(area)){
            
            if(area != null)
                area.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeXTArea(A area) {
        if(areas.remove(area)){
            
            if(area != null)
                area.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasXTArea(A area) {
        return areas.contains(area);
    }


    public boolean hasXTArea() {
        return !areas.isEmpty();
    }


    public Iterable<A> getXTArea() {
        return areas;
    }
    
    public boolean addXTProperty(Pp property) {
        if(properties.add(property)){
            
            if(property != null)
                property.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeXTProperty(Pp property) {
        if(properties.remove(property)){
            
            if(property != null)
                property.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasXTProperty(Pp property) {
        return properties.contains(property);
    }


    public boolean hasXTProperty() {
        return !properties.isEmpty();
    }


    public Iterable<Pp> getXTPropeties() {
        return properties;
    }

    public boolean addXTSwitch(S XTswitch) {
        if(switches.add(XTswitch)){

            if(XTswitch != null)
                XTswitch.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeXTSwitch(S XTswitch) {
        if(switches.remove(XTswitch)){

            if(XTswitch != null)
                XTswitch.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasXTSwitch(S xtswitch) {
        return switches.contains(xtswitch);
    }


    public boolean hasXTSwitch() {
        return !switches.isEmpty();
    }


    public Iterable<S> getXTSwitches() {
        return switches;
    }

    public boolean addVariable(V variable) {
        if(variables.add(variable)){

            if(variable != null)
                variable.setParent(this);

            return true;
        }
        return false;
    }

    public boolean removeVariable(V variable) {
        if(variables.remove(variable)){

            if(variable != null)
                variable.setParent(null);

            return true;
        }
        return false;
    }

    public boolean hasVariable(V variable) {
        return variables.contains(variable);
    }


    public boolean hasVariable() {
        return !variables.isEmpty();
    }


    public Iterable<V> getVariables() {
        return variables;
    }

    public XMLElement getSelectedElement(){
        return this.selectedElement;
    }

    public void setSelectedElement(XMLElement element){
        this.selectedElement = element;
    }
    private void SelectedComponentReference(){
        XMLElement root = getParent();
        while(!(root instanceof XTPDoc)){
            root = root.getParent();
        }

        if(this.getSelectedInterfaceXLabel(select)!=null){
            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedInterfaceXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }
        if(this.getSelectedComponentXLabel(select)!=null){
            XMLElement component = ((XTPDoc)root).getVocabulary().findComponent(getSelectedComponentXLabel(select));
            if(component!=null){
                setSelectedElement(component);
                return;
            }
        }

        addWarning("Could not find selected Element with xlabel"+getSelectedComponentXLabel(select)+"with interface"+getSelectedInterfaceXLabel(select));
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
      try{
        System.out.println("entrou no start element do foreach");
        if(localName.equals("for-each")){
            
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("select"))
                        setSelect(attributes.getValue(i));
                }
        }

        else if(localName.equals("area")){
                System.out.println("entrou no startElement d elemento media");
                A child = createArea();
                child.startElement(uri, localName, qName, attributes);
                addXTArea(child);
        }
        else if(localName.equals("property")){
                Pp child = createProperty();
                child.startElement(uri, localName, qName, attributes);
                addXTProperty(child);
            }
            else if(localName.equals("port")){
                Pt child = createPort();
                child.startElement(uri, localName, qName, attributes);
                addXTPort(child);
            }
            else if(localName.equals("switch")){

                S child = createSwitch();
                child.startElement(uri, localName, qName, attributes);
                addXTSwitch(child);
            }
            else if(localName.equals("link")){
                L child = createLink();
                child.startElement(uri, localName, qName, attributes);
                addXTLink(child);
            }

            else if(localName.equals("variable")){
                V child = createVariable();
                child.startElement(uri, localName, qName, attributes);
                addVariable(child);
            }

        }
        catch(Exception ex){
            addError(ex.getMessage());
        }
    }

    @Override
    public void endDocument(){
        if(getSelect() != null)
            SelectedComponentReference();
        if(hasVariable()){
            for(V var : variables){
                var.endDocument();
                addWarning(var.getWarnings());
                addError(var.getErrors());
            }
            //variableReference();
        }
        if(hasXTArea()){

            for(A area : areas){
                area.endDocument();
                addWarning(area.getWarnings());
                addError(area.getErrors());
            }
        }
        if(hasXTPort()){
            for(Pt port : ports){
                port.endDocument();
                addWarning(port.getWarnings());
                addError(port.getErrors());
            }
        }
        if(hasXTProperty()){
            for(Pp property : properties){
                property.endDocument();
                addWarning(property.getWarnings());
                addError(property.getErrors());
            }
        }
        if(hasXTLink()){
            for(L link : links){
                link.endDocument();
                addWarning(link.getWarnings());
                addError(link.getErrors());
            }
        }
        if(hasXTSwitch()){

             for(S xtswitch : switches){
                xtswitch.endDocument();
                addWarning(xtswitch.getWarnings());
                addError(xtswitch.getErrors());
            }
        }
    }

    private void variableReference(){
        if(getParent() == null){
            addWarning("Could not find a variable parent");
            return;
        }

        Iterable<V> exVariables = null;

        if(getParent() instanceof XTPBody)
            exVariables = ((XTPBody) getParent()).getVariables();
        else if(getParent() instanceof XTPContext)
            exVariables = ((XTPContext) getParent()).getNodes();

        if(exVariables!=null){
            String name1;
            String name2;
           for(V inVariable : variables){
               name1 = inVariable.getName();
                for(V exVariable : exVariables){
                    name2 = exVariable.getName();
                    if(name1.equals(name2)){
                        exVariable.setIncrement(inVariable.getSelect());
                        boolean remove = removeVariable(inVariable);
                        boolean add = addVariable(exVariable);
                        return;
                    }
                    else{
                        addWarning("Could not find variable"+inVariable.getName());
                    }
                }
            }
        }
    }

    public void searchForEach(Iterable<D> descriptors, Iterable<Cn> connectors, Iterable<R> rules){
        if(this.hasXTSwitch()){
            for(S xtswitch: switches){
               ((XTPSwitch) xtswitch).searchSwitch(descriptors, connectors, rules);
            }
        }


        if(this.hasXTLink()){

            for(L link: links ){
                ((XTPLink)link).searchLink(descriptors, connectors);
            }
        }
    }
    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected A createArea(){
        return  (A) new  XTPArea(getReader(), this);
    }

    protected Pp createProperty(){
        return  (Pp) new  XTPProperty(getReader(), this);
    }

    protected Pt createPort(){
        return  (Pt) new  XTPPort(getReader(), this);
    }

    protected L createLink(){
        return  (L) new  XTPLink(getReader(), this);
    }

    protected S createSwitch(){
        return  (S) new  XTPSwitch(getReader(), this);
    }

    protected V createVariable(){
        return  (V) new  XTPVariable(getReader(), this);
    }
}
