package com.hkkj.modules.sys.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @package: com.hkkj.vo
 * @description: 登录用户信息
 * @version: V1.0.0
 */
@Data
public class LoginSession implements Serializable{

    private static final long serialVersionUID = 1847417165637588522L;
    /**
     * 登录用户sessionId
     */
    private Serializable sessionId;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录IP
     */
    private String loginIP;
}
