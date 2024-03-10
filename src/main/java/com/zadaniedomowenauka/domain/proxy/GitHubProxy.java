package com.zadaniedomowenauka.domain.proxy;
import com.zadaniedomowenauka.domain.error.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j2
public class GitHubProxy {

    @Value("${itunes-proxy}")
    String url;

    @Value("${itunes-port}")
    int port;

    private final RestTemplate restTemplate;

    public GitHubProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String makeGetRequest(String username){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .newInstance()
                    .scheme("https")
                    .host(url)
                    .port(port)
                    .path("/users/" + username + "/repos");
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exception){
            throw new UserNotFoundException("username: " + username + " not found");
        } catch (RestClientException exception){
            String message = exception.getMessage();
            log.error(message);
        }
        return null;
    }

    public String makeGetBranches(String owner, String repo){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .newInstance()
                    .scheme("https")
                    .host(url)
                    .port(port)
                    .path("/repos/" + owner + "/" + repo + "/branches");
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exception){
            String message = exception.getMessage() + exception.getStatusCode().value();
            log.error(message);
        } catch (RestClientException exception){
            String message = exception.getMessage();
            log.error(message);
        }
        return null;
    }
}
