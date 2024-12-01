package com.nasa.nasaasteroidapi.service;

import com.nasa.nasaasteroidapi.dto.FeedDto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface FeedService {
    FeedDto.FeedDataResponse getNeoFeedData(FeedDto.RequestParameters requestParameters) throws IOException;
    FeedDto.FeedLookupResponse getNeoLookupData(String neoId) throws UnsupportedEncodingException;
}
