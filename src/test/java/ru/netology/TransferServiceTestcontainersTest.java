package ru.netology;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferServiceTestcontainersTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final GenericContainer<?> appRestApi = new GenericContainer<>("TransferMoneyRestAPI")
            .withExposedPorts(5500);

    @BeforeAll
    public static void setUp() {
        appRestApi.start();
    }

    @Test
    void testRequest() {
        HttpHeaders headers = this.restTemplate.getForEntity("/transfer", String.class).getHeaders();
        assertThat(headers.getLocation()).hasHost("localhost");
    }

    @Test
    void testResponse() {
        ResponseEntity<String> response = restTemplate.
                getForEntity("http://localhost:" + appRestApi.getMappedPort(5500) + "/transfer", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}

