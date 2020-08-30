package uk.co.mruoc.localphone.regionconfig;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.neovisionaries.i18n.CountryCode;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.localphone.DefaultLocalPhoneNumber;
import uk.co.mruoc.localphone.LocalPhoneNumber;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Slf4j
public abstract class AbstractRegionConfig implements RegionConfig {

    private static final PhoneNumberUtil UTIL = PhoneNumberUtil.getInstance();

    @Override
    public LocalPhoneNumber toLocal(String rawNumber) {
        PhoneNumber parsedNumber = parse(rawNumber);
        Optional<CountryCode> localRegion = toLocalRegion(parsedNumber);
        return DefaultLocalPhoneNumber.builder()
                .defaultRegion(getParent())
                .region(localRegion.orElse(null))
                .raw(rawNumber)
                .formatted(format(parsedNumber))
                .local(localRegion.isPresent())
                .mobile(isMobile(parsedNumber))
                .build();
    }

    protected Collection<PhoneNumberType> getMobileTypes() {
        return Collections.singleton(PhoneNumberUtil.PhoneNumberType.MOBILE);
    }

    protected PhoneNumberFormat getFormat() {
        return PhoneNumberFormat.E164;
    }

    private PhoneNumber parse(String number) {
        try {
            return UTIL.parse(number, getParent().getAlpha2());
        } catch (NumberParseException e) {
            throw new PhoneNumberParseFailedException(e);
        }
    }

    private Optional<CountryCode> toLocalRegion(PhoneNumber number) {
        return getChildren().stream()
                .map(region -> toLocalRegion(region, number))
                .filter(Optional::isPresent)
                .findFirst()
                .orElse(Optional.empty());
    }

    private static Optional<CountryCode> toLocalRegion(CountryCode country, PhoneNumber number) {
        boolean valid = UTIL.isValidNumberForRegion(number, country.getAlpha2());
        log.debug("number {} valid for country {}: {}", number, country, valid);
        if (valid) {
            return Optional.of(country);
        }
        return Optional.empty();
    }

    private boolean isMobile(PhoneNumber number) {
        return getMobileTypes().contains(UTIL.getNumberType(number));
    }

    private String format(PhoneNumber number) {
        return UTIL.format(number, getFormat());
    }

}
