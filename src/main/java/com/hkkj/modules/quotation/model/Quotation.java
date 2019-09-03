package com.hkkj.modules.quotation.model;

import com.hkkj.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * 报价单
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_quotation")
public class Quotation extends BaseEntity {

    private String code;

    private String customer;

    private Long customerId;

    private String salesman;

    private Long salesmanId;

    private String quotedTime;

    private String operator;

    private String auditor;

    private Long auditorId;

    private String content;

    private Integer auditorStatus;
}