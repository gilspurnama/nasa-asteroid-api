package com.nasa.nasaasteroidapi.service.impl;

import com.nasa.nasaasteroidapi.dto.FeedDto;
import com.nasa.nasaasteroidapi.service.FeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.nasa.nasaasteroidapi.util.EnumHelper.*;
import static com.nasa.nasaasteroidapi.util.ParameterStringBuilder.getParamsString;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {

    private final RestTemplate restTemplate;

    public FeedServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public FeedDto.FeedDataResponse getNeoFeedData(FeedDto.RequestParameters requestParameters) throws UnsupportedEncodingException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("start_date", requestParameters.getStartDate());
        parameters.put("end_date", requestParameters.getEndDate());
        parameters.put("api_key", NASA_API_KEY.value());

        String completeURI = NASA_NEO_REST_FEED_V1.value() + "?" + getParamsString(parameters);
        FeedDto.GetFeedData response = restTemplate.getForObject(completeURI, FeedDto.GetFeedData.class);
        return feedDataResponse(response, requestParameters.getNearest10().equals("true"));
    }

    @Override
    public FeedDto.FeedLookupResponse getNeoLookupData(String neoId) throws UnsupportedEncodingException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", NASA_API_KEY.value());

        String completeURI = NASA_NEO_REST_LOOKUP_V1.value() + "/" + neoId + "?" + getParamsString(parameters);
        log.info(completeURI);
        FeedDto.FeedLookupResponse response = restTemplate.getForObject(completeURI, FeedDto.FeedLookupResponse.class);
        return response;
    }

    FeedDto.FeedDataResponse feedDataResponse(FeedDto.GetFeedData response, boolean isTopTen) {
        LinkedList<FeedDto.NearEarthObjectData> orderData = new LinkedList<>();
        int counter = 0;
        for (Map.Entry<String, LinkedList<FeedDto.NearEarthObjectData>> entry: response.getNearEarthObjects().entrySet()) {
            orderData.addAll(entry.getValue());
        }
        if (isTopTen) {
            orderData = orderData.stream().sorted(Comparator.comparing(o -> o.getCloseApproachData().get(0).getMissDistance().getKilometers()))
                .limit(10)
                .collect(Collectors.toCollection(LinkedList::new));
            counter = orderData.size();
        } else {
            counter = response.getElementCount();
        }
        return FeedDto.FeedDataResponse.builder()
                .links(response.getLinks())
                .nearEarthObjects(orderData)
                .elementCount(counter)
                .build();
    }

    FeedDto.GetFeedData getNearest10(FeedDto.GetFeedData response) {
        LinkedHashMap<String, LinkedList<FeedDto.NearEarthObjectData>> orderData = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, LinkedList<FeedDto.NearEarthObjectData>> entry: response.getNearEarthObjects().entrySet()) {
            orderData.put(entry.getKey(), entry.getValue().stream()
                    .sorted(Comparator.comparing(o -> o.getCloseApproachData().get(0).getMissDistance().getKilometers()))
                            .limit(10)
                    .collect(Collectors.toCollection(LinkedList::new)));
            count = count + orderData.get(entry.getKey()).size();
        }
        response.setNearEarthObjects(orderData);
        response.setElementCount(count);
        return response;
    }
}
