package uk.co.mruoc.localphone;

import com.neovisionaries.i18n.CountryCode;
import uk.co.mruoc.localphone.DefaultLocalPhoneNumber.DefaultLocalPhoneNumberBuilder;

public interface LocalPhoneNumberMother {

    static LocalPhoneNumber localMobile() {
        return builder().build();
    }

    static LocalPhoneNumber nonLocalMobile() {
        return builder()
                .region(CountryCode.CH)
                .local(false)
                .build();
    }

    static LocalPhoneNumber localNonMobile() {
        return builder().mobile(false).build();
    }

    static LocalPhoneNumber nonLocalNonMobile() {
        return builder()
                .region(CountryCode.CH)
                .local(false)
                .mobile(false)
                .build();
    }

    static DefaultLocalPhoneNumberBuilder builder() {
        return DefaultLocalPhoneNumber.builder()
                .defaultRegion(CountryCode.GB)
                .region(CountryCode.GB)
                .raw("07911 123456")
                .formatted("+447911123456")
                .local(true)
                .mobile(true);
    }

}
