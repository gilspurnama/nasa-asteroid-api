package com.nasa.nasaasteroidapi.service;

import com.nasa.nasaasteroidapi.dto.FeedDto;
import com.nasa.nasaasteroidapi.dto.ResponseDto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public interface FeedService {
    FeedDto.FeedDataResponse getNeoFeedData(FeedDto.RequestParameters requestParameters) throws IOException;
    FeedDto.FeedLookupResponse getNeoLookupData(String neoId) throws UnsupportedEncodingException;
}
