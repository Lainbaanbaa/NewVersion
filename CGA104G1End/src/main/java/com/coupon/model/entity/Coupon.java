package com.coupon.model.entity;

import com.memberCoupon.model.entity.MemberCoupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COUPON", schema = "ba_rei")
public class Coupon implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COUPON_ID")
    private Integer couponId;
    @Basic
    @Column(name = "COUPON_NAR")
    private String couponNar;
    @Basic
    @Column(name = "COUPON_VAL")
    private Double couponVal;
    @Basic
    @Column(name = "RECEIVE_START")
    private Date receiveStart;
    @Basic
    @Column(name = "RECEIVE_OVER")
    private Date receiveOver;
    @Basic
    @Column(name = "USE_START")
    private Date useStart;
    @Basic
    @Column(name = "USE_OVER")
    private Date useOver;
    @Basic
    @Column(name = "MINIMUM")
    private Double minimum;

    @OneToMany(mappedBy = "coupon")
    private List<MemberCoupon> memberCoupon;

}
