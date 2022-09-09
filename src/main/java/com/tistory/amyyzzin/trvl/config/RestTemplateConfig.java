package com.tistory.amyyzzin.trvl.config;

import java.net.URI;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Configuration
public class RestTemplateConfig {

    @Value("${open.api.key}")
    private String serviceKeyValue;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(3000))
            .setReadTimeout(Duration.ofMillis(5000))
            .additionalInterceptors(
                this.serviceKeyInjectionInterceptor()
            )
            .build();
    }

    private ClientHttpRequestInterceptor serviceKeyInjectionInterceptor() {
        return (request, body, execution) -> {
            String query = request.getURI()
                .getQuery();
            if (!StringUtils.isEmpty(query) && query.contains("ServiceKey")) {
                return execution.execute(request, body);
            }
            URI uri = UriComponentsBuilder.fromUri(request.getURI())
                .queryParam("ServiceKey", serviceKeyValue)
                .build(true)
                .toUri();
            return execution.execute(new RequestWrapper(request, uri), body);
        };
    }

    /**
     * HttpRequest 클래스의 uri 값을 변경하기 위한 wrapper class
     */
    private static class RequestWrapper implements HttpRequest {
        private final HttpRequest original;

        private final URI newUriWithParam;

        private RequestWrapper(HttpRequest original, URI newUriWithParam) {
            this.original = original;
            this.newUriWithParam = newUriWithParam;
        }

        @Override
        public String getMethodValue() {
            return original.getMethodValue();
        }

        @Override
        public URI getURI() {
            return newUriWithParam;
        }

        @Override
        public HttpHeaders getHeaders() {
            return original.getHeaders();
        }
    }
}
