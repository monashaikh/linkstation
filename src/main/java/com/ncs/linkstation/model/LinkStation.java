package com.ncs.linkstation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "LINKSTATION")
@ToString
public class LinkStation {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "X_COORDINATE")
    private Integer xCoordinate;

    @Column(name = "Y_COORDINATE")
    private Integer yCoordinate;

    @Column(name = "REACH")
    private Integer reach;

}
