package com.nasa.nasaasteroidapi.controller;

import com.nasa.nasaasteroidapi.dto.FeedDto;
import com.nasa.nasaasteroidapi.dto.ResponseDto;
import com.nasa.nasaasteroidapi.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/neo")
public class NeoController {

    private final FeedService feedService;

    @GetMapping("/feeds")
    public ResponseEntity<ResponseDto<FeedDto.FeedDataResponse>> getFeed(FeedDto.RequestParameters requestParameters) throws IOException {
        FeedDto.FeedDataResponse response = feedService.getNeoFeedData(requestParameters);
        return ResponseEntity.ok(new ResponseDto<>(response));
    }

    @GetMapping("/feeds/{neoId}")
    public ResponseEntity<ResponseDto<FeedDto.FeedLookupResponse>> getFeedLookup(@PathVariable String neoId) throws IOException {
        FeedDto.FeedLookupResponse response = feedService.getNeoLookupData(neoId);
        return ResponseEntity.ok(new ResponseDto<>(response));
    }
}
