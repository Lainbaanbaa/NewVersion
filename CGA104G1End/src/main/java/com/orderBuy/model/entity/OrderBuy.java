package com.orderBuy.model.entity;

import com.commodityDetails.model.entity.CommodityDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_BUY", schema = "ba_rei")
public class OrderBuy implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID")
    private Integer orderId;
    @Basic
    @Column(name = "MEM_ID")
    private Integer memId;
    @Basic
    @Column(name = "ORIGINAL_PRICE")
    private Double originalPrice;
    @Basic
    @Column(name = "DISCOUNT_PRICE")
    private Double discountPrice;
    @Basic
    @Column(name = "FINAL_PRICE")
    private Double finalPrice;
    @Basic
    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;
    @Basic
    @Column(name = "ORDER_PAYING")
    private Byte orderPaying;
    @Basic
    @Column(name = "ORDER_SEND")
    private Byte orderSend;
    @Basic
    @Column(name = "ORDER_STATUS")
    private Byte orderStatus;
    @Basic
    @Column(name = "ORDER_OTHER")
    private String orderOther;
    @Basic
    @Column(name = "TRACKING_NUM")
    private String trackingNum;
    @Basic
    @Column(name = "RECEIVER_NAME")
    private String receiverName;
    @Basic
    @Column(name = "RECEIVER_ADDRESS")
    private String receiverAddress;
    @Basic
    @Column(name = "RECEIVER_PHONE")
    private String receiverPhone;
    @Basic
    @Column(name = "PICKUP_TIME")
    private Timestamp pickupTime;

    @OneToMany(mappedBy = "orderBuy")
    private List<CommodityDetails> orderBuys;

}
