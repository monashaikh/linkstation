package com.ncs.linkstation;

import com.ncs.linkstation.controller.LinkStationController;
import com.ncs.linkstation.model.Point;
import com.ncs.linkstation.service.LinkStationService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = LinkstationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LinkStationServiceIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    LinkStationService linkStationService;

    @Autowired
    LinkStationController linkStationController;

    @Autowired
    TestRestTemplate restTemplate;

    ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testGetBestLinkStationForOrigin_validLinkStation() {
        Point point = getPoint(0, 0);
        HttpEntity<Point> httpEntity = new HttpEntity<>(point, getHttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.POST, httpEntity, String.class);
        String responseBody = response.getBody();
        Assert.assertEquals("Best link station for point 0,0 is 0,0 with power 100.0", responseBody);
    }

    @Test
    public void testGetBestLinkStationForOrigin_NoLinkStation() {
        Point point = getPoint(100, 100);
        HttpEntity<Point> httpEntity = new HttpEntity<>(point, getHttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.POST, httpEntity, String.class);
        String responseBody = response.getBody();
        Assert.assertEquals("No link station within reach for point 100,100", responseBody);
    }

    @Test
    public void testGetBestLinkStationForOrigin_BadRequest() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.POST, httpEntity, String.class);
        String responseBody = response.getBody();
        Assert.assertTrue(responseBody.contains("Required request body is missing"));
    }

    @Test
    public void testGetBestLinkStationForOrigin_invalidXCoordinate() {
        try {
            getPoint(null, 5);
        } catch (NullPointerException e) {
            Assert.assertEquals("xCoordinate is marked non-null but is null", e.getMessage());
        }

    }

    private String getUrl() {
        return "http://localhost:" + port + "/api/get-best-link-station";
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    private Point getPoint(Integer x, Integer y) {
        return Point.builder().xCoordinate(x).yCoordinate(y).build();
    }

}
