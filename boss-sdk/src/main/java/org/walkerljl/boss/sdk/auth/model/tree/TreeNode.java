/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.sdk.auth.model.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.walkerljl.boss.sdk.BaseEntity;

/**
 * 树节点
 *
 * @author lijunlin
 * @created 2012-03-09
 */
public class TreeNode extends BaseEntity implements Comparable<Object> {

    /**
     * id
     */
    private String  id;
    /**
     * 编号
     */
    private String  code;
    /**
     * 名称
     */
    private String  name;
    /**
     * 父id
     */
    private String  parentId;
    /**
     * 排序值
     */
    private Integer sortSequence;
    /**
     * 是否选中
     */
    private Boolean checked;
    /**
     * 是否展开节点
     */
    private Boolean expand;
    /**
     * 备注
     */
    private String  remark;
    /**
     * 菜单url
     */
    private String  url;
    /**
     * icon
     */
    private String  icon;
    /**
     * 是否禁用
     */
    private Boolean disabled;
    /**
     * 类型
     */
    private String  type;
    /**
     * 是否父亲
     */
    private Boolean parent;
    /**
     * 是否加载过,默认为false
     */
    private Boolean async;

    /**
     * 孩子
     */
    private List<TreeNode> children;

    /**
     * 构造函数
     */
    public TreeNode() {}

    /**
     * 构造函数
     *
     * @param id ID
     * @param name 名称
     * @param parentId 父ID
     */
    public TreeNode(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    /**
     * 获取Ztree节点序列化格式
     *
     * @return
     */
    public static Map<String, String> getZtreeNodeFormat() {
        Map<String, String> format = new HashMap<String, String>(6);
        format.put("parentId", "pId");
        format.put("icon", "iconSkin");
        format.put("disabled", "chkDisabled");
        format.put("expand", "open");
        format.put("parent", "isParent");
        format.put("async", "zAsync");
        return format;
    }

    @Override
    public int compareTo(Object o) {
        TreeNode treeNode = (TreeNode) o;
        if (treeNode.sortSequence > this.sortSequence) {
            return 1;
        } else if (treeNode.sortSequence < this.sortSequence) {
            return -1;
        } else {
            return treeNode.id.compareTo(this.id);
        }
    }

    /**
     * 添加孩子
     *
     * @param treeNode
     */
    public void addChild(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        if (children == null) {
            children = new ArrayList<TreeNode>(10);
        }
        children.add(treeNode);
    }

    /**
     * 获取节点id
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置节点id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取编号
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编号
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取节点名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置节点名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父节点id
     *
     * @return
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父节点id
     *
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序值
     *
     * @return
     */
    public Integer getSortSequence() {
        return sortSequence;
    }

    /**
     * 设置排序值
     *
     * @param sortSequence
     */
    public void setSortSequence(Integer sortSequence) {
        this.sortSequence = sortSequence;
    }

    /**
     * 是否选中
     *
     * @return
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * 设置是否选中,true:选中,false:不选中
     *
     * @param checked
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * 是否展开
     *
     * @return
     */
    public Boolean getExpand() {
        return expand;
    }

    /**
     * 设置是否展开,true:展开,false:不展开
     *
     * @param expand
     */
    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    /**
     * 获取备注
     *
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取URL
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取图标样式
     *
     * @return
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标样式
     *
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 是否禁用
     *
     * @return
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * 设置禁用,true:禁用,false:不禁用
     *
     * @param disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * 获取类型
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取孩子节点
     *
     * @return
     */
    public List<TreeNode> getChildren() {
        return children;
    }

    /**
     * 设置孩子节点
     *
     * @param children
     */
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    /**
     * 设置父节点
     *
     * @param parent
     * @return
     */
    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    /**
     * 是否父节点
     *
     * @return
     */
    public Boolean getParent() {
        return parent;
    }

    /**
     * 获取是否异步
     *
     * @return
     */
    public Boolean getAsync() {
        return async;
    }

    /**
     * 设置是否异步
     *
     * @param async
     */
    public void setAsync(Boolean async) {
        this.async = async;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        TreeNode other = (TreeNode) obj;
        if (id == null) {
            if (other.id != null) { return false; }
        } else if (!id.equals(other.id)) { return false; }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}