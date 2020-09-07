package br.com.rsousa.pojo;

public enum DriverStatus {
    FINISHED("Finished"), DISQUALIFIED("DSQ"), DID_NOT_FINISH("DNF");

    private final String text;

    DriverStatus(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

}
