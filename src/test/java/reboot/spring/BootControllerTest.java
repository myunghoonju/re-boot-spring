package reboot.spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class BootControllerTest {

    @Test
    void api() {
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> restForEntity = rest.getForEntity("http://localhost:8080/hello?name={name}",
                                                                 String.class,
                                                 "Spring");

        //validation:: status code, content-type, body
        assertThat(restForEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(restForEntity.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(restForEntity.getBody()).isEqualTo("SimpleBootService reboot " + "Spring");

    }
}