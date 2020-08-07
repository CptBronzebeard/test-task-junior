package com.justai.testtaskjunior.model.queries;

import com.justai.testtaskjunior.util.HttpClientWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Scope("prototype")
public abstract class ApiQuery {
    protected UriComponentsBuilder builder;
    private HttpClientWrapper httpClientWrapper;
    @Value("${vk.api.access-key}")
    private String accessToken;
    @Value("${vk.api.version}")
    private String version;

    protected ApiQuery(String url, HttpClientWrapper httpClientWrapper) {
        builder = UriComponentsBuilder.fromHttpUrl(url);
        this.httpClientWrapper = httpClientWrapper;
    }

    public String execute() {
        builder.queryParam("access_token", accessToken);
        builder.queryParam("v", version);
        return httpClientWrapper.post(builder.toUriString());
    }
}
