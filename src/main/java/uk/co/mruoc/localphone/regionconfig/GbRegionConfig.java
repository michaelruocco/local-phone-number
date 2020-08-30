package uk.co.mruoc.localphone.regionconfig;

import com.neovisionaries.i18n.CountryCode;

import java.util.Arrays;
import java.util.Collection;

public class GbRegionConfig extends AbstractRegionConfig {

    @Override
    public CountryCode getParent() {
        return CountryCode.GB;
    }

    @Override
    public Collection<CountryCode> getChildren() {
        return Arrays.asList(
                CountryCode.GB,
                CountryCode.IM,
                CountryCode.JE,
                CountryCode.GG
        );
    }

}
