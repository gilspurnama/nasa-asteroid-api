package com.nasa.nasaasteroidapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FeedDto {

    @Getter
    @Builder
    public static class GetFeedData {
        private Links links;
        @JsonProperty("element_count")
        private Integer elementCount;
        @JsonProperty("near_earth_objects")
        private Map<String, NearEarthObjectData[]> nearEarthObjects;
    }

    @Getter
    @Builder
    public static class Links {
        private String next;
        private String previous;
        private String self;
    }

    @Getter
    @Builder
    public static class NeoLink {
        private String self;
    }

    @Getter
    @Builder
    public static class NearEarthObjectData {
        private NeoLink links;
        private String id;
        @JsonProperty("neo_reference_id")
        private String neoReferenceId;
        private String name;
        @JsonProperty("nasa_jpl_url")
        private String nasaJplUrl;
        @JsonProperty("absolute_magnitude_h")
        private Long absoluteMagnitudeH;
        private EstimatedDiameter estimatedDiameter;
        @JsonProperty("is_potentially_hazardous_asteroid")
        private Boolean isPotentiallyHazardousAsteroid;
        private CloseApproachData closeApproachData;
        @JsonProperty("is_sentry_object")
        private Boolean isSentryObject;
    }

    @Getter
    @Builder
    public static class CloseApproachData {
        @JsonProperty("close_approach_date")
        private String closeApproachDate;
        @JsonProperty("close_approach_date_full")
        private String closeApproachDateFull;
        @JsonProperty("epoch_date_close_approach")
        private Long epochDateCloseApproach;
        private RelativeVelocity relativeVelocity;
        private MissDistance missDistance;
        @JsonProperty("orbiting_body")
        private String orbitingBody;
    }

    @Getter
    @Builder
    public static class RelativeVelocity {
        @JsonProperty("kilometers_per_second")
        private String kilometersPerSecond;
        @JsonProperty("kilometers_per_hour")
        private String kilometersPerHour;
        @JsonProperty("miles_per_second")
        private String milesPerSecond;
    }

    @Getter
    @Builder
    public static class MissDistance {
        private String astronomical;
        private String lunar;
        private String kilometers;
        private String miles;
    }

    @Getter
    @Builder
    public static class EstimatedDiameter {
        private EstimatedDiameterMinMax kilometers;
        private EstimatedDiameterMinMax meters;
        private EstimatedDiameterMinMax miles;
    }

    @Getter
    @Builder
    public static class EstimatedDiameterMinMax {
        @JsonProperty("estimated_diameter_min")
        private Long EstimatedDiameterMin;
        @JsonProperty("estimated_diameter_max")
        private Long EstimatedDiameterMax;
    }

    @Getter
    @Setter
    public static class RequestParameters {
        private String startDate;
        private String endDate;
    }
}