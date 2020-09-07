package br.com.rsousa.pojo;

public enum DriverStatus {
    FINISHED("Finished", ""),
    LAST_POSITION("LP", "Last Position (LP)"),
    DISQUALIFIED("DQ","Desclassificação (DQ)"),
    DID_NOT_FINISH("DNF", "Abandono");

    private final String text;
    private final String fullText;

    DriverStatus(String text, String fullText) {
        this.text = text;
        this.fullText = fullText;
    }

    public String text() {
        return text;
    }

    public String fullText() {
        return fullText;
    }

}
