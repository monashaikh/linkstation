package com.ncs.linkstation;

import com.ncs.linkstation.model.Point;
import com.ncs.linkstation.service.LinkStationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest(classes = LinkstationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LinkStationServiceUnitTest {

    @Autowired
    LinkStationService linkStationService;

    @Test
    public void testGetBestLinkStationForOrigin_validLinkStation() {
        Point point = getPoint(0, 0);
        String response = linkStationService.getBestLinkStation(point);
        Assert.assertEquals("Best link station for point 0,0 is 0,0 with power 100.0", response);
    }

    @Test
    public void testGetBestLinkStationForPoint_noLinkStation() {
        Point point = getPoint(100, 100);
        String response = linkStationService.getBestLinkStation(point);
        Assert.assertEquals("No link station within reach for point 100,100", response);
    }

    @Test(expected = ResponseStatusException.class)
    public void testGetBestLinkStationForPoint_BadRequest() {
        linkStationService.getBestLinkStation(null);
    }

    private Point getPoint(Integer x, Integer y) {
        return Point.builder().xCoordinate(x).yCoordinate(y).build();
    }

}
