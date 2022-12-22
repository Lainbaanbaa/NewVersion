package com.commodityDetails.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode
@Embeddable
public class CommodityDetailsPK implements Serializable {
    @Column(name = "ORDER_ID", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(name = "ITEM_ID", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

}
