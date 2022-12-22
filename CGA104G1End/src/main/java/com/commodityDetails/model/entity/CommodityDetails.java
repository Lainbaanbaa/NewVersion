package com.commodityDetails.model.entity;

import com.item.model.ItemVO;
import com.orderBuy.model.entity.OrderBuy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commodity_details", schema = "ba_rei")
@IdClass(CommodityDetailsPK.class)
public class CommodityDetails implements Serializable {

    @EmbeddedId
    private CommodityDetailsPK commodityDetailsPK;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID", insertable = false, updatable = false)
    private Integer orderId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ITEM_ID", insertable = false, updatable = false)
    private Integer itemId;
    @Basic
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Basic
    @Column(name = "CD_AMOUNT")
    private Integer cdAmount;
    @Basic
    @Column(name = "ITEM_PRICE")
    private Double itemPrice;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", insertable = false, updatable = false)
    private ItemVO item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    private OrderBuy orderBuy;

}
