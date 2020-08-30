package uk.co.mruoc.localphone.regionconfig;

import com.neovisionaries.i18n.CountryCode;

public interface RegionConfigProvider {

    RegionConfig provide(CountryCode country);
}
