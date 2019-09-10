package com.hkkj.modules.sys.model;

import com.hkkj.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_customer")
public class Customer  extends BaseEntity{

    private String name;

    private Boolean sex;

    private String mobilePhone;

    private String email;

    private String content;

    private Integer status;
}