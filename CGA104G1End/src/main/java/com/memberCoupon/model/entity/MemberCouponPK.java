package com.memberCoupon.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode
@Embeddable
public class MemberCouponPK implements Serializable {
    @Column(name = "COUPON_ID", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponId;
    @Column(name = "MEM_ID", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memId;

}
