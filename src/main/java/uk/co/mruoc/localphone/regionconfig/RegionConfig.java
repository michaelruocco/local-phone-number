package uk.co.mruoc.localphone.regionconfig;

import com.neovisionaries.i18n.CountryCode;
import uk.co.mruoc.localphone.LocalPhoneNumber;

import java.util.Collection;

public interface RegionConfig {

    CountryCode getParent();

    Collection<CountryCode> getChildren();

    LocalPhoneNumber toLocal(String rawNumber);

}
