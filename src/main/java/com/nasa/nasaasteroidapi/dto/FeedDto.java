package com.nasa.nasaasteroidapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;


public class FeedDto {

    @Getter
    @Builder
    @Data
    public static class GetFeedData {
        private Links links;
        @JsonProperty("element_count")
        private Integer elementCount;
        @JsonProperty("near_earth_objects")
        private LinkedHashMap<String, LinkedList<NearEarthObjectData>> nearEarthObjects;
    }

    @Getter
    @Builder
    @Data
    public static class FeedDataResponse {
        private Links links;
        @JsonProperty("element_count")
        private Integer elementCount;
        @JsonProperty("near_earth_objects")
        private LinkedList<NearEarthObjectData> nearEarthObjects;
    }

    @Getter
    @Builder
    @Data
    public static class Links {
        private String next;
        private String previous;
        private String self;
    }

    @Getter
    @Builder
    @Data
    public static class NearEarthObjectData {
        private Object links;
        private String id;
        @JsonProperty("neo_reference_id")
        private String neoReferenceId;
        private String name;
        @JsonProperty("nasa_jpl_url")
        private String nasaJplUrl;
        @JsonProperty("absolute_magnitude_h")
        private Long absoluteMagnitudeH;
        @JsonProperty("estimated_diameter")
        private EstimatedDiameter estimatedDiameter;
        @JsonProperty("is_potentially_hazardous_asteroid")
        private Boolean isPotentiallyHazardousAsteroid;
        @JsonProperty("close_approach_data")
        private LinkedList<CloseApproachData> closeApproachData;
        @JsonProperty("is_sentry_object")
        private Boolean isSentryObject;
    }

    @Getter
    @Builder
    @Data
    public static class FeedLookupResponse {
        private Object links;
        private String id;
        @JsonProperty("neo_reference_id")
        private String neoReferenceId;
        private String name;
        private String designation;
        @JsonProperty("nasa_jpl_url")
        private String nasaJplUrl;
        @JsonProperty("absolute_magnitude_h")
        private Long absoluteMagnitudeH;
        @JsonProperty("estimated_diameter")
        private Object estimatedDiameter;
        @JsonProperty("is_potentially_hazardous_asteroid")
        private Boolean isPotentiallyHazardousAsteroid;
        @JsonProperty("close_approach_data")
        private LinkedList<Object> closeApproachData;
        @JsonProperty("is_sentry_object")
        private Boolean isSentryObject;
        @JsonProperty("orbital_data")
        private OrbitalData orbitalData;
    }

    @Getter
    @Builder
    @Data
    public static class Self {
        @JsonProperty("self")
        private String self;
    }

    @Getter
    @Builder
    @Data
    public static class CloseApproachData {
        @JsonProperty("close_approach_date")
        private String closeApproachDate;
        @JsonProperty("close_approach_date_full")
        private String closeApproachDateFull;
        @JsonProperty("epoch_date_close_approach")
        private Long epochDateCloseApproach;
        @JsonProperty("relative_velocity")
        private RelativeVelocity relativeVelocity;
        @JsonProperty("miss_distance")
        private MissDistance missDistance;
        @JsonProperty("orbiting_body")
        private String orbitingBody;
    }

    @Getter
    @Builder
    @Data
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
    @Data
    public static class MissDistance {
        private String astronomical;
        private String lunar;
        private String kilometers;
        private String miles;
    }

    @Getter
    @Builder
    @Data
    public static class EstimatedDiameter {
        private EstimatedDiameterMinMax kilometers;
        private EstimatedDiameterMinMax meters;
        private EstimatedDiameterMinMax miles;
        private EstimatedDiameterMinMax feet;
    }

    @Getter
    @Builder
    @Data
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
        private String nearest10 = "true";
    }

    @Getter
    @Builder
    @Data
    public static class OrbitalData {
        @JsonProperty("orbit_id")
        private String orbitId;
        @JsonProperty("orbit_determination_date")
        private String orbitDeterminationDate;
        @JsonProperty("first_observation_date")
        private String firstObservationDate;
        @JsonProperty("last_observation_date")
        private String lastObservationDate;
        @JsonProperty("data_arc_in_days")
        private Integer dataArcInDays;
        @JsonProperty("observations_used")
        private Integer observationsUsed;
        @JsonProperty("orbit_uncertainty")
        private String orbitUncertainty;
        @JsonProperty("minimum_orbit_intersection")
        private String minimumOrbitIntersection;
        @JsonProperty("jupiter_tisserand_invariant")
        private String jupiterTisserandInvariant;
        @JsonProperty("epoch_osculation")
        private String epochOsculation;
        @JsonProperty("eccentricity")
        private String eccentricity;
        @JsonProperty("semi_major_axis")
        private String semiMajorAxis;
        @JsonProperty("inclination")
        private String inclination;
        @JsonProperty("ascending_node_longitude")
        private String ascendingNodeLongitude;
        @JsonProperty("orbital_period")
        private String orbitalPeriod;
        @JsonProperty("perihelion_distance")
        private String perihelionDistance;
        @JsonProperty("perihelion_argument")
        private String perihelionArgument;
        @JsonProperty("aphelion_distance")
        private String aphelionDistance;
        @JsonProperty("perihelion_time")
        private String perihelionTime;
        @JsonProperty("mean_anomaly")
        private String meanAnomaly;
        @JsonProperty("mean_motion")
        private String meanMotion;
        @JsonProperty("equinox")
        private String equinox;
        @JsonProperty("orbit_class")
        private OrbitalClass orbitClass;
    }

    @Getter
    @Builder
    @Data
    public static class OrbitalClass {
        @JsonProperty("orbit_class_type")
        private String orbitClassType;
        @JsonProperty("orbit_class_description")
        private String orbitClassDescription;
        @JsonProperty("orbit_class_range")
        private String orbitClassRange;
    }
}