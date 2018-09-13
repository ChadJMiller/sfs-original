package com.noofinc.dsm.webapi.client.core.timezone;

import java.util.TimeZone;

public class DefaultTimeZoneProvider implements TimeZoneProvider {

    @Override
    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
}
