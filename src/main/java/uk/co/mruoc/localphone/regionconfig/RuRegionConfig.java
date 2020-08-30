package uk.co.mruoc.localphone.regionconfig;

import com.neovisionaries.i18n.CountryCode;

import java.util.Arrays;
import java.util.Collection;

public class RuRegionConfig extends AbstractRegionConfig {

    @Override
    public CountryCode getParent() {
        return CountryCode.RU;
    }

    @Override
    public Collection<CountryCode> getChildren() {
        return Arrays.asList(
                CountryCode.RU,
                CountryCode.KZ
        );
    }

}
