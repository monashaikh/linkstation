package com.ncs.linkstation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Point {

    @JsonProperty("xCoordinate")
    private Integer xCoordinate;

    @JsonProperty("yCoordinate")
    private Integer yCoordinate;
}
