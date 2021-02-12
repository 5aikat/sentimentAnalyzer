package com.saikat.poc.sentimentAnalyzer.restclient;


import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AsyncClient {

    private static final Logger logger = LogManager.getLogger(AsyncClient.class);
    private static RestTemplate restTemplate = new RestTemplate();

    @Async
    public static void doPost(String url, String body, HttpHeaders headers, HttpMethod method, Class clazz, Object... arguments){
        if(headers == null){
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        HttpEntity<String> entity = new HttpEntity<>(body,headers);
        restTemplate.exchange(url,method,entity,clazz,arguments);
    }
}
