package com.hkkj.modules.cms.model;

import com.hkkj.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 内容
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Content extends BaseEntity {

    /**
     * 内容分类ID
     */
    private Long contentCatId;

    /**
     * 图片
     */
    private String img;

    /**
     * 内容标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 标题描述
     */
    private String titleDesc;

    /**
     * 内容
     */
    private String content;

    /**
     * 链接
     */
    private String url;

    /**
     * 时间
     */
    private Date time;

}
