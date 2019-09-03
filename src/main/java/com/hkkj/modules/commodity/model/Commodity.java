package com.hkkj.modules.commodity.model;

import com.hkkj.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_commodity")
public class Commodity extends BaseEntity {

    private String code;

    private String model;

    private String brand;

    private String name;

    private String img;

    private String spareNo;

    private String specification;

    private BigDecimal taxPrice;

    private BigDecimal price;

    private String supplier;

    private String drawingNumber;

    private String size;

    private String quotedTime;

    private Integer num;

    private String content1;

    private String content2;

    private String content3;

    private Integer type;

    private Integer status;

}