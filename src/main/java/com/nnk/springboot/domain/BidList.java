package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields

    @Id
    private Long id;


    Integer BidListId;
    String account;
    String type;
    Double bidQuantity;
    Double askQuantity;
    Double bid;
    Double ask;
    String benchmark;
    Timestamp bidListDate;
    String commentary;
    String security;
    String status;
    String trader;
    String book;
    String creationName;
    Timestamp creationDate;
    String revisionName;
    Timestamp revisionDate;
    String dealName;
    String dealType;
    String sourceListId;
    String side;



    public BidList() {

    }

    public BidList(String accountTest, String typeTest, double v) {
    }

    public Integer getBidListId() {
        return 0;
    }

    public double getBidQuantity() {
        return 0;
    }

    public void setBidQuantity(double v) {
    }


}
