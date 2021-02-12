package com.saikat.poc.sentimentAnalyzer.service;

import com.google.cloud.language.v1.*;
import com.google.gson.Gson;
import com.saikat.poc.sentimentAnalyzer.model.PlotRequestModel;
import com.saikat.poc.sentimentAnalyzer.restclient.AsyncClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleCloudSentimentAnalysisService implements SentimentAnalysisService {

    private static final Logger logger = LogManager.getLogger(GoogleCloudSentimentAnalysisService.class);

    private String GRAPH_PLOT_URL="http://localhost:5000/stats";

    @Async
    @Override
    public void Analyze(String text) {
        try(LanguageServiceClient languageServiceClient = LanguageServiceClient.create()){
            Document document = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = languageServiceClient.analyzeSentiment(document);
            Sentiment sentiment = response.getDocumentSentiment();
            if(sentiment!= null){
                logger.info("Sentiment Analysis doc : {} ",document.getContent());
                logger.info("Sentiment magnitude : {} ",sentiment.getMagnitude());
                logger.info("Sentiment score : {} ",sentiment.getScore());

                // publish the data for plotting
                PlotRequestModel requestModel = new PlotRequestModel(sentiment.getScore(),sentiment.getMagnitude());
                String json = new Gson().toJson(requestModel);
                AsyncClient.doPost(GRAPH_PLOT_URL,json,null, HttpMethod.POST,Void.class);
            }

        }catch (IOException e){
            logger.error("Exception occured : {} ",e.getMessage());
        }
    }
}
