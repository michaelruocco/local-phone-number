package uk.co.mruoc.localphone;

import com.neovisionaries.i18n.CountryCode;

import java.util.Optional;

public interface LocalPhoneNumber {

    CountryCode getDefaultRegion();

    Optional<CountryCode> getLocalRegion();

    String getRawValue();

    String getFormattedValue();

    boolean isLocal();

    boolean isMobile();

}
