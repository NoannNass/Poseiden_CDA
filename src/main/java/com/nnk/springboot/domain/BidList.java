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
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="id")
    private Long id;

    @Column(name="bidlistid")
    private Integer BidListId;

    @Column(name="account")
    private String account;

    @Column(name="type")
    private String type;

    @Column(name="bidquantity")
    private Double bidQuantity;

    @Column(name="askquantity")
    private Double askQuantity;

    @Column(name="bid")
    Double bid;

    @Column(name="ask")
    private Double ask;

    @Column(name="benchmark")
    private String benchmark;

    @Column(name="tradedate")
    private Timestamp tradeDate;

    @Column(name="bidlistdate")
    private Timestamp bidListDate;

    @Column(name="commentary")
    private String commentary;

    @Column(name="security")
    private String security;

    @Column(name="status")
    private String status;


   @Column(name="trader")
    private String trader;

   @Column(name="book")
    private String book;

   @Column(name="creationname")
    private String creationName;

   @Column(name="creationdate")
    private Timestamp creationDate;

   @Column(name="revisionname")
    private String revisionName;

   @Column(name="revisiondate")
    private Timestamp revisionDate;

   @Column(name="dealname")
    private String dealName;

   @Column(name="dealtype")
    private String dealType;

   @Column(name="sourcelistid")
    private String sourceListId;

   @Column(name="side")
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
