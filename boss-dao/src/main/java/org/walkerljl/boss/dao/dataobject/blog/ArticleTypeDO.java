package org.walkerljl.boss.dao.dataobject.blog;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;
import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Entity;

/**
 * ArticleType
 *
 * @author lijunlin
 */
@Entity("blog_article_type")
public class ArticleTypeDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Column("name")
    private String name;
    /**
     * 父Id
     */
    @Column("parent_id")
    private Long parentId;
    /**
     * 顺序值
     */
    @Column("order")
    private Integer order;

    public ArticleTypeDO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
