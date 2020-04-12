package Utils;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomAssertions {

    public static void assertThatEquals(String actual, String expected) {
        assertThat(actual).isEqualTo(TestDataReader.getDataFromFile(expected));
    }

    public static void assertThatEquals(String actual, String expected, String exceptionMessage) {
        assertThat(actual.trim()).as(exceptionMessage).isEqualTo(TestDataReader.getDataFromFile(Utils.getSpecificDate(expected)).trim());
    }

    public static void assertThatContains(String actual, String expected, String exceptionMessage) {
        assertThat(actual).as(exceptionMessage).contains(TestDataReader.getDataFromFile(Utils.getSpecificDate(expected)).trim());
    }

    public static void assertThatTrue(boolean result, String exceptionMessage) {
        assertThat(result).as(exceptionMessage).isTrue();
    }
}
