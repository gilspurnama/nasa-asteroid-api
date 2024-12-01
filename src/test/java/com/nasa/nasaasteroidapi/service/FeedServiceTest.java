package com.nasa.nasaasteroidapi.service;

import com.nasa.nasaasteroidapi.dto.FeedDto;
import com.nasa.nasaasteroidapi.service.impl.FeedServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

import static com.nasa.nasaasteroidapi.util.EnumHelper.NASA_API_KEY;
import static com.nasa.nasaasteroidapi.util.EnumHelper.NASA_NEO_REST_FEED_V1;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class FeedServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @InjectMocks
    private FeedServiceImpl feedServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void neoFeedDataTest() throws IOException {
        FeedDto.RequestParameters requestParameters = new FeedDto.RequestParameters();
        requestParameters.setEndDate("2015-09-08");
        requestParameters.setStartDate("2015-09-07");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("start_date", requestParameters.getStartDate());
        parameters.put("end_date", requestParameters.getEndDate());
        parameters.put("api_key", NASA_API_KEY.value());

        String completeURI = NASA_NEO_REST_FEED_V1.value() + "?end_date=2015-09-08&api_key=" + NASA_API_KEY.value() + "&start_date=2015-09-07";
        log.info(completeURI);
        Mockito.when(restTemplate.getForObject(completeURI,FeedDto.GetFeedData.class)).thenReturn(setFeedData());

        ReflectionTestUtils.setField(feedServiceImpl, "restTemplate", restTemplate);
        FeedDto.FeedDataResponse result = feedServiceImpl.getNeoFeedData(requestParameters);

        assertEquals(result.getElementCount(), 1);
    }

    FeedDto.GetFeedData setFeedData(){
        FeedDto.Links links =  FeedDto.Links.builder()
                .next("nextLink")
                .self("selfLink")
                .next("nextLink")
                .build();

        FeedDto.EstimatedDiameterMinMax estimatedDiameterMinMax = FeedDto.EstimatedDiameterMinMax.builder()
                .EstimatedDiameterMax(1L)
                .EstimatedDiameterMin(1L)
                .build();

        FeedDto.EstimatedDiameter estimatedDiameter = FeedDto.EstimatedDiameter.builder()
                .kilometers(estimatedDiameterMinMax)
                .meters(estimatedDiameterMinMax)
                .miles(estimatedDiameterMinMax)
                .build();

        FeedDto.RelativeVelocity relativeVelocity = FeedDto.RelativeVelocity.builder()
                .kilometersPerHour("18.1279360862")
                .kilometersPerSecond("65260.5699103704")
                .milesPerSecond("40550.3802312521")
                .build();

        FeedDto.MissDistance missDistance = FeedDto.MissDistance.builder()
                .astronomical("0.3027469457")
                .lunar("117.7685618773")
                .kilometers("45290298.225725659")
                .miles("28142086.3515817342")
                .build();

        FeedDto.CloseApproachData closeApproachData = FeedDto.CloseApproachData.builder()
                .closeApproachDate("2015-09-08")
                .closeApproachDateFull("2015-Sep-08 20:28")
                .epochDateCloseApproach(1441744080000L)
                .relativeVelocity(relativeVelocity)
                .missDistance(missDistance)
                .orbitingBody("Earth")
                .build();
        LinkedList<FeedDto.CloseApproachData> listOfCloseApproachData = new LinkedList<>();
        listOfCloseApproachData.add(closeApproachData);

        FeedDto.NearEarthObjectData neoData = FeedDto.NearEarthObjectData.builder()
//                .links(neoLink)
                .id("neoID")
                .neoReferenceId("neoReferenceId")
                .name("neoName")
                .nasaJplUrl("nasaJplUrl")
                .absoluteMagnitudeH(20L)
                .estimatedDiameter(estimatedDiameter)
                .isPotentiallyHazardousAsteroid(true)
                .closeApproachData(listOfCloseApproachData)
                .isSentryObject(false)
                .build();

        LinkedList<FeedDto.NearEarthObjectData> neoDataArr = new LinkedList<>();
        neoDataArr.add(neoData);
        LinkedHashMap<String, LinkedList<FeedDto.NearEarthObjectData>> nearEarthData = new LinkedHashMap<>();
        nearEarthData.put("2015-09-08", neoDataArr);

        return FeedDto.GetFeedData.builder()
                .links(links)
                .elementCount(1)
                .nearEarthObjects(nearEarthData)
                .build();
    }
}
