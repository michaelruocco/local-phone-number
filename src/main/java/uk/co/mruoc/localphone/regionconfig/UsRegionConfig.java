package uk.co.mruoc.localphone.regionconfig;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.neovisionaries.i18n.CountryCode;

import java.util.Arrays;
import java.util.Collection;

public class UsRegionConfig extends AbstractRegionConfig {

    @Override
    public CountryCode getParent() {
        return CountryCode.US;
    }

    @Override
    public Collection<CountryCode> getChildren() {
        return Arrays.asList(
                CountryCode.US,
                CountryCode.CA,
                CountryCode.AG,
                CountryCode.AI,
                CountryCode.AS,
                CountryCode.BB,
                CountryCode.BM,
                CountryCode.BS,
                CountryCode.DM,
                CountryCode.DO,
                CountryCode.GD,
                CountryCode.GU,
                CountryCode.JM,
                CountryCode.KN,
                CountryCode.KY,
                CountryCode.LC,
                CountryCode.MP,
                CountryCode.MS,
                CountryCode.PR,
                CountryCode.SX,
                CountryCode.TC,
                CountryCode.TT,
                CountryCode.VC,
                CountryCode.VG,
                CountryCode.VI,
                CountryCode.UM
        );
    }

    @Override
    protected Collection<PhoneNumberUtil.PhoneNumberType> getMobileTypes() {
        return Arrays.asList(
                PhoneNumberUtil.PhoneNumberType.MOBILE,
                PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE
        );
    }

}
