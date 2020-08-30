package uk.co.mruoc.localphone.regionconfig;

import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class DefaultRegionConfig extends AbstractRegionConfig {

    private final CountryCode country;
    private final Collection<PhoneNumberType> mobileTypes;
    private final PhoneNumberFormat format;

    public DefaultRegionConfig(CountryCode country) {
        this(country, defaultMobileTypes(), defaultFormat());
    }

    @Override
    public CountryCode getParent() {
        return country;
    }

    @Override
    public Collection<CountryCode> getChildren() {
        return Collections.singleton(country);
    }

    @Override
    protected Collection<PhoneNumberType> getMobileTypes() {
        return mobileTypes;
    }

    @Override
    protected PhoneNumberFormat getFormat() {
        return format;
    }

    private static Collection<PhoneNumberType> defaultMobileTypes() {
        return Collections.singleton(PhoneNumberType.MOBILE);
    }

    private static PhoneNumberFormat defaultFormat() {
        return PhoneNumberFormat.E164;
    }

}
