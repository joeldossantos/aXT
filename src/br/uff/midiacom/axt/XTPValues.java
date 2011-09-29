

package AXT;

/**
 *
 * @author Flavia
 */
public  abstract class XTPValues {


    public enum XTemplateNamespace {

        XSI("http://www.w3.org/2001/XMLSchema-instance"),
        XT("http://www.midiacom.uff.br/gtvd/XTemPlate30/XTemPlateENCL"),
        SCHEMALOCATION("http://www.midiacom.uff.br/gtvd/XTemPlate30/XTemPlateENCL http://www.midiacom.uff.br/gtvd/XTemPlate30/profiles/XTPENCL.xsd");


        private String name;
        private XTemplateNamespace(String name) {
            this.name = name;
            }
        }
        public enum XTemplateXType {
        TEXT("TEXT"),
        TEXT_HTML("text/html"),
        TEXT_PLAIN("text/plain"),
        TEXT_CSS("text/css"),
        TEXT_XML("text/xml"),
        IMAGE("image"),
        IMAGE_BMP("image/bmp"),
        IMAGE_PNG("image/png"),
        IMAGE_GIF("image/gif"),
        IMAGE_JPEG("image/jpeg"),
        AUDIO("audio"),
        AUDIO_BASIC("audio/basic"),
        AUDIO_MP3("audio/mp3"),
        AUDIO_MP2("audio/mp2"),
        AUDIO_MPEG("audio/mpeg"),
        AUDIO_MPEG4("audio/mpeg4"),
        VIDEO("video"),
        VIDEO_MPEG("video/mpeg"),
        APPLICATION_X_GINGA_NCL("application/x-ginga-NCL"),
        APPLICATION_X_GINGA_NCLUA("application/x-ginga-NCLua"),
        APPLICATION_X_GINGA_NCLET("application/x-ginga-NCLet"),
        APPLICATION_X_GINGA_SETTINGS("application/x-ginga-settings"),
        APPLICATION_X_GINGA_TIME("application/x-ginga-time"),
        CONTEXT("context"),
        SWITCH("switch");
        
        private String name;
     
        private XTemplateXType(String name){
            this.name = name;
        }
        @Override
        public String toString() {
            return name;
        }

    }

    

}
