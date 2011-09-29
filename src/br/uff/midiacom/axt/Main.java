

package AXT;

import br.uff.midiacom.axt.component.XTemplateComponent;
import br.uff.midiacom.axt.connector.XTemplateConnector;
import br.uff.midiacom.axt.Media.XTemplateMedia;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.reuse.NCLImport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main<N extends NCLNode>{


               

    public static void main(String[] args)  {
      XTemplateDoc doc = new XTemplateDoc();
      
            try {


                doc.loadXML("teste.xml");
                System.out.println("depois do load");
                
                


                Iterable<String> erros = doc.getBody().getErrors();
                for(String erro: erros){

                        System.out.println(erro);

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        
      
    }

}
