package com.justai.testtaskjunior.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateWrapper implements HttpClientWrapper {

    private RestTemplate restTemplate;
    @Autowired
    public RestTemplateWrapper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public String get(String url) {
        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    @Override
    public String post(String url) {
        return null;
    }
}
