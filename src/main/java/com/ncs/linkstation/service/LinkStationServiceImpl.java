package com.ncs.linkstation.service;

import com.ncs.linkstation.model.LinkStation;
import com.ncs.linkstation.model.Point;
import com.ncs.linkstation.repository.LinkStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static com.ncs.linkstation.constants.LinkStationConstants.INPUT_ERROR;

/**
 * @author Mona Hanif Shaikh
 * @version 1.0
 * @implSpec this class implements the methods for performing operations on linkStation
 * @Since 17 Oct 2021
 */
@Service
@Slf4j
public class LinkStationServiceImpl implements LinkStationService {

    @Autowired
    private LinkStationRepository linkStationRepository;

    /**
     * @param point
     * @return String
     * @apiNote API to find the best linkStation for the device at a given point
     */
    @Override
    public String getBestLinkStation(Point point) {
        if (Objects.isNull(point) || Objects.isNull(point.getXCoordinate()) || Objects.isNull(point.getYCoordinate())) {
            log.error(INPUT_ERROR);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INPUT_ERROR);
        }
        log.info("Device location is : {}", point.toString());

        Integer deviceXCoordinate = point.getXCoordinate();
        Integer deviceYCoordinate = point.getYCoordinate();

        LinkStation bestLinkStation = null;
        double power;
        double bestLinkStationsPower = 0;
        double deviceDistanceFromLinkStation;
        List<LinkStation> linkStations = linkStationRepository.findAll();

        for (LinkStation linkStation : linkStations) {
            power = 0;
            deviceDistanceFromLinkStation = getDeviceDistanceFromLinkStation(linkStation, deviceXCoordinate, deviceYCoordinate);
            Integer linkStationReach = linkStation.getReach();
            if (deviceDistanceFromLinkStation <= linkStationReach) {
                power = Math.pow(linkStationReach - deviceDistanceFromLinkStation, 2);
                log.info("Device at point : {} has power : {} from linkStation : {}", point.toString(), power, linkStation.toString());
            }
            if (power > 0 && power >= bestLinkStationsPower) {
                bestLinkStationsPower = power;
                bestLinkStation = linkStation;
            }
        }

        if (Objects.isNull(bestLinkStation)) {
            return "No link station within reach for point " + deviceXCoordinate + "," + deviceYCoordinate;
        }
        return "Best link station for point " + deviceXCoordinate + "," + deviceYCoordinate + " is " + bestLinkStation.getXCoordinate() + "," + bestLinkStation.getYCoordinate() + " with power " + bestLinkStationsPower;
    }

    private double getDeviceDistanceFromLinkStation(LinkStation linkStation, Integer deviceXCoordinate, Integer deviceYCoordinate) {
        return Math.sqrt((Math.pow(linkStation.getXCoordinate() - deviceXCoordinate, 2)) +
                (Math.pow(linkStation.getYCoordinate() - deviceYCoordinate, 2)));
    }
}
