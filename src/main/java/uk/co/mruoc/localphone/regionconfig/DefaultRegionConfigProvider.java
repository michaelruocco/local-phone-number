package uk.co.mruoc.localphone.regionconfig;

import com.neovisionaries.i18n.CountryCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class DefaultRegionConfigProvider implements RegionConfigProvider {

    private final Map<CountryCode, RegionConfig> mappings = new EnumMap<>(CountryCode.class);

    public DefaultRegionConfigProvider() {
        this(buildDefaultMappings());
    }

    public DefaultRegionConfigProvider(Collection<RegionConfig> inputMappings) {
        inputMappings.forEach(this::add);
    }

    @Override
    public RegionConfig provide(CountryCode country) {
        return Optional.ofNullable(mappings.get(country))
                .orElse(new DefaultRegionConfig(country));
    }

    private void add(RegionConfig mapping) {
        mappings.put(mapping.getParent(), mapping);
    }

    private static Collection<RegionConfig> buildDefaultMappings() {
        return Arrays.asList(
                new GbRegionConfig(),
                new UsRegionConfig(),
                new RuRegionConfig()
        );
    }

}
