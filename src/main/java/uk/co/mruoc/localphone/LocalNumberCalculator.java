package uk.co.mruoc.localphone;

import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.localphone.regionconfig.RegionConfig;
import uk.co.mruoc.localphone.regionconfig.RegionConfigProvider;
import uk.co.mruoc.localphone.regionconfig.DefaultRegionConfigProvider;

@RequiredArgsConstructor
@Slf4j
public class LocalNumberCalculator {

    private final RegionConfigProvider configProvider;

    public LocalNumberCalculator() {
        this(new DefaultRegionConfigProvider());
    }

    public LocalPhoneNumber toLocalPhoneNumber(String number, CountryCode country) {
        RegionConfig config = configProvider.provide(country);
        LocalPhoneNumber localNumber = config.toLocal(number);
        log.debug("loaded {} from raw number {} and country {}", localNumber, number, country);
        return localNumber;
    }

}
