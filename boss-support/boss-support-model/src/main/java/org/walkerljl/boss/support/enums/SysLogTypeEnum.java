package org.walkerljl.boss.support.enums;

import org.walkerljl.toolkit.standard.enums.IEnum;

/**
 *
 * @author lijunlin
 */
public enum SysLogTypeEnum implements IEnum {

    /**
     * 添加
     */
    ADD("1", "添加"),

    /**
     * 修改
     */
    UPDATE("2", "修改"),

    /**
     * 查看
     */
    VIEW("3", "查看"),

    /**
     * 启用
     */
    ENABLE("4", "启用"),

    /**
     * 停用
     */
    DISABLE("5", "停用"),

    /**
     * 删除
     */
    DELETE("6", "删除"),

    /**
     * 物理删除
     */
    PHYSICS_DELETE("7", "物理删除"),

    ;

    /** 编码*/
    private String code;
    /** 描述*/
    private String description;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    SysLogTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过编码获取类型对象
     *
     * @param code 编码
     * @return
     */
    public static SysLogTypeEnum getByCode(String code) {

        for (SysLogTypeEnum item : SysLogTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }

        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}