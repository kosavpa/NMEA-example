package owlhome.testexample.utils;

public enum IdLine {
    GPGGA("$GPGGA"), BDVTG("$BDVTG"), GNVTG("$GNVTG"), GNZDA("$GNZDA"), GPZDA("$GPZDA"), GPVTG("$GPVG"), UNKNOWN("$UNK");

    private final String id;

    IdLine(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
