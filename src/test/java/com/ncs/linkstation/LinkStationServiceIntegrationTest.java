package com.ncs.linkstation;

import com.ncs.linkstation.controller.LinkStationController;
import com.ncs.linkstation.model.Point;
import com.ncs.linkstation.service.LinkStationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@SpringBootTest(classes = LinkStationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @Test
    public void testGetBestLinkStationForOrigin_validLinkStation() {
        Point point = getPoint(0, 0);
        HttpEntity<Point> httpEntity = new HttpEntity<>(point, getHttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.POST, httpEntity, String.class);
        String responseBody = response.getBody();
        Assert.assertEquals("Best link station for point 0,0 is 0,0 with power 100.0", responseBody);
    }

    @Test
    public void testGetBestLinkStation_NoLinkStation() {
        Point point = getPoint(100, 100);
        HttpEntity<Point> httpEntity = new HttpEntity<>(point, getHttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.POST, httpEntity, String.class);
        String responseBody = response.getBody();
        Assert.assertEquals("No link station within reach for point 100,100", responseBody);
    }

    @Test
    public void testGetBestLinkStation_BadRequest() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.POST, httpEntity, String.class);
        String responseBody = response.getBody();
        Assert.assertTrue(responseBody.contains("Required request body is missing"));
    }

    @Test
    public void testGetBestLinkStation_invalidXCoordinate() {
        Point point = getPoint(null, 100);
        HttpEntity<Point> httpEntity = new HttpEntity<>(point, getHttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.POST, httpEntity, String.class);
        String responseBody = response.getBody();
        Assert.assertEquals("400 BAD_REQUEST \"Point cannot be empty, please enter device's xCoordinate and yCoordinate in the expected format\"", responseBody);

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
