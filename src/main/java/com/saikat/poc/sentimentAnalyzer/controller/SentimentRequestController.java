package com.saikat.poc.sentimentAnalyzer.controller;


import com.saikat.poc.sentimentAnalyzer.model.SentimentRequestModel;
import com.saikat.poc.sentimentAnalyzer.service.SentimentAnalysisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/v1/sentiment")
public class SentimentRequestController {

    private static final Logger logger = LogManager.getLogger(SentimentRequestController.class);

    @Autowired
    private SentimentAnalysisService analysisService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postSentimentRequest(@RequestBody SentimentRequestModel requestModel){
        logger.info("Sentiment analyze request recieved for : {}",requestModel.getText());
        analysisService.Analyze(requestModel.getText());
    }
}
