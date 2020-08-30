package uk.co.mruoc.localphone;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Builder
@Data
public class DefaultLocalPhoneNumber implements LocalPhoneNumber {

    private final CountryCode defaultRegion;
    private final CountryCode region;
    private final String raw;
    private final String formatted;
    private final boolean local;
    private final boolean mobile;

    @Override
    public CountryCode getDefaultRegion() {
        return defaultRegion;
    }

    @Override
    public Optional<CountryCode> getLocalRegion() {
        return Optional.ofNullable(region);
    }

    @Override
    public String getRawValue() {
        return raw;
    }

    @Override
    public String getFormattedValue() {
        return formatted;
    }

    @Override
    public boolean isLocal() {
        return getLocalRegion().isPresent();
    }

    @Override
    public boolean isMobile() {
        return mobile;
    }

}
