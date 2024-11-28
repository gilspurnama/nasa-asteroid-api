package com.nasa.nasaasteroidapi.util;

public enum EnumHelper {
    NASA_API_KEY                ("BvKEhY6vb2n2Ps3mFQvhgejHrOyBQ227GxyinWGh"),
    NASA_NEO_REST_FEED_V1       ("https://api.nasa.gov/neo/rest/v1/feed"),
    NASA_NEO_REST_LOOKUP_V1     ("https://api.nasa.gov/neo/rest/v1/neo");

    private final String value;

    EnumHelper(final String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
