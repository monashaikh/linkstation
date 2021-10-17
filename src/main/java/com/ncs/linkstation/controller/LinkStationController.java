package com.ncs.linkstation.controller;

import com.ncs.linkstation.exception.LinkStationExceptions;
import com.ncs.linkstation.model.Point;
import com.ncs.linkstation.service.LinkStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkStationController extends LinkStationExceptions {

    @Autowired
    LinkStationService linkStationService;

    @ResponseBody
    @PostMapping(value = "/get-best-link-station", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getBestLinkStation(@RequestBody final Point point) {
        return linkStationService.getBestLinkStation(point);
    }
}

