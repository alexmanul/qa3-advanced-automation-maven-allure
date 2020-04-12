package Utils.Advanced;

import Utils.TestDataReader;

public class AdvancedString {

    private final String string;

    public AdvancedString(String string) {
        this.string = string;
    }

    public String toString() {
        return TestDataReader.getDataFromFile(this.string);
    }

}
