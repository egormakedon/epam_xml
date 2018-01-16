package constant;

public enum PaperType {
    JOURNAL("journal"), NEWSPAPER("newspaper"), BOOKLET("booklet"),
    MOTHLY("mothly"), COLOR("color"), VOLUME("volume"), PAPER_ID("paper-id"),
    SUBSCRIPTION_INDEX("subscription-index"), TITTLE("tittle"), GLOSS("gloss");


    private String value;

    PaperType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
