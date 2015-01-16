package io.sphere.sdk.zones.queries;

import io.sphere.sdk.queries.DefaultModelQueryModelImpl;
import io.sphere.sdk.queries.QueryModel;
import io.sphere.sdk.zones.Zone;

import java.util.Optional;

public class ZoneQueryModel extends DefaultModelQueryModelImpl<Zone> {
    static ZoneQueryModel get() {
        return new ZoneQueryModel(Optional.<QueryModel<Zone>>empty(), Optional.<String>empty());
    }

    private ZoneQueryModel(final Optional<? extends QueryModel<Zone>> parent, final Optional<String> pathSegment) {
        super(parent, pathSegment);
    }
}
