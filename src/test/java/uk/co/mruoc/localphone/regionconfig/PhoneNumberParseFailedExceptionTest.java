package uk.co.mruoc.localphone.regionconfig;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberParseFailedExceptionTest {

    @Test
    void shouldReturnCause() {
        Throwable cause = new Exception("cause");

        Throwable exception = new PhoneNumberParseFailedException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
    }

}
