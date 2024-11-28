package com.nasa.nasaasteroidapi.controller;

import com.nasa.nasaasteroidapi.dto.FeedDto;
import com.nasa.nasaasteroidapi.dto.ResponseDto;
import com.nasa.nasaasteroidapi.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/neo")
public class NeoController {

    private final FeedService feedService;

    @GetMapping("/feeds")
    public ResponseEntity<ResponseDto<FeedDto.GetFeedData>> getFeed(FeedDto.RequestParameters requestParameters) throws IOException {
        FeedDto.GetFeedData response = feedService.getNeoFeedData(requestParameters);
        return ResponseEntity.ok(new ResponseDto<>(response));
    }
}
