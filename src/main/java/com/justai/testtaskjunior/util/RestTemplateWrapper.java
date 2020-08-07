package com.justai.testtaskjunior.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
public class RestTemplateWrapper implements HttpClientWrapper {

    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateWrapper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String get(String url) {
        String decoded = decodeUrl(url);
        HttpEntity<String> response = restTemplate.exchange(decoded, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    @Override
    public String post(String url) {
        String decoded = decodeUrl(url);
        HttpEntity<String> response = restTemplate.exchange(decoded, HttpMethod.POST, null, String.class);
        return response.getBody();
    }

    private String decodeUrl(String url) {
        String decoded = url;
        try {
            decoded = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decoded;
    }
}
