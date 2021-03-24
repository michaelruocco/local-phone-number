package uk.co.mruoc.localphone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.neovisionaries.i18n.CountryCode.CH;
import static com.neovisionaries.i18n.CountryCode.DE;
import static com.neovisionaries.i18n.CountryCode.GB;
import static com.neovisionaries.i18n.CountryCode.GG;
import static com.neovisionaries.i18n.CountryCode.IN;
import static com.neovisionaries.i18n.CountryCode.RU;
import static com.neovisionaries.i18n.CountryCode.US;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Local number calculator tests")
public class LocalNumberCalculatorTest {

    private static final String GUERNSEY_NUMBER = "01481 960 194";
    private static final String SWISS_NUMBER = "41 22 343 80 14";
    private static final String GERMAN_NUMBER = "491762260312";
    private static final String INDIAN_NUMBER = "917503907302";

    private final LocalNumberCalculator calculator = new LocalNumberCalculator();

    private static String prefixPlus(String number) {
        return String.format("+%s", number);
    }

    @Nested
    @DisplayName("GB local number tests")
    class GbLocalNumberTests {

        @Test
        void shouldReturnGuernseyNumberIfLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GUERNSEY_NUMBER, GB);

            assertThat(number.getDefaultRegion()).isEqualTo(GB);
            assertThat(number.getLocalRegion()).contains(GG);
            assertThat(number.isLocal()).isTrue();
            assertThat(number.getRawValue()).isEqualTo(GUERNSEY_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo("+441481960194");
        }

        @Test
        void shouldReturnSwissNumberIfNotLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(SWISS_NUMBER, GB);

            assertThat(number.getDefaultRegion()).isEqualTo(GB);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
            assertThat(number.getRawValue()).isEqualTo(SWISS_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo("+41223438014");
        }

        @Test
        void shouldReturnGermanNumberIfNotLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GERMAN_NUMBER, GB);

            assertThat(number.getDefaultRegion()).isEqualTo(GB);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
            assertThat(number.getRawValue()).isEqualTo(GERMAN_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo("+491762260312");
        }

        @Test
        void shouldReturnIndianNumberIfNotLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(INDIAN_NUMBER, GB);

            assertThat(number.getDefaultRegion()).isEqualTo(GB);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
            assertThat(number.getRawValue()).isEqualTo(INDIAN_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo("+917503907302");
        }

    }

    @Nested
    @DisplayName("Swiss local number tests")
    class SwissLocalNumberTests {

        @Test
        void shouldReturnSwissNumberIsLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(SWISS_NUMBER, CH);

            assertThat(number.getDefaultRegion()).isEqualTo(CH);
            assertThat(number.getLocalRegion()).contains(CH);
            assertThat(number.isLocal()).isTrue();
        }

        @Test
        void shouldReturnGuernseyNumberIsNotLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GUERNSEY_NUMBER, CH);

            assertThat(number.getDefaultRegion()).isEqualTo(CH);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
        }

    }

    @Nested
    @DisplayName("Russian local number tests")
    class RussianLocalNumberTests {

        @Test
        void shouldReturnRussianNumberIfLocal() {
            String russianNumber = "495 123 4567";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(russianNumber, RU);

            assertThat(number.getDefaultRegion()).isEqualTo(RU);
            assertThat(number.getLocalRegion()).contains(RU);
            assertThat(number.isLocal()).isTrue();
        }

        @Test
        void shouldReturnGuernseyNumberIfNotLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GUERNSEY_NUMBER, RU);

            assertThat(number.getDefaultRegion()).isEqualTo(RU);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
        }

    }

    @Nested
    @DisplayName("US local number tests")
    class UsLocalNumberTests {

        @Test
        void shouldReturnUsNumberIfLocal() {
            String usNumber = "1-541-754-3010";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(usNumber, US);

            assertThat(number.getDefaultRegion()).isEqualTo(US);
            assertThat(number.getLocalRegion()).contains(US);
            assertThat(number.isLocal()).isTrue();
        }

        @Test
        void shouldReturnGuernseyNumberIfNotLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GUERNSEY_NUMBER, US);

            assertThat(number.getDefaultRegion()).isEqualTo(US);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
        }

    }

    @Nested
    @DisplayName("German local number tests")
    class GermanLocalNumberTests {

        @Test
        void shouldReturnGermanNumberIfLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GERMAN_NUMBER, DE);

            assertThat(number.getDefaultRegion()).isEqualTo(DE);
            assertThat(number.getLocalRegion()).contains(DE);
            assertThat(number.isLocal()).isTrue();
        }

        @Test
        void shouldReturnGuernseyNumberIfNotLocal() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GUERNSEY_NUMBER, DE);

            assertThat(number.getDefaultRegion()).isEqualTo(DE);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
        }

    }

    @Nested
    @DisplayName("Formatted value tests")
    class FormattedValueTests {

        @Test
        void shouldReturnRawAndFormattedValueIfNumberIsLocalToGb() {
            String gbNumber = "441694429025";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(gbNumber, GB);

            assertThat(number.getRawValue()).isEqualTo(gbNumber);
            assertThat(number.getFormattedValue()).isEqualTo("+441694429025");
        }

        @Test
        void shouldReturnRawAndFormattedValueIfNumberIsLocalToGuernsey() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GUERNSEY_NUMBER, GB);

            assertThat(number.getRawValue()).isEqualTo(GUERNSEY_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo("+441481960194");
        }

        @Test
        void shouldReturnRawAndFormattedValueIfNumberIsLocalToGermany() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GERMAN_NUMBER, DE);

            assertThat(number.getRawValue()).isEqualTo(GERMAN_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo("+491762260312");
        }

        @Test
        void shouldReturnRawAndFormattedValueIfNumberIsLocalToIndia() {
            String indianNumber = "917503907302";
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(indianNumber, IN);

            assertThat(number.getRawValue()).isEqualTo(indianNumber);
            assertThat(number.getFormattedValue()).isEqualTo("+917503907302");
        }

        @Test
        void shouldReturnRawValueAsFormattedValueForIndianInternationalNumberFromUkSinceWeCantGuessTheCountryCode() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(INDIAN_NUMBER, GB);

            assertThat(number.getRawValue()).isEqualTo(INDIAN_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo(prefixPlus(INDIAN_NUMBER));
        }

        @Test
        void shouldReturnRawAndFormattedValueIfNumberIsLocalToSwitzerland() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(SWISS_NUMBER, CH);

            assertThat(number.getRawValue()).isEqualTo(SWISS_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo("+41223438014");
        }

        @Test
        void shouldReturnRawValueAsFormattedValueForGermanInternationalNumberFromUkSinceWeCantGuessTheCountryCode() {
            LocalPhoneNumber number = calculator.toLocalPhoneNumber(GERMAN_NUMBER, GB);

            assertThat(number.getRawValue()).isEqualTo(GERMAN_NUMBER);
            assertThat(number.getFormattedValue()).isEqualTo(prefixPlus(GERMAN_NUMBER));
        }

    }

    @Nested
    @DisplayName("Mobile number tests")
    class MobileNumberTests {

        @Test
        void shouldReturnIsMobileTrueForMobileNumber() {
            String mobileNumber = "07911 123456";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(mobileNumber, GB);

            assertThat(number.isMobile()).isTrue();
        }

        @Test
        void shouldReturnIsMobileFalseForFixedLineNumber() {
            String fixedLineNumber = "01604 123456";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(fixedLineNumber, GB);

            assertThat(number.isMobile()).isFalse();
        }

    }

}
