package com.nasa.nasaasteroidapi.service.impl;

import com.nasa.nasaasteroidapi.dto.FeedDto;
import com.nasa.nasaasteroidapi.service.FeedService;
import com.nasa.nasaasteroidapi.util.ParameterStringBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.nasa.nasaasteroidapi.util.EnumHelper.NASA_API_KEY;
import static com.nasa.nasaasteroidapi.util.EnumHelper.NASA_NEO_REST_FEED_V1;
import static com.nasa.nasaasteroidapi.util.ParameterStringBuilder.getParamsString;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {

    @Override
    public FeedDto.GetFeedData getNeoFeedData(FeedDto.RequestParameters requestParameters) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("start_date", requestParameters.getStartDate());
        parameters.put("end_date", requestParameters.getEndDate());
        parameters.put("api_key", NASA_API_KEY.value());

        String completeURI = NASA_NEO_REST_FEED_V1.value() + "?" + getParamsString(parameters);
        return restTemplate.getForObject(completeURI, FeedDto.GetFeedData.class);
    }

    @Override
    public FeedDto.NearEarthObjectData getNeoLookupData(String neoId) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", NASA_API_KEY.value());

        String completeURI = NASA_NEO_REST_FEED_V1.value() + "/" + neoId + "?" + getParamsString(parameters);
        return restTemplate.getForObject(completeURI, FeedDto.NearEarthObjectData.class);
    }
}
