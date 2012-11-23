package de.commercetools.sphere.client;

import de.commercetools.internal.util.Url;

/** Centralizes construction of backend API urls. */
public class Endpoints {

    /** Sphere OAuth 2.0 token endpoint. */
    public static String tokenEndpoint(String authEndpoint) {
        return Url.combine(authEndpoint, "/oauth/token");
    }

    /** Sphere backend API endpoints for given project. */
    public static ProjectEndpoints forProject(String coreEndpoint, String projectKey) {
        if (projectKey == null)
            throw new IllegalArgumentException("projectKey cannot be null");
        return new ProjectEndpoints(Url.combine(coreEndpoint, projectKey));
    }
}