package io.sphere.sdk.zones.queries;

import io.sphere.sdk.http.JsonEndpoint;
import io.sphere.sdk.zones.Zone;

final class ZonesEndpoint {
    static final JsonEndpoint<Zone> ENDPOINT = JsonEndpoint.of(Zone.typeReference(), "/zones");
}
