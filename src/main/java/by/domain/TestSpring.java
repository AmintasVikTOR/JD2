package by.domain;

import org.springframework.stereotype.Component;

import java.net.http.HttpClient;

@Component
public class TestSpring {
    /*1. Use Spring annotations
       @Autowired
       @Qualifier("role")*/

    /* 2. Use Java annotations
        JSR-330
        @Inject
        @Named*/
    private HttpClient httpClient;

    /*3. Use constructor*/
    public TestSpring(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /*4. Use setter*/
    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String toString() {
        return "TestSpring{" +
                ", httpClient=" + httpClient +
                '}';
    }
}

