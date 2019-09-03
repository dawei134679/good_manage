package com.hkkj.modules.quotation.model;

import com.hkkj.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.math.BigDecimal;
/**
 * 报价单商品
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "q_commodity")
public class Qcommodity extends BaseEntity {

    private Long qid;

    private String code;

    private String brand;

    private String model;

    private String name;

    private String img;

    private String spareNo;

    private String units;

    private Integer num;

    private BigDecimal price;

    private String discount;

    private String taxrate;

    private BigDecimal ntaxPrice;

    private BigDecimal amount;

    private String productQuality;

    private String drawingNumber;

    private String size;

    private String quotedTime;

}