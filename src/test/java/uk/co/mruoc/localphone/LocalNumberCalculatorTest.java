package uk.co.mruoc.localphone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.neovisionaries.i18n.CountryCode.CH;
import static com.neovisionaries.i18n.CountryCode.GB;
import static com.neovisionaries.i18n.CountryCode.GG;
import static com.neovisionaries.i18n.CountryCode.RU;
import static com.neovisionaries.i18n.CountryCode.US;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Local number calculator tests")
public class LocalNumberCalculatorTest {

    private final LocalNumberCalculator calculator = new LocalNumberCalculator();

    @Nested
    @DisplayName("GB local number tests")
    class GbLocalNumberTests {

        @Test
        void shouldReturnGuernseyNumberIsLocal() {
            String guernseyNumber = "07911 123456";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(guernseyNumber, GB);

            assertThat(number.getDefaultRegion()).isEqualTo(GB);
            assertThat(number.getLocalRegion()).contains(GG);
            assertThat(number.isLocal()).isTrue();
        }

        @Test
        void shouldReturnSwissNumberIsNotLocal() {
            String swissNumber = "044 668 18 00";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(swissNumber, GB);

            assertThat(number.getDefaultRegion()).isEqualTo(GB);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
        }

    }

    @Nested
    @DisplayName("CH local number tests")
    class ChLocalNumberTests {

        @Test
        void shouldReturnSwissNumberIsLocal() {
            String swissNumber = "044 668 18 00";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(swissNumber, CH);

            assertThat(number.getDefaultRegion()).isEqualTo(CH);
            assertThat(number.getLocalRegion()).contains(CH);
            assertThat(number.isLocal()).isTrue();
        }

        @Test
        void shouldReturnGuernseyNumberIsNotLocal() {
            String guernseyNumber = "07911 123456";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(guernseyNumber, CH);

            assertThat(number.getDefaultRegion()).isEqualTo(CH);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
        }

    }

    @Nested
    @DisplayName("RU local number tests")
    class RuLocalNumberTests {

        @Test
        void shouldReturnRussianNumberIsLocal() {
            String russianNumber = "495 123 4567";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(russianNumber, RU);

            assertThat(number.getDefaultRegion()).isEqualTo(RU);
            assertThat(number.getLocalRegion()).contains(RU);
            assertThat(number.isLocal()).isTrue();
        }

        @Test
        void shouldReturnGuernseyNumberIsNotLocal() {
            String guernseyNumber = "07911 123456";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(guernseyNumber, RU);

            assertThat(number.getDefaultRegion()).isEqualTo(RU);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
        }

    }

    @Nested
    @DisplayName("US local number tests")
    class UsLocalNumberTests {

        @Test
        void shouldReturnUsNumberIsLocal() {
            String usNumber = "1-541-754-3010";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(usNumber, US);

            assertThat(number.getDefaultRegion()).isEqualTo(US);
            assertThat(number.getLocalRegion()).contains(US);
            assertThat(number.isLocal()).isTrue();
        }

        @Test
        void shouldReturnGuernseyNumberIsNotLocal() {
            String guernseyNumber = "07911 123456";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(guernseyNumber, US);

            assertThat(number.getDefaultRegion()).isEqualTo(US);
            assertThat(number.getLocalRegion()).isEmpty();
            assertThat(number.isLocal()).isFalse();
        }

    }

    @Nested
    @DisplayName("Formatted value tests")
    class FormattedValueTests {

        @Test
        void shouldReturnRawAndFormattedValue() {
            String rawNumber = "07911 123456";

            LocalPhoneNumber number = calculator.toLocalPhoneNumber(rawNumber, GB);

            assertThat(number.getRawValue()).isEqualTo(rawNumber);
            assertThat(number.getFormattedValue()).isEqualTo("+447911123456");
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
