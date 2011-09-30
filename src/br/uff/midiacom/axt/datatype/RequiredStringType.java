package br.uff.midiacom.axt.datatype;


public class RequiredStringType {

    private String value;


    public RequiredStringType(String value) throws NullPointerException, IllegalArgumentException {
        if(value == null)
            throw new NullPointerException("Null String");
        if("".equals(value.trim()))
            throw new IllegalArgumentException("Empty String");

        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
