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
        PhoneNumber parsedNumber = tryParse(rawNumber);
        Optional<CountryCode> localRegion = toLocalRegion(parsedNumber);
        if (localRegion.isPresent()) {
            return toLocalNumber(parsedNumber, localRegion.get());
        }
        return toInternationalNumber(parsedNumber);
    }

    protected Collection<PhoneNumberType> getMobileTypes() {
        return Collections.singleton(PhoneNumberUtil.PhoneNumberType.MOBILE);
    }

    protected PhoneNumberFormat getFormat() {
        return PhoneNumberFormat.E164;
    }

    private PhoneNumber tryParse(String number) {
        String region = getParentRegionAlpha2();
        PhoneNumber originalParsed = parse(number, region);
        if (isValid(originalParsed)) {
            return originalParsed;
        }
        return prefixPlusAndParse(number, region).orElse(originalParsed);
    }

    private PhoneNumber parse(String number, String region) {
        try {
            return UTIL.parseAndKeepRawInput(number, region);
        } catch (NumberParseException e) {
            throw new PhoneNumberParseFailedException(e);
        }
    }

    private Optional<PhoneNumber> prefixPlusAndParse(String number, String region) {
        try {
            String prefixed = prefixPlus(number);
            PhoneNumber phoneNumber = UTIL.parseAndKeepRawInput(prefixed, region);
            phoneNumber.setRawInput(number);
            return Optional.of(phoneNumber);
        } catch (NumberParseException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    private String getParentRegionAlpha2() {
        return getParent().getAlpha2();
    }

    private String prefixPlus(String number) {
        return String.format("+%s", number);
    }

    private Optional<CountryCode> toLocalRegion(PhoneNumber number) {
        return getChildren().stream()
                .map(region -> toLocalRegion(region, number))
                .filter(Optional::isPresent)
                .findFirst()
                .orElse(Optional.empty());
    }

    private LocalPhoneNumber toLocalNumber(PhoneNumber parsedNumber, CountryCode localRegion) {
        return DefaultLocalPhoneNumber.builder()
                .defaultRegion(getParent())
                .raw(parsedNumber.getRawInput())
                .formatted(format(parsedNumber))
                .mobile(isMobile(parsedNumber))
                .region(localRegion)
                .local(true)
                .build();
    }

    private LocalPhoneNumber toInternationalNumber(PhoneNumber parsedNumber) {
        log.debug("using raw input {} as formatted number because we have an international number and cannot guess the country code", parsedNumber.getRawInput());
        return DefaultLocalPhoneNumber.builder()
                .defaultRegion(getParent())
                .raw(parsedNumber.getRawInput())
                .formatted(format(parsedNumber))
                .mobile(isMobile(parsedNumber))
                .local(false)
                .build();
    }

    private boolean isMobile(PhoneNumber number) {
        PhoneNumberType type = UTIL.getNumberType(number);
        boolean mobile = getMobileTypes().contains(type);
        log.debug("number {} has type {} is mobile: {}", number, type, mobile);
        return mobile;
    }

    private String format(PhoneNumber number) {
        return UTIL.format(number, getFormat());
    }

    private static Optional<CountryCode> toLocalRegion(CountryCode country, PhoneNumber number) {
        boolean valid = UTIL.isValidNumberForRegion(number, country.getAlpha2());
        log.debug("number {} valid for country {}: {}", number, country, valid);
        if (valid) {
            return Optional.of(country);
        }
        return Optional.empty();
    }

    private static boolean isValid(PhoneNumber phoneNumber) {
        return doesNotHaveDuplicatedGermanCountryCodePrefix(phoneNumber) &&
                UTIL.isValidNumber(phoneNumber);
    }

    //The need for this check is explained here:
    //https://github.com/google/libphonenumber/blob/master/FAQ.md#why-wasnt-the-country-code-removed-when-parsing
    private static boolean doesNotHaveDuplicatedGermanCountryCodePrefix(PhoneNumber phoneNumber) {
        int countryCode = phoneNumber.getCountryCode();
        if (!isGermanNumber(countryCode)) {
            return true;
        }
        String nationalNumber = Long.toString(phoneNumber.getNationalNumber());
        log.debug("national number {} country code {}", nationalNumber, countryCode);
        return !nationalNumber.startsWith(Integer.toString(countryCode));
    }

    private static boolean isGermanNumber(int countryCode) {
        return countryCode == 49;
    }

}
