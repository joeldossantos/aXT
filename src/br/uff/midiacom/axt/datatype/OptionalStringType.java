package br.uff.midiacom.axt.datatype;


public class OptionalStringType {

    private String value;


    public OptionalStringType(String value) throws IllegalArgumentException {
        if(value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty String");

        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
