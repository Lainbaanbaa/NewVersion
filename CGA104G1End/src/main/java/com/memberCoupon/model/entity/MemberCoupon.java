package com.memberCoupon.model.entity;

import com.coupon.model.entity.Coupon;
import com.mem.model.MemVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member_coupon", schema = "ba_rei")
@IdClass(MemberCouponPK.class)
public class MemberCoupon implements Serializable {

    @EmbeddedId
    private MemberCouponPK memberCouponPK;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COUPON_ID", insertable = false, updatable = false)
    private Integer couponId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MEM_ID", insertable = false, updatable = false)
    private Integer memId;
    @Basic
    @Column(name = "MCPN_GETTIME")
    private Timestamp mcpnGettime;
    @Basic
    @Column(name = "MCPN_USE")
    private Byte mcpnUse;

    @ManyToOne
    @JoinColumn(name = "COUPON_ID", insertable = false, updatable = false)
    private Coupon coupon;

}
