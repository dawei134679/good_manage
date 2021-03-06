package com.hkkj.modules.sys.vo;

import lombok.Data;

/**'
 * 树对象
 */
@Data
public class TreeNode {

    /**
     * id
     */
    private Long id;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 是否展开
     */
    private Boolean open = true;
}
