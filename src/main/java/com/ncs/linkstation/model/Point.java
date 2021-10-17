package com.ncs.linkstation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class Point {

    @NonNull
    @JsonProperty("xCoordinate")
    private Integer xCoordinate;

    @NonNull
    @JsonProperty("yCoordinate")
    private Integer yCoordinate;
}
